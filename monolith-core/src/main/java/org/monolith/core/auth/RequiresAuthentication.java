package org.monolith.core.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
 * @date 2017年8月10日
 * @version 1.0
 * @description 安全控制注解，主要适用于基于RBAC模型的鉴权
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequiresAuthentication {

	/**
	 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
	 * @return {@link Level}
	 * @date 2017年8月10日
	 * @version 1.0
	 * @description 控制细粒度（级别）
	 */
	Level level();

	/**
	 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
	 * @return {@link String[]}
	 * @date 2017年8月10日
	 * @version 1.0
	 * @description 权限描述值，用于匹配数据库<br>
	 *              建议命名规则：权限等级[:模块][:对象][:操作]<br>
	 *              例如：<br>
	 *              role:user:manager 用户管理角色<br>
	 *              role:user:user:select 用户查询权限<br>
	 *              role:user:user:user:insert 新增用户操作<br>
	 */
	String[] value();

	/**
	 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
	 * @return {@link Boolean}
	 * @date 2017年8月10日
	 * @version 1.0
	 * @description 是否忽略校验 默认false
	 */
	boolean ignore() default false;

	/**
	 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
	 * @return {@link Boolean}
	 * @date 2017年8月10日
	 * @version 1.0
	 * @description 是否只做登录鉴权 默认false
	 */
	boolean authc() default false;
}
