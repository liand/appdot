/**
 * 
 */
package org.appdot.service.impl;

import java.io.Serializable;
import java.util.List;

import org.appdot.repository.BaseJpaRepository;
import org.appdot.service.GenericManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * 
 * @param <T> 实体类型
 * @param <ID> 实体类型的主键
 * 
 * @author Lian
 */
public class GenericManagerImpl<T, ID extends Serializable> implements GenericManager<T, ID> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 基础的Repository接口, 由子类在构造器中注入
	 */
	protected BaseJpaRepository<T, ID> dao;

	public GenericManagerImpl() {
	}

	public GenericManagerImpl(BaseJpaRepository<T, ID> dao) {
		this.dao = dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T get(ID id) {
		return dao.findOne(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> getAll() {
		return dao.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T save(T entity) {
		return dao.save(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(ID id) {
		dao.delete(id);
	}
}
