package org.monolith.core.entity;

import java.util.List;

/**
 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
 * @Date 2016年10月28日
 * @Version 1.0
 * @Description 顶层实体类抽象类 包含分页属性
 */
public class BaseEntity {

	/**
	 * @Fields <font color="blue">page</font>
	 * @description 当前页数 从1开始计数
	 */
	transient Integer page;
	/**
	 * @Fields <font color="blue">rows</font>
	 * @description 每页记录数 必须大于0
	 */
	transient Integer rows;

	/**
	 * 用来排序
	 */
	transient List<Sort> sorts;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		if (page != null) {
			this.page = page > 0 ? page : 1;
		} else {
			this.page = 1;
		}
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		if (rows != null) {
			this.rows = rows > 0 ? rows : 10;
		} else {
			this.rows = 1;
		}
	}

	public List<Sort> getSorts() {
		return sorts;
	}

	public void setSorts(List<Sort> sorts) {
		this.sorts = sorts;
	}

	/**
	 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
	 * @Date 2016年10月28日
	 * @Version 1.0
	 * @Description 用于新增单条记录的校验分组接口
	 */
	public interface Insert {
	};

	/**
	 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
	 * @Date 2016年10月28日
	 * @Version 1.0
	 * @Description 用于修改单条记录的校验分组接口
	 */
	public interface Update {
	};

	/**
	 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
	 * @Date 2016年10月28日
	 * @Version 1.0
	 * @Description 用于查询多条记录的校验分组接口
	 */
	public interface SelectAll {
	};

	/**
	 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
	 * @Date 2016年10月28日
	 * @Version 1.0
	 * @Description 用于查询条单条记录的校验分组接口
	 */
	public interface SelectOne {
	};

	/**
	 * 
	 * @author <font color="red"><b>Zhao.Fei</b></font>
	 * @Date 2017年10月23日
	 * @Version BaseEntity
	 * @Description 用于查询条数据重复的校验分组接口
	 */
	public interface SelectRepeat {
	};

	/**
	 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
	 * @Date 2016年10月28日
	 * @Version 1.0
	 * @Description 用于删除记录的校验分组接口
	 */
	public interface Delete {
	};
}
