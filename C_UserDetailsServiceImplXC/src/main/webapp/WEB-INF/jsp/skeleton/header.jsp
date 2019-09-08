<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
	.header-item:hover
	{
		cursor: pointer;
	}
	.logout-label
	{
		font-size: x-small;
	}
	.glyphicon
	{
		 color:white;
	}
</style>

<div class="col-md-2 header-item"><spring:message code="app.header.projectname.label"/>
</div>
<div class="col-md-3 offset-7 user-info-session-control-box">
	<div class="row">
		<div class="col-md-4 header-item">
			<span class="badge badge-primary" title="click to keep session alive" id="sessionTimeRemaining" 
				onclick="ajaxSessionRefresh()" style="display:none;">
				<i class="badge badge-danger" id="sessionTimeRemainingBadge" style="float:left">30</i>
				 &nbsp; 
				 <small>Refresh</small>
				 <i class="glyphicon glyphicon-refresh"></i>
			</span>
		</div>
		<div class="col-md-4 header-item">
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
