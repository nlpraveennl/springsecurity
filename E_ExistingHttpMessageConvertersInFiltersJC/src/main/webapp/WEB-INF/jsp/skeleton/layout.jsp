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
<script>
var sessionCheckIntervalInSec = 10;
var timerToDisplay = sessionCheckIntervalInSec * 2; // Display timer if remaining sesssion time lies between 10 sec to 20 sec; 
var sessionCheckIntervalId = setInterval(doAjaxCall, sessionCheckIntervalInSec * 1000);
var timerDisplayIntervalId;
var timerShown = false;

function doAjaxCall() 
{ 
	  $.ajax({
	   type: "GET",
	   url: '<c:url value="/api/sessionValid" />',
	   success: function(data, textStatus, xhr)
	   {
		   timeLeft = parseInt(data);
		   if(timeLeft < (timerToDisplay) && timeLeft >= sessionCheckIntervalInSec)
		   {
			   showTimer(timeLeft + 1);
			   console.log("Timer shown "+ (timeLeft + 1) * 1000)
			   window.clearInterval(sessionCheckIntervalId);
			   sessionCheckIntervalId = setInterval(doAjaxCall, (timeLeft + 1) * 1000);
		   }
		   else if(timerShown && timeLeft > sessionCheckIntervalInSec)
		   {
			   console.log("Session got refreshed when less than "+ timerToDisplay +" seconds left to timeout.(i.e, After displaying timer)");
			   console.log("Hiding timer");
			   hideTimer();
			   window.clearInterval(sessionCheckIntervalId);
			   sessionCheckIntervalId = setInterval(doAjaxCall, sessionCheckIntervalInSec * 1000);
		   }
		   else if(timeLeft <= 0)
		   {
			   window.clearInterval(sessionCheckIntervalId);
			   location.href =  '<c:url value="/logout?expired=true" />';
		   }
		   
	   }
	 });
}

function showTimer(timeLeft)
{
	$('#sessionTimeRemaining').show();
	$('#sessionTimeRemainingBadge').html(timeLeft--);
	timerDisplayIntervalId = setInterval(function(){
		$('#sessionTimeRemainingBadge').html(timeLeft--);
	}, 1000);
	timerShown = true;
}

function hideTimer()
{
	window.clearInterval(timerDisplayIntervalId);
	$('#sessionTimeRemaining').hide();
	timerShown = false;
}

function ajaxSessionRefresh() 
{ 
	  $.ajax({
	   type: "GET",
	   url: '<c:url value="/keepSessionAlive" />',
	   success: function(data, textStatus, xhr)
	   {
		   window.clearInterval(timerDisplayIntervalId);
		   $('#sessionTimeRemaining').hide();
	   	   console.log(data);			   
	   }
	 });
}
</script>
</html>
