<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.4.1.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.bundle.min.js" />" ></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}<spring:theme code='styleSheet'/>" type="text/css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Title</title>
	<style>
		html, body 
		{
		  height: 100%;
		  margin: 0;
		}
		.wrapper 
		{
		  height: 100%;
		}
		.content 
		{
		  flex: 1;
		  overflow: auto;
		  min-height:86%
		}
		.header
		{
			min-height:8%;
			padding-top: 15px;
			font-variant-caps: petite-caps;
	   	  	font-family: initial;
		}
		.footer
		{
			min-height:6%;
			background-color: lavender;
	   		color: black;
		}
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
</head>
<body>
	<div class="container-fluid wrapper">
		<div class="row header">
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
		</div>
		<div class="row content">
			<div class="col-md-2 col-sm-2 col-xs-2 sidebar">
				<div class="menu-item">
					<a href="${pageContext.request.contextPath}/app/admin/list-user" style="color:white">
						<spring:message code="app.menu.userlist.label"/>
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
			</div>
			<div class="col-md-10 col-sm-10 col-xs-10">
				<div class="col-md-6">
				<c:if test="${empty update}">
					<h2 style="font-variant-caps: all-petite-caps;"><spring:message code="app.menu.adduser.label"/></h2>
					<hr>
					<form:form method="post" action="${pageContext.request.contextPath}/app/admin/add-user" modelAttribute="user">
						<form:hidden path="id"/>
						<table>
							<tr>
								<td style="width:200px">
									<form:label path="firstName"><spring:message code="app.userform.firstname.label"/></form:label>
								</td>
								<td><form:input path="firstName" /></td>
							</tr>
							<tr>
								<td>
									<form:label path="lastName"><spring:message code="app.userform.lastname.label"/></form:label>
								</td>
								<td><form:input path="lastName" /></td>
							</tr>
							<tr>
								<td>
									<form:label path="email"><spring:message code="app.userform.email.label"/></form:label>
								</td>
								<td><form:input path="email" /></td>
							</tr>
							<tr>
								<td>
									<form:label path="gender"><spring:message code="app.userform.gender.label"/></form:label>
								</td>
								<td>
									<form:radiobutton path="gender" value="MALE" /><spring:message code="app.userform.gender.male.label"/> 
									<form:radiobutton path="gender" value="FEMALE" /><spring:message code="app.userform.gender.female.label"/>
								</td>
							</tr>
							<tr>
								<td>
									<form:label path="userName"><spring:message code="app.userform.username.label"/></form:label>
								</td>
								<td><form:input path="userName" /></td>
							</tr>
							<tr>
								<td>
									<form:label path="password"><spring:message code="app.userform.password.label"/></form:label>
								</td>
								<td><form:password path="password" /></td>
							</tr>
							<tr>
								<td>
									<form:label path="selectedRoles"><spring:message code="app.userform.role.label"/></form:label>
								</td>
								<td>
									<form:checkboxes path="selectedRoles" items="${roleList}" itemLabel="name" itemValue="id"/>
								</td>
							</tr>
							
							<tr>
								<td colspan="2"><input type="submit" value="<spring:message code="app.actions.save.label"/>" /></td>
							</tr>
						</table>
					</form:form>
				</c:if>
				
				<c:if test="${not empty update}">
					<h2 style="font-variant-caps: all-petite-caps;"><spring:message code="app.menu.updateuser.label"/></h2>
					<hr>
					<form:form method="post" action="${pageContext.request.contextPath}/app/admin/modify-user" modelAttribute="user">
						<form:hidden path="id"/>
						<form:hidden path="previousMappings"/>
						<table>
							<tr>
								<td style="width:200px">
									<form:label path="firstName"><spring:message code="app.userform.firstname.label"/></form:label>
								</td>
								<td><form:input path="firstName" /></td>
							</tr>
							<tr>
								<td>
									<form:label path="lastName"><spring:message code="app.userform.lastname.label"/></form:label>
								</td>
								<td><form:input path="lastName" /></td>
							</tr>
							<tr>
								<td>
									<form:label path="email"><spring:message code="app.userform.email.label"/></form:label>
								</td>
								<td><form:input path="email" /></td>
							</tr>
							<tr>
								<td>
									<form:label path="gender"><spring:message code="app.userform.gender.label"/></form:label>
								</td>
								<td>
									<form:radiobutton path="gender" value="MALE" /><spring:message code="app.userform.gender.male.label"/> 
									<form:radiobutton path="gender" value="FEMALE" /><spring:message code="app.userform.gender.female.label"/>
								</td>
							</tr>
							<tr>
								<td>
									<form:label path="userName"><spring:message code="app.userform.username.label"/></form:label>
								</td>
								<td><form:input path="userName" /></td>
							</tr>
							<tr>
								<td>
									<form:label path="password"><spring:message code="app.userform.password.label"/></form:label>
								</td>
								<td><form:password path="password" /></td>
							</tr>
							<tr>
								<td>
									<form:label path="selectedRoles"><spring:message code="app.userform.role.label"/></form:label>
								</td>
								<td>
									<form:checkboxes path="selectedRoles" items="${roleList}" itemLabel="name" itemValue="id"/>
								</td>
							</tr>
							
							<tr>
								<td colspan="2"><input type="submit" value="<spring:message code="app.actions.update.label"/>" /></td>
							</tr>
						</table>
					</form:form>	
				</c:if>
				
				<c:if test="${not empty msg}">
					<script>
						alert("${msg}");
					</script>
				</c:if>
			</div>
			</div>
		</div>
		
		<div class="row footer">
			<div style="margin: auto;">
				&copy;Copyright  2019-2020 nlpraveennl@gmail.com
			</div>
		</div>
	</div>
</body>
</html>


