package org.monolith.core.dao;

import java.util.List;
import java.util.Map;

import org.monolith.core.entity.BaseEntity;

/**
 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
 * @Date 2016年10月28日
 * @Version 1.0
 * @Description 顶层基础Mapper
 */
public abstract interface IBaseMapper<T extends BaseEntity> {
	/**
	 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
	 * @param entity
	 * @return {@link Map}
	 * @date 2016年10月28日
	 * @version 1.0
	 * @description 检查数据是否存在
	 */
	abstract Map<String, Object> getRepeat(T entity);

	/**
	 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
	 * @param entity
	 * @return {@link Integer}
	 * @date 2016年10月28日
	 * @version 1.0
	 * @description 新增对象
	 */
	abstract int insert(T entity);

	/**
	 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
	 * @param entity
	 * @return {@link Integer}
	 * @date 2016年10月28日
	 * @version 1.0
	 * @description 删除对象
	 */
	abstract int delete(T entity);

	/**
	 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
	 * @param entity
	 * @return {@link Integer}
	 * @date 2016年10月28日
	 * @version 1.0
	 * @description 修改对象
	 */
	abstract int update(T entity);

	/**
	 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
	 * @param entity
	 * @return {@link Map}
	 * @date 2016年10月28日
	 * @version 1.0
	 * @description 查询单个对象
	 */
	abstract Map<String, Object> getOne(T entity);

	/**
	 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
	 * @param entity
	 * @return {@link List}
	 * @date 2016年10月28日
	 * @version 1.0
	 * @description 查询多条记录
	 */
	abstract List<Map<String, Object>> getList(T entity);
}