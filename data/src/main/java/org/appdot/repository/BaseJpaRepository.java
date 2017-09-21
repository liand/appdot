/**
 * 
 */
package org.appdot.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 基础的Repository(Dao)接口，继承自Spring的JpaRepository
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * 
 * @author Lian
 *
 */
@NoRepositoryBean
public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

}
