package org.appdot.archetypes.repository;

import org.appdot.archetypes.entity.account.User;
import org.appdot.repository.BaseJpaRepository;


/**
 * 用户对象的Dao interface.
 * 
 * @author liand
 */
public interface UserRepository extends BaseJpaRepository<User, Long> {

	User findByLoginName(String loginName);
}
