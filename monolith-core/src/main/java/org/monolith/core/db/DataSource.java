package org.monolith.core.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* @author <font color="green"><b>Liu.Gang.Qiang</b></font> 
* @date 2017年7月14日 
* @version 1.0
* @description 多数据源注解
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface DataSource {
	/** 
	* @author <font color="red"><b>Liu.Gang.Qiang</b></font>
	* @return {@link String}
	* @date 2017年7月15日 
	* @version 1.0
	* @description 数据源名称，来源Spring配置
	*/
	String value();
}
