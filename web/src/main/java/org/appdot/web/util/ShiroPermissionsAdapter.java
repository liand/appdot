/**
 * 
 */
package org.appdot.web.util;

import java.util.regex.Pattern;

import net.sf.navigator.menu.MenuComponent;
import net.sf.navigator.menu.PermissionsAdapter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用Shiro的权限控制判断菜单是否可见
 * 
 * @author Lian
 *
 */
public class ShiroPermissionsAdapter implements PermissionsAdapter {

	private static final Logger logger = LoggerFactory.getLogger(ShiroPermissionsAdapter.class);

	private Pattern delimiters = Pattern.compile("(?<!\\\\),");

	/* (non-Javadoc)
	 * @see net.sf.navigator.menu.PermissionsAdapter#isAllowed(net.sf.navigator.menu.MenuComponent)
	 */
	@Override
	public boolean isAllowed(MenuComponent menu) {
		logger.debug("{}'s roles is {}", menu.getName(), menu.getRoles());
		if (menu.getRoles() == null) {
			return true;
		}
		// Get the list of roles this menu allows
		String[] allowedRoles = delimiters.split(menu.getRoles());

		Subject subject = SecurityUtils.getSubject();
		for (int i = 0; i < allowedRoles.length; i++) {
			if (subject.isPermitted(allowedRoles[i])) {
				return true;
			}
		}
		return false;
	}

}
