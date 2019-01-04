package org.monolith.core.auth;

/**
 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
 * @date 2017年8月10日
 * @version 1.0
 * @description 安全级别枚举类<br>
 * 				目前提供三种级别<br>
 * 				ROLE:角色级别<br>
 * 				PERMISSION:权限级别<br>
 * 				OPERATION:操作（方法）级别
 */
public enum Level {
	/**
	 * 角色级别，指安全控制只精确到角色这一级
	 */
	ROLE,
	/**
	 * 权限级别，指安全控制只精确到权限这一级
	 */
	PERMISSION,
	/**
	 * 操作级别，指安全控制只精确到操作（方法）这一级
	 */
	OPERATION
}
