<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page session="true"%>
<br>
<span style="font-size:20px"><spring:message code="app.project.list.label"/></span> 
<span style="float:right">
	<a href="<c:url value="/app/user/project-details"/>">
		<spring:message code="app.project.addproject.label"/>
	</a>
</span>
<hr>
<table class="table table-bordered" style="font-size:12px">
  <tr>
  	<th>#</th>
    <th><spring:message code="app.project.name.label"/></th>
    <th><spring:message code="app.project.description.label"/></th>
    <th><spring:message code="app.project.manager.label"/></th>
    <th><spring:message code="app.actions.action.label"/></th>
  </tr>
  <c:forEach items="${projects}" var="project" varStatus="loop">
  	<tr>
  		<td>${ loop.count }</td>
	    <td><a href="<c:url value="/app/user/issue-list/${project.id}"/>">${ project.name }</a></td>
	    <td>${ project.description }</td>
	    <td>${ project.mgrStr }</td>
	    <td>
	    	<a href="<c:url value="/app/user/project/${project.id}"/>">
	    		<spring:message code="app.actions.edit.label"/>
	    	</a>
	    </td>
  	</tr>
  </c:forEach>
</table>