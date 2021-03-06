package org.appdot.archetypes.repository.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.appdot.archetypes.entity.account.Role;
import org.appdot.archetypes.entity.account.User;
import org.appdot.archetypes.entity.data.AccountData;
import org.appdot.archetypes.repository.BaseDaoJpaTestCase;
import org.appdot.archetypes.repository.RoleRepository;
import org.appdot.archetypes.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * RoleDao的测试用例, 测试ORM映射及特殊的DAO操作.
 * 
 * @author liand
 */
public class RoleRepositoryTest extends BaseDaoJpaTestCase {

	@Autowired
	private RoleRepository roleDao;

	@Autowired
	private UserRepository userDao;

	/**
	 * 测试删除权限组时删除用户-权限组的中间表.
	 * @throws Exception 
	 */
	@Test
	public void deleteRole() throws Exception {

		//新增测试权限组并与admin用户绑定.
		Role role = AccountData.getRandomRole();
		roleDao.save(role);
		flush();

		User user = userDao.findOne(-1L);
		user.getRoleList().add(role);
		userDao.save(user);
		flush();

		int oldJoinTableCount = countRowsInTable("TW_USER_ROLE");
		int oldUserTableCount = countRowsInTable("TW_USER");

		//删除用户权限组, 中间表将减少1条记录,而用户表应该不受影响.
		roleDao.deleteWithReference(role.getId());
		flush();

		user = userDao.findOne(1L);
		int newJoinTableCount = countRowsInTable("TW_USER_ROLE");
		int newUserTableCount = countRowsInTable("TW_USER");
		assertEquals(1, oldJoinTableCount - newJoinTableCount);
		assertEquals(0, oldUserTableCount - newUserTableCount);
	}

	@Test
	public void crudEntityWithRole() {
		//新建并保存带权限组的用户
		Role role = AccountData.getRandomRoleWithPermissions();
		roleDao.save(role);
		flush();
		//获取用户
		role = roleDao.findOne(role.getId());
		assertTrue(role.getPermissionList().size() > 0);

		logger.debug("role == {}", role);
	}
}
