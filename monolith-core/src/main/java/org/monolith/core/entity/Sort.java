package org.monolith.core.entity;

/**
 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
 * @date 2017年9月21日
 * @version 1.0
 * @description 排序辅助类
 */
public class Sort {

	private String field;
	private String direction;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getDirection() {
		if (direction != null && ("ASC".equalsIgnoreCase(direction) || "DESC".equalsIgnoreCase(direction))) {
			return direction;
		} else {
			return "ASC";
		}
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
