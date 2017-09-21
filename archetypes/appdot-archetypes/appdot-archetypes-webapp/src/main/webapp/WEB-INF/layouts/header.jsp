<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="navbar-collapse collapse" id="navbar">
<ul class="nav navbar-nav">
    <c:if test="${empty pageContext.request.remoteUser}">
        <li class="active">
            <a href="<c:url value="/login"/>">登录</a>
        </li>
    </c:if>
    <c:if test="${not empty pageContext.request.remoteUser}">
        <li>
            <a href="${ctx}/profile">编辑信息</a>
        </li>
        <li>
            <a href="<c:url value="/logout"/>">退出</a>
        </li>
    </c:if>
</ul>
</div>