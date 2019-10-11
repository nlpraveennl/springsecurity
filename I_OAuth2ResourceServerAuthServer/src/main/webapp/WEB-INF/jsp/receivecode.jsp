<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Auth and Resource Server</title>
</head>
<body>
	<h4>Received Code: '${token.code}'</h4>
	
	<form:form action="${pageContext.request.contextPath}/oauth/token" method="post" modelAttribute="token">
<pre>
Grant Type   : <form:input path="grant_type" style="width:500px" /><br>
Code         : <form:input path="code" style="width:500px" /><br>
Redirect URI : <form:input path="redirect_uri" style="width:500px"/><br>
<form:button type="submit">Get Token</form:button>
</pre>
 	</form:form>
</body>
</html>