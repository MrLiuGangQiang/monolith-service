package org.monolith.redis.cache;

/**
 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
 * @date 2017年8月16日
 * @version 1.0
 * @description redis缓存配置
 */
public class RedisConfig {
	/**
	 * 有效期
	 */
	private Long expire;
	/**
	 * 是否自动延期
	 */
	private Boolean isDelay;

	public RedisConfig(Long expire, Boolean isDelay) {
		this.expire = expire;
		this.isDelay = isDelay;
	}

	public RedisConfig() {
	}

	public Long getExpire() {
		return expire;
	}

	public void setExpire(Long expire) {
		this.expire = expire;
	}

	public Boolean getIsDelay() {
		return isDelay;
	}

	public void setIsDelay(Boolean isDelay) {
		this.isDelay = isDelay;
	}
}
