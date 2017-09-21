/**
 * 
 */
package org.appdot.web.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import net.sf.navigator.menu.PermissionsAdapter;

import org.appdot.web.util.ShiroPermissionsAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 扩展Struts-Menu的UseMenuDisplayer，缺省使用ShiroPermissionsAdapter判断菜单显示的权限控制
 * 
 * @author Lian
 *
 */
public class UseMenuDisplayerTag extends net.sf.navigator.taglib.el.UseMenuDisplayerTag {

	private static final long serialVersionUID = 4797957744660671169L;

	public static final String PERMISSIONS_ADAPTER = "permissionsAdapter";

	private static final Logger logger = LoggerFactory.getLogger(UseMenuDisplayerTag.class);

	@Override
	protected PermissionsAdapter getPermissionsAdapter() throws JspException {
		String permissions = getPermissions();
		logger.debug("permissions is {}", permissions);
		if (permissions == null || permissions.equalsIgnoreCase(PERMISSIONS_ADAPTER)) {
			PermissionsAdapter permissionsAdapter = (PermissionsAdapter) pageContext.getAttribute(PERMISSIONS_ADAPTER);
			if (permissionsAdapter == null) {
				permissionsAdapter = new ShiroPermissionsAdapter();
				pageContext.setAttribute(PERMISSIONS_ADAPTER, permissionsAdapter, PageContext.APPLICATION_SCOPE);
			}
			return permissionsAdapter;
		}
		return super.getPermissionsAdapter();
	}

}
