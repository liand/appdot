<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="footer" class="container navbar-fixed-bottom">
	<span class="col-sm-6 text-left">App.Version <shiro:user>
            | 你好， <shiro:principal property="name"/>
            </shiro:user>
	</span>
	<span class="col-sm-6 text-right">
		Copyright &copy; 2012-2014 <a href="http://www.appdot.org">appdot.org</a>
	</span> 
</div>

