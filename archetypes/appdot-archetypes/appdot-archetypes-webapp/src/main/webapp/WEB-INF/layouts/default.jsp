<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html lang="zh">
<head>
<title>AppDot:<decorator:title /></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<t:assets />
<decorator:head />
</head>

<body <decorator:getProperty property="body.id" writeEntireProperty="true"/>
	<decorator:getProperty property="body.class" writeEntireProperty="true"/>>
	<c:set var="currentMenu" scope="request">
		<decorator:getProperty property="meta.menu" />
	</c:set>

	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="navbar-header">
			<%-- For smartphones and smaller screens --%>
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value='/'/>">AppDot</a>
		</div>
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
	</div>

	<div class="container">
		<div class="row">
			<c:choose>
				<c:when test="${currentMenu == 'Login'}">
					<decorator:body />
				</c:when>
				<c:otherwise>
					<div class="col-sm-2">
						<%@ include file="/WEB-INF/layouts/menu.jsp"%>
					</div>
					<div class="col-sm-10">
						<decorator:body />
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<%@ include file="/WEB-INF/layouts/footer.jsp"%>
	</div>
	<%=(request.getAttribute("scripts") != null) ? request.getAttribute("scripts") : ""%>
</body>
</html>