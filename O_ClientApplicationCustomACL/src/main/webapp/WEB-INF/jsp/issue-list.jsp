<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page session="true"%>

<br>
<span style="font-size:20px"><spring:message code="app.issue.list.label"/></span>
<span style="float:right">
	<a href="<c:url value="/app/user/issue-details"/>">
		<spring:message code="app.issue.addissue.label"/>
	</a>
</span>
<hr>
<table class="table table-bordered" style="font-size:12px">
  <tr>
  	<th>#</th>
    <th><spring:message code="app.issue.name.label"/></th>
    <th><spring:message code="app.issue.summary.label"/></th>
    <th><spring:message code="app.issue.description.label"/></th>
    <th><spring:message code="app.issue.issuetype.label"/></th>
    <th><spring:message code="app.issue.reporter.label"/></th>
    <th><spring:message code="app.issue.assignee.label"/></th>
    <th><spring:message code="app.issue.project.label"/></th>
    <th><spring:message code="app.actions.action.label"/></th>
  </tr>
  <c:forEach items="${issues}" var="issue" varStatus="loop">
  	<tr>
  		<td>${ loop.count }</td>
	    <td>${ issue.name }</td>
	    <td>${ issue.summary }</td>
	    <td>${ issue.description }</td>
	    <td>${ issue.issueType }</td>
	    <td>${ issue.reporterStr }</td>
	    <td>${ issue.assigneeStr }</td>
	    <td>${ issue.projectStr }</td>
	    <td>
	      	<a href="<c:url value="/app/user/issue-details/${issue.id}" />">
	      		<spring:message code="app.actions.edit.label"/>
	      	</a>
	    </td>
  	</tr>
  </c:forEach>
</table>