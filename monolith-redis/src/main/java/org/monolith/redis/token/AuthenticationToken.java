package org.monolith.redis.token;

/**
 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
 * @Date 2016年11月2日
 * @Version 1.0
 * @Description 认证令牌对象
 */
public class AuthenticationToken {

	/**
	 * @Fields <font color="blue">cacheName</font>
	 * @description 缓存名
	 */
	private String cacheName;
	/**
	 * @Fields <font color="blue">key</font>
	 * @description 缓存token
	 */
	private String key;
	/**
	 * @Fields <font color="blue">session</font>
	 * @description 数据对象
	 */
	private AuthenticationSession session;

	public AuthenticationToken() {

	}

	/**
	 * Creates a new instance of AuthenticationToken.<br>
	 *
	 * @param cacheName
	 *            数据需要存入的缓存名
	 * @param key
	 *            数据的唯一key 注意：存入时是用户的唯一标识 而获取时是令牌token
	 * @param session
	 *            {@link AuthenticationSession} 缓存的数据对象
	 */
	public AuthenticationToken(String cacheName, String key, AuthenticationSession session) {
		this.cacheName = cacheName;
		this.key = key;
		this.session = session;
	}

	/**
	 * Creates a new instance of AuthenticationToken.<br>
	 *
	 * @param cacheName
	 *            数据需要存入的缓存名
	 * @param key
	 *            数据的唯一key 注意：存入时是用户的唯一标识 而获取时是令牌token
	 */
	public AuthenticationToken(String cacheName, String key) {
		this.cacheName = cacheName;
		this.key = key;
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public AuthenticationSession getSession() {
		return session;
	}

	public void setSession(AuthenticationSession session) {
		this.session = session;
	}
}
