package org.appdot.archetypes.repository;

import org.appdot.archetypes.entity.account.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 权限组（角色）对象的Dao interface.
 * 
 * @author liand
 */

public interface RoleRepository extends PagingAndSortingRepository<Role, Long>, RoleRepositoryCustom {
}
