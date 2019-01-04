package org.monolith.web.system.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.monolith.core.api.ApiCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;

/**
 * <font color="green">全局异常处理拦截器</font>
 * 
 * @ClassName MyExceptionResolver
 * @author Friday-LiuGangQiang
 * @date 2015年10月12日 上午11:53:25
 *
 * @version 1.0
 */

public class ExceptionResolver extends SimpleMappingExceptionResolver {
	Logger log = LoggerFactory.getLogger(ExceptionResolver.class);

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		if (handler instanceof HandlerMethod) {
			// HandlerMethod method = (HandlerMethod) handler;
			ModelAndView mv = new ModelAndView();
			FastJsonJsonView view = new FastJsonJsonView();
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("code", ApiCode.ERROR.getValue());
			attributes.put("data", "");
			attributes.put("msg", ApiCode.ERROR.getMessage());
			view.setAttributesMap(attributes);
			mv.setView(view);
			log.error("Json Exception {}", ex.getMessage());
			return mv;
		}
		return super.doResolveException(request, response, handler, ex);
	}
}