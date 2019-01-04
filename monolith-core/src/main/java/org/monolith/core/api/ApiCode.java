package org.monolith.core.api;

/**
 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
 * @Date 2016年10月28日
 * @Version 1.0
 * @Description 操作码枚举类
 */
public enum ApiCode {

	/**
	 * 成功
	 */
	OK(200, "Ok"),

	/**
	 * 失败
	 */
	FAIL(202, "Fail"),
	/**
	 * 请求参数有误
	 */
	BAD_REQUEST(400, "Bad Request"),
	/**
	 * 未授权
	 */
	UNAUTHORIZED(401, "Unauthorized"),
	/**
	 * 拒绝（权限不足）
	 */
	FORBIDDEN(403, "Forbidden"),
	/**
	 * 没有数据
	 */
	NOT_FOUND(404, "Not Found"),
	/**
	 * 数据冲突
	 */
	CONFLICT(409, "Conflict"),
	/**
	 * 系统异常
	 */
	ERROR(500, "Internal Server Error"),
	/**
	 * 方法未定义
	 */
	UNIMPLEMENTED(501, "Not Implemented"),
	/**
	 * 超时
	 */
	TIMEOUT(504, "Gateway Timeout");

	private int value;
	private String message;

	public int getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}
	
	ApiCode(int value, String message) {
		this.value = value;
		this.message = message;
	}
}