<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<fmt:message key="app.login.username.required" var="usernameRequired" />
<fmt:message key="app.login.password.required" var="passwordRequired" />
<fmt:message key="app.login.username.label" var="usernameLabel" />
<fmt:message key="app.login.password.label" var="passwordLabel" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.4.1.js" />" ></script>
		<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
		<style>
		.login-form-container
		{
			margin-top:10%;
			border: 1px solid darkgray;
		    padding: 15px;
		    background-color: royalblue;
		    color: white;
		}
		.login-form-header
		{
			font-family: monospace;
		    font-size: 22px;
		    border-bottom: 1px solid white;
		}
		.login-form-header p{
			margin: auto
		}
		.login-form-body
		{
			margin-top: 25px;
		}
		.errors-container
		{
			padding: 15px;
			margin-top: 10px;
			border: 1px solid transparent;
			border-radius: 4px;
			color: #a94442;
			background-color: #f2dede;
			border-color: #ebccd1;
		}
		.message-container
		{
			padding: 15px;
			margin-top: 10px;
			border: 1px solid transparent;
			border-radius: 4px;
			color: #31708f;
			background-color: #d9edf7;
			border-color: #bce8f1;
		}
		</style>
	</head>
	
	<body>
		<div class="container-fluid">
			<form:form method="post" action="login" class="form-horizontal" modelAttribute="login"  onsubmit="return validate(this);">
				<div class="login-form-container col-md-4 offset-md-4">
					<div class="row login-form-header">
						<p style="font-variant-caps: all-petite-caps;"><spring:message code="app.login.loginhere.label"/></p>
					</div>
					<div class="login-form-body">
						  <div class="form-group">
						  	<div class="col-sm-12" style="font-variant-caps: all-petite-caps;">
						   		<spring:message code="app.login.username.label"/>
						   	</div>
						    <div class="col-sm-12">
						      <form:input path="userName" type="text" id="username" placeholder="${usernameLabel}" style="width:100%"/>
						    </div>
						  </div>
						  <div class="form-group" style="font-variant-caps: all-petite-caps;">
						  	<div class="col-sm-12">
						    	<spring:message code="app.login.password.label"/>
						    </div>
						    <div class="col-sm-12">
						      <form:password path="password" id="password" placeholder="${passwordLabel}" style="width:100%"/>
						    </div>
						  </div>
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <button type="submit" class="btn btn-default" style="font-variant-caps: all-petite-caps;"><spring:message code="app.login.signin.label"/></button>
						    </div>
						  </div>
					</div>
				</div>
				<c:if test="${not empty error}">
					<div class="errors-container col-md-4 offset-md-4">
						<form:errors path="*" />
						<div class="error">${error}</div>
					</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="message-container col-md-4 offset-md-4">
						<div class="msg">${msg}</div>
					</div>
				</c:if>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form:form>
		</div>
	</body>
	<script>
		function validate()
		{
			if($('#username').val().trim().length == 0)
			{
				alert("${usernameRequired}");
				return false;
			}
			else if($('#password').val().trim().length == 0)
			{
				alert("${passwordRequired}");
				return false;
			}
		}
	</script>
</html>