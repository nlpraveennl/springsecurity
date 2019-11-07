<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<br>
<span style="font-size:20px"><spring:message code="app.userlist.userlist.label"/></span> 
<span style="float:right">
	<a href="${pageContext.request.contextPath}/app/admin/user-details">
		<spring:message code="app.menu.adduser.label"/>
	</a>
</span>
<hr>
<table class="table" style="font-size:12px">
  <thead>
    <tr>
      <th>#</th>
      <th><spring:message code="app.userlist.firstname.label"/></th>
      <th><spring:message code="app.userlist.lastname.label"/></th>
      <th><spring:message code="app.userlist.email.label"/></th>
      <th><spring:message code="app.userlist.username.label"/></th>
      <th><spring:message code="app.userlist.gender.label"/></th>
      <th><spring:message code="app.userlist.role.label"/></th>
      <th><spring:message code="app.actions.action.label"/></th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${userList}" var="user">
  		<tr>
	      <th>${user.id }</th>
	      <td>${user.firstName }</td>
	      <td>${user.lastName }</td>
	      <td>${user.email }</td>
	      <td>${user.userName }</td>
	      <td>${user.gender }</td>
	      <td>${user.roleStr }</td>
	      <td>
	      	<a href="<c:url value="/app/user/userdetails/${user.id}" />">
	      		<spring:message code="app.actions.edit.label"/>
	      	</a>
	      </td>
	    </tr>
  	</c:forEach>
  </tbody>
</table>