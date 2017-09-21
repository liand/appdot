/**
 * 
 */
package org.appdot.service;

import java.io.Serializable;
import java.util.List;

/**
 * 基础的业务接口完成基本的CRUD操作.
 *
 * <p>如果决定在Service层使用接口，继承这个基础的接口，拥有三个常用方法</p>
 *
 * @author <a href="mailto:duanlian@keesail.com">Lian Duan</a>
 * @param <T> 实体类型
 * @param <ID> 实体类型的主键
 */
public interface GenericManager<T, ID extends Serializable> {

	/**
	 * 保存实体对象的通用方法，同时处理对象的创建和修改
	 * @param 要保存的对象
	 * @return 保存后的对象
	 */
	public T save(T entity);

	/**
	 * 获取所有特定类型实体对象的通用方法，不适用于查询大量实体对象
	 * 
	 * @return 特定类型的实体对象集合
	 */
	public List<T> getAll();

	/**
	 * 获取单个实体对象的通用方法. 
	 * 如果未找到实体对象，返回null
	 *
	 * @param id 对象的主键
	 * @return 实体对象
	 */
	public T get(ID id);

	/**
	 * 删除实体对象的通用方法
	 * 
	 * @param id 要删除的实体主键
	 */
	void delete(ID id);

}
