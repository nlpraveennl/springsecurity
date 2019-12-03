<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-md-2 header-item"><spring:message code="app.header.projectname.label"/>
</div>
<div class="col-md-3 offset-7 user-info-session-control-box">
	<div class="row">
		<div class="offset-4 col-md-4 header-item">
			<span class="glyphicon glyphicon-user"></span>
			<span>${loggedInUser.userName}</span>
		</div>
		<div class="col-md-4 header-item">
			<a href="${pageContext.request.contextPath}/logout">
				<span class="glyphicon glyphicon-off"></span>
				<span class="logout-label"><spring:message code="app.header.logout.label"/></span>
			</a>
		</div>
	</div>
</div>
