<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.4.1.js" />" ></script>
		<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
	</head>
	
	<body>
		<div class="container-fluid">
			Navigate to >> <a href='<c:url  value="/app/user/dashboard"/>'>Dashboard</a>
		</div>
	</body>
	<script>
	$(document).ready(function(){
		$.ajax({
		   type: "GET",
		   url: '<c:url value="/app/user/dashboard" />',
		   success: function(data, textStatus, xhr)
		   {
		   	   console.log(data);			   
		   },
		   beforeSend: function (xhr) {
			    xhr.setRequestHeader ("Authorization", "Basic " + btoa("invaliduseranme" + ":" + "invalidpassword"));
		   }
		});
	});
	</script>
</html>
