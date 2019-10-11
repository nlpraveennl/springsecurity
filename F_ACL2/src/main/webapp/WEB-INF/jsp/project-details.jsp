<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page session="true"%>

<c:url var="addProject" value="/app/user/add-project" />
<c:url var="modifyProject" value="/app/user/modify-project" />

<br>
<c:if test="${empty update}">
	<span style="font-size:20px"><spring:message code="app.projectdetails.addproject.label" /></span> 
	<hr>
	<form:form action="${addProject}" modelAttribute="project">
		<table class="table table-bordered" style="font-size:12px">
		    <tr><th><spring:message code="app.projectdetails.name.label" /></th> <td><form:input path="name"/></td></tr>
		    <tr><th><spring:message code="app.projectdetails.description.label" /></th> <td><form:input path="description"/></td></tr>
		    <tr>
		    	<th><spring:message code="app.projectdetails.manager.label" /></th>
		    	<td>
		    		<form:select path="mgr" multiple="false">
					    <form:options items="${users}" itemValue="id" itemLabel="userName"/>
					</form:select>
				</td>
		    </tr>
		    
		</table>
		<form:button value="submit"><spring:message code="app.actions.save.label" /></form:button>
	</form:form>
</c:if>
<c:if test="${not empty update}">
	<span style="font-size:20px"><spring:message code="app.projectdetails.updateproject.label" /></span> 
	<hr>
	<form:form action="${modifyProject}" modelAttribute="project">
		<table class="table table-bordered" style="font-size:12px">
			<form:hidden path="id"/>
		    <tr><th><spring:message code="app.projectdetails.name.label" /></th> <td><form:input path="name"/></td></tr>
		    <tr><th><spring:message code="app.projectdetails.description.label" /></th> <td><form:input path="description"/></td></tr>
		    <tr>
		    	<th><spring:message code="app.projectdetails.manager.label" /></th> 
		    	<td>
		    		<form:select path="mgr" multiple="false">
					    <form:options items="${users}" itemValue="id" itemLabel="userName"/>
					</form:select>
				</td>
		    </tr>
		</table>
		<form:button value="submit"><spring:message code="app.actions.update.label" /></form:button>
	</form:form>
</c:if>
