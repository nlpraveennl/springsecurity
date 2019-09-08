<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="menu-item">
	<a href="${pageContext.request.contextPath}/app/admin/list-user" style="color:white">
		<spring:message code="app.menu.userlist.label"/>
	</a>
</div>
<div class="menu-item">
	<a href="${pageContext.request.contextPath}/app/admin/user-form" style="color:white">
		<spring:message code="app.menu.adduser.label"/>
	</a>
</div>
<div class="menu-item">
	<a href="${pageContext.request.contextPath}/app/admin/app-config" style="color:white">
		<spring:message code="app.menu.appconfig.label"/>
	</a>
</div>
<div class="menu-item">
	<a href="${pageContext.request.contextPath}/app/user/dashboard" style="color:white">
		<spring:message code="app.dashboard.label"/>
	</a>
</div>