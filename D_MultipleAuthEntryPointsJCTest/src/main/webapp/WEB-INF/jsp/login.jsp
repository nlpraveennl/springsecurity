<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<body>
		<form:form method="post" action="login" class="form-horizontal" modelAttribute="login" >
		  <p style="font-variant-caps: all-petite-caps;">Login Here</p>
	   		UserName : <form:input path="username" type="text"  style="width:100%"/><br>
	    	Password : <form:password path="password" style="width:100%"/><br>
	      <button type="submit" style="font-variant-caps: all-petite-caps;">
	      	Login
	      </button>
		</form:form>	
	</body>
</html>