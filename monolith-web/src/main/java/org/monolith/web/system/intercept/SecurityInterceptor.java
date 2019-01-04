package org.monolith.web.system.intercept;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.monolith.core.api.ApiCode;
import org.monolith.core.api.JsonApi;
import org.monolith.core.auth.RequiresAuthentication;
import org.monolith.redis.cache.RedisCacheManager;
import org.monolith.redis.token.AuthenticationSession;
import org.monolith.redis.token.AuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

/**
 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
 * @date 2017年7月4日
 * @version 1.0
 * @description 安全拦截器，用于校验该请求携带的令牌是否能访问系统某方法
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);
	@Autowired
	private RedisCacheManager cacheManager;

	/**
	 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
	 * @param list
	 * @param auths
	 * @return {@link Boolean}
	 * @date 2017年7月4日
	 * @version 1.0
	 * @description 权限校验方法
	 */
	private boolean validate(List<Map<String, Object>> list, String[] auths) {
		if (list != null && list.size() > 0) {
			for (String auth : auths) {
				for (Map<String, Object> map : list) {
					if (map != null && map.size() > 0) {
						if (auth.equals(map.get("code"))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			/* 设置响应头为Json */
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			/* 获取token */
			String token = request.getHeader("你的token的key");
			/* 获取方法 */
			HandlerMethod method = (HandlerMethod) handler;
			/* 获取权限注解 */
			RequiresAuthentication authentication = method.getMethodAnnotation(RequiresAuthentication.class);
			AuthenticationSession session = cacheManager.getSession(new AuthenticationToken("你的缓存", token));
			if (authentication == null) {
				/* 如果权限注解为空不允许访问 */
				if (logger.isInfoEnabled()) {
					logger.info("你的日志");
				}
				response.getWriter().write(JSON.toJSONString(new JsonApi(ApiCode.UNIMPLEMENTED)));
				return false;
			}
			/* 权限注解不为空 则进行鉴权判断 */
			if (authentication.ignore()) {
				/* 如果设置忽略校验直接放行 */
				return true;
			} else if (authentication.authc()) {
				/* 如果设置只做登录鉴权则判断是否登录，登录则放行 */
				if (session != null) {
					return true;
				}
				if (logger.isInfoEnabled()) {
					logger.info("你的日志");
				}
				response.getWriter().write(JSON.toJSONString(new JsonApi(ApiCode.UNAUTHORIZED)));
				return false;
			} else {
				/* 这里进行具体的业务权限判断 */
				switch (authentication.level()) {
				/* 角色判断 */
				case ROLE:
					/* 获取角色列表 */
					// TODO 实际开发中需要通过数据库获取
					List<Map<String, Object>> roleList = null;
					if (validate(roleList, authentication.value())) {
						return true;
					}
					break;
				case PERMISSION:
					/* 获取角色列表 */
					// TODO 实际开发中需要通过数据库获取
					List<Map<String, Object>> permissionList = null;
					if (validate(permissionList, authentication.value())) {
						return true;
					}
					break;
				case OPERATION:
					/* 获取角色列表 */
					// TODO 实际开发中需要通过数据库获取
					List<Map<String, Object>> operationList = null;
					if (validate(operationList, authentication.value())) {
						return true;
					}
					break;
				}
			}
			if (logger.isInfoEnabled()) {
				logger.info("你的日志");
			}
			response.getWriter().write(JSON.toJSONString(new JsonApi(ApiCode.FORBIDDEN)));
			return false;
		}
		return true;
	}
}