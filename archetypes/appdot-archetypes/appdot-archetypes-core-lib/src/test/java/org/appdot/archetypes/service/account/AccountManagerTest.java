/**
 * 
 */
package org.appdot.archetypes.service.account;

import org.appdot.archetypes.entity.account.Role;
import org.appdot.test.service.BaseManagerTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Lian
 *
 */
public class AccountManagerTest extends BaseManagerTestCase {

	@Autowired
	AccountManager accountManager;

	@Test
	public void testSaveRole() {
		Role role = accountManager.getRole(-1L);
		role.setName("neverHasThis");
		accountManager.saveRole(role);
		logger.debug("updated role == {}", role);
	}
}
