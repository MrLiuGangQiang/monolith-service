package org.monolith.redis.token;

import java.io.Serializable;

/**
 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
 * @Date 2016年11月2日
 * @Version 1.0
 * @Description 缓存的数据对象
 */
@SuppressWarnings("unchecked")
public class AuthenticationSession implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * @Fields <font color="blue">identify</font>
	 * @description 唯一区别串
	 */
	private String identify;
	/**
	 * @Fields <font color="blue">data</font>
	 * @description 缓存主体数据
	 */
	private Object data;

	public AuthenticationSession() {

	}

	public AuthenticationSession(String identify, Object data) {
		this.identify = identify;
		this.data = data;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public Object getData() {
		return data;
	}

	public <T> T get(Class<T> type) {
		return data == null ? null : (T) data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
