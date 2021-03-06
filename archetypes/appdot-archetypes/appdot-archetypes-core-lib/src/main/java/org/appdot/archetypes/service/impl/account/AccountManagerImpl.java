package org.appdot.archetypes.service.impl.account;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.appdot.archetypes.entity.account.Role;
import org.appdot.archetypes.entity.account.User;
import org.appdot.archetypes.repository.RoleRepository;
import org.appdot.archetypes.repository.UserRepository;
import org.appdot.archetypes.service.ServiceException;
import org.appdot.archetypes.service.account.AccountManager;
import org.appdot.archetypes.service.account.ShiroDbRealm;
import org.appdot.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springside.modules.persistence.Hibernates;

/**
 * 安全相关实体的管理类,包括用户和角色.
 * 
 * @author liand
 */
//Spring Bean的标识.
@Service("accountManager")
public class AccountManagerImpl extends GenericManagerImpl<User, Long> implements AccountManager {

	private UserRepository userDao;
	private RoleRepository roleDao;
	private ShiroDbRealm shiroRealm;

	public AccountManagerImpl() {
	}

	@Override
	public User save(User entity) {
		User user = super.save(entity);
		shiroRealm.clearCachedAuthorizationInfo(entity.getLoginName());
		return user;
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	@Override
	public void delete(Long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", SecurityUtils.getSubject().getPrincipal());
			throw new ServiceException("不能删除超级管理员用户");
		}
		super.delete(id);
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	@Override
	public User getUserByLoginName(String loginName) {
		User user = userDao.findByLoginName(loginName);
		if (user != null) {
			Hibernates.initLazyProperty(user.getRoleList());
		}
		return user;
	}

	//-- Role Manager --//
	@Override
	public Role getRole(Long id) {
		return roleDao.findOne(id);
	}

	@Override
	public List<Role> getAllRole() {
		return (List<Role>) roleDao.findAll((new Sort(Direction.ASC, "id")));
	}

	@Override
	public void saveRole(Role entity) {
		logger.debug("role == {}", entity);
		roleDao.save(entity);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	@Override
	public void deleteRole(Long id) {
		roleDao.delete(id);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	@Autowired
	public void setUserDao(UserRepository userDao) {
		this.dao = userDao;
		this.userDao = userDao;
	}

	@Autowired
	public void setRoleDao(RoleRepository roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired(required = false)
	public void setShiroRealm(ShiroDbRealm shiroRealm) {
		this.shiroRealm = shiroRealm;
	}
}
