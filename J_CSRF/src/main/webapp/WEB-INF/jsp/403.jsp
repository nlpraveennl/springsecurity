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
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.bundle.min.js" />" ></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />" >
	<link rel="stylesheet" href="<c:url value="/resources/css/app.css" />" >
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Dash board</title>
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
				<div style="background-size: 100% 100%; margin-left: -15px; margin-right: -15px; background-repeat:no-repeat; 
				height:100%; background-image: url('<c:url value="/resources/imgs/access-denied.png" />');">				
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