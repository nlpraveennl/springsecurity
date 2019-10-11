<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
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
	<link rel="stylesheet" href="<spring:theme code='styleSheet'/>" type="text/css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
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
	</style>
</head>
<body>
	<div class="container-fluid wrapper">
		<div class="row header">
			<tiles:insertAttribute name="header" />
		</div>
		<div class="row content">
			<div class="col-md-2 col-sm-2 col-xs-2 sidebar">
				<tiles:insertAttribute name="menu" />
			</div>
			<div class="col-md-10 col-sm-10 col-xs-10">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		
		<div class="row footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>
