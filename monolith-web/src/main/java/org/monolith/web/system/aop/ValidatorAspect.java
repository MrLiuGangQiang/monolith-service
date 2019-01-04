package org.monolith.web.system.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.monolith.core.api.ApiCode;
import org.monolith.core.api.JsonApi;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
 * @date 2017年8月15日
 * @version 1.0
 * @description 实体类校验切面
 */
@Component
@Aspect
public class ValidatorAspect {

	/**
	 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
	 * @param point
	 * @return {@link Object}
	 * @throws Throwable
	 * @date 2017年8月18日
	 * @version 1.0
	 * @description 环绕校验
	 */
	@Around("execution(public * org.monolith.web.system.controller..*.*(..)) && args(..,result)")
	public Object around(ProceedingJoinPoint point, BindingResult result) throws Throwable {
		if (result.hasErrors()) {
			return new JsonApi(ApiCode.BAD_REQUEST).setMsg(result.getFieldError().getDefaultMessage());
		}
		return point.proceed();
	}
}