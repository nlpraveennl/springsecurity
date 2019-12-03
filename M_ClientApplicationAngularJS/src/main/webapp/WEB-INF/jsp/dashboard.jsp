<%@page session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script>
		var contextPath = "${pageContext.request.contextPath}";
	</script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.4.1.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.bundle.min.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/angular.min.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/angular-route.min.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/app/app.js" />" ></script>

	<!-- Services -->
	<script type="text/javascript" src="<c:url value="/resources/app/services/UserDetailsService.js" />" ></script>
			
	<!-- Controllers -->
	<script type="text/javascript" src="<c:url value="/resources/app/controllers/DashboardController.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/app/controllers/UsersListController.js" />" ></script>
	
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}<spring:theme code='styleSheet'/>" type="text/css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css" type="text/css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Title</title>
</head>
<body ng-app="sampleApp">
	<div class="container-fluid wrapper">
		<div class="row header">
			<jsp:include page="template/header.jsp"></jsp:include>
		</div>
		<div class="row content">
			<div class="col-md-2 col-sm-2 col-xs-2 sidebar">
				<jsp:include page="template/menu.jsp"></jsp:include>
			</div>
			<div class="col-md-10 col-sm-10 col-xs-10">
				<ng-view></ng-view>
			</div>
		</div>
		
		<div class="row footer">
			<jsp:include page="template/footer.jsp"></jsp:include>
		</div>
	</div>
</body>
<script type="text/javascript" src="<c:url value="/resources/app/controllers/DashboardController.js" />" ></script>
</html>
