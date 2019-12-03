<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="menu-item">
	<a href="${pageContext.request.contextPath}/app/admin/list-user" style="color:white">
		<spring:message code="app.menu.userlist.label"/>
	</a>
</div>
<div class="menu-item">
	<a ng-href="#!/UsersList" style="color:white">
		Angular <spring:message code="app.menu.userlist.label"/>
	</a>
</div>
<div class="menu-item">
	<a href="${pageContext.request.contextPath}/app/user/projects-list" style="color:white">
		<spring:message code="app.menu.projectlist.label"/>
	</a>
</div>
<div class="menu-item">
	<a href="${pageContext.request.contextPath}/app/user/issue-list" style="color:white">
		<spring:message code="app.menu.issuelist.label"/>
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
<div class="menu-item">
	<a ng-href="#!/Dashboard" style="color:white">
		Angular <spring:message code="app.dashboard.label"/>
	</a>
</div>