<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:url var="addIssue" value="/app/user/add-issue" />
<c:url var="modifyIssue" value="/app/user/modify-issue" />
<c:url var="addComment" value="/app/user/issue/addComment" />

<spring:message var="textAreaPlaceHolder" code="app.issuedetails.comment.placeholder.label" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.4.1.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.bundle.min.js" />" ></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}<spring:theme code='styleSheet'/>" type="text/css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app.css" type="text/css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Title</title>
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
						<h2 style="font-variant-caps: all-petite-caps;"><spring:message code="app.issuedetails.add.label"/></h2>
						<hr>
						<form:form method="post" action="${addIssue}" modelAttribute="issue">
							<table class="table table-bordered" style="font-size:12px">
								<tr>
									<th><spring:message code="app.issue.name.label"/></th>
									<td><form:input path="name"/></td>
								</tr>
							    <tr>
							    	<th><spring:message code="app.issue.summary.label"/></th>
							    	<td><form:input path="summary"/></td>
							    </tr>
							    <tr>
							    	<th><spring:message code="app.issue.description.label"/></th>
							    	<td><form:input path="description"/></td>
							    </tr>
							    <tr>
							    	<th><spring:message code="app.issue.issuetype.label"/></th>
							    	<td><form:input path="issueType"/></td>
							    </tr>
							    <tr>
							    	<th><spring:message code="app.issue.reporter.label"/></th>
							    	<td>
							    		<form:select path="reporter" multiple="false">
										    <form:options items="${users}" itemValue="id" itemLabel="userName"/>
										</form:select>
							    	</td>
							    </tr>
							    <tr>
							    	<th><spring:message code="app.issue.assignee.label"/></th>
							    	<td>
								    	<form:select path="assignee" multiple="false">
										    <form:options items="${users}" itemValue="id" itemLabel="userName"/>
										</form:select>
							    	</td>
							    </tr>
							    <tr>
							    	<th><spring:message code="app.issue.project.label"/></th>
							    	<td>
								    	<form:select path="projectId" multiple="false">
										    <form:options items="${projects}" itemValue="id" itemLabel="name"/>
										</form:select>
							    	</td>
							    </tr>
							    <tr>
							    	<th></th>
							    	<td>
							    		<form:button calss="btn btn-sm btn-outline-primary" value="submit">
							    			<spring:message code="app.actions.save.label" />
							    		</form:button>
							    	</td>
							    </tr>
							</table>
						</form:form>
					</c:if>
					
					<c:if test="${not empty update}">
						<h2 style="font-variant-caps: all-petite-caps;"><spring:message code="app.issuedetails.edit.label"/></h2>
						<hr>
						<form:form method="post" action="${modifyIssue}" modelAttribute="issue">
							<form:hidden path="id"/>
							<table class="table table-bordered" style="font-size:12px">
								<tr>
									<th><spring:message code="app.issue.name.label"/></th>
									<td><form:input path="name"/></td>
								</tr>
							    <tr>
							    	<th><spring:message code="app.issue.summary.label"/></th>
							    	<td><form:input path="summary"/></td>
							    </tr>
							    <tr>
							    	<th><spring:message code="app.issue.description.label"/></th>
							    	<td><form:input path="description"/></td>
							    </tr>
							    <tr>
							    	<th><spring:message code="app.issue.issuetype.label"/></th>
							    	<td><form:input path="issueType"/></td>
							    </tr>
							    <tr>
							    	<th><spring:message code="app.issue.reporter.label"/></th>
							    	<td>
							    		<form:select path="reporter" multiple="false">
										    <form:options items="${users}" itemValue="id" itemLabel="userName"/>
										</form:select>
							    	</td>
							    </tr>
							    <tr>
							    	<th><spring:message code="app.issue.assignee.label"/></th>
							    	<td>
								    	<form:select path="assignee" multiple="false">
										    <form:options items="${users}" itemValue="id" itemLabel="userName"/>
										</form:select>
							    	</td>
							    </tr>
							    <tr>
							    	<th><spring:message code="app.issue.project.label"/></th>
							    	<td>
								    	<form:select path="projectId" multiple="false">
										    <form:options items="${projects}" itemValue="id" itemLabel="name"/>
										</form:select>
							    	</td>
							    </tr>
							    <tr>
							    	<th></th>
							    	<td>
							    		<form:button class="btn btn-sm btn-outline-primary" value="submit">
							    			<spring:message code="app.actions.update.label" />
							    		</form:button>
							    	</td>
							    </tr>
							</table>
						</form:form>
					</c:if>
				</div>
				
				<c:if test="${not empty update}">
					<div style="width: 100%;padding-right: 15px;padding-left: 15px;margin-right: auto;margin-left: auto;">
						<div class="row">
							<span style="font-weight: bold; font-size:20px">
								<spring:message code="app.issuedetails.comment.comments.label" />
							</span>
						</div>
						<table class="table table-sm" style="font-size:14px">
							<c:forEach items="${commentsWrapper.commentList}" var="comment" varStatus="loop">
								<tr>
									<th style="font-variant-caps: all-petite-caps; width: 100px">
										${comment.userStr}
									</th>
									<td>
										${comment.message}
									</td>
									<td>
										<a href='<c:url value="/app/user/issuecomment/delete/${comment.issueId}/${comment.id}"/>'>
											<button class="btn btn-sm btn-outline-danger">
												<spring:message code="app.actions.delete.label" />	
											</button>
										</a>
									</td>
								</tr>
							</c:forEach>
						</table>
						<div class="row">
							<form:form action="${addComment}" modelAttribute="issueComment">
								<form:hidden path="id"/>
								<form:hidden path="issueId"/>
								<form:hidden path="userId"/>
								<form:textarea path="message" style="width:800px" placeholder="${textAreaPlaceHolder}"/><br>
								<form:button class="btn btn-sm btn-outline-primary" type="submit">
									<spring:message code="app.issuedetails.comment.addcomment.label" />
								</form:button>
							</form:form>
						</div>
						<br><br>
					</div>
				</c:if>
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
