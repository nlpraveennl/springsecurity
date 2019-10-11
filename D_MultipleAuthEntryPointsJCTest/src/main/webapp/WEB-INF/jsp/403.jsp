<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.4.1.js" />" ></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Forbidden</title>
	</head>
	<body>
		<div style="height:630px">
			<div style="background-size: 100% 100%; background-repeat:no-repeat; 
				height:100%; background-image: url('<c:url value="/resources/imgs/access-denied.png" />');">				
			</div>		
		</div>
	</body>
</html>