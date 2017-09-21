<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://struts-menu.sf.net/tag-el" prefix="menu" %>
<%@ taglib prefix="appdot" uri="http://www.appdot.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<appdot:useMenuDisplayer name="Velocity" config="navlistMenu.vm" permissions="permissionsAdapter">
<shiro:user>
	<div id="menu">	
		<div class="panel-group" id="accordionMenu">
			<menu:displayMenu name="SystemManagementMenu" />
			<menu:displayMenu name="ExampleMenu" />
		</div>
	</div>
</shiro:user>
</appdot:useMenuDisplayer>