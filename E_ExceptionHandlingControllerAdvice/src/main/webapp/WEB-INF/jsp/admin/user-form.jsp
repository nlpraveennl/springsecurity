<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-6">
	<h2 style="font-variant-caps: all-petite-caps;"><spring:message code="app.menu.adduser.label"/></h2>
	<hr>
	<form:form method="post" action="${pageContext.request.contextPath}/app/admin/add-user" modelAttribute="user">
		<table>
			<tr>
				<td style="width:200px">
					<form:label path="firstName"><spring:message code="app.userform.firstname.label"/></form:label>
				</td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td>
					<form:label path="lastName"><spring:message code="app.userform.lastname.label"/></form:label>
				</td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td>
					<form:label path="email"><spring:message code="app.userform.email.label"/></form:label>
				</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>
					<form:label path="gender"><spring:message code="app.userform.gender.label"/></form:label>
				</td>
				<td>
					<form:radiobutton path="gender" value="MALE" /><spring:message code="app.userform.gender.male.label"/> 
					<form:radiobutton path="gender" value="FEMALE" /><spring:message code="app.userform.gender.female.label"/>
				</td>
			</tr>
			<tr>
				<td>
					<form:label path="userName"><spring:message code="app.userform.username.label"/></form:label>
				</td>
				<td><form:input path="userName" /></td>
			</tr>
			<tr>
				<td>
					<form:label path="password"><spring:message code="app.userform.password.label"/></form:label>
				</td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td>
					<form:label path="roleId"><spring:message code="app.userform.role.label"/></form:label>
				</td>
				<%-- <td><form:radiobuttons path="role" items="${roles}"/></td> --%>
				<td>
					<c:forEach items="${roleList}" var="roleObj">
						<form:radiobutton path="roleId" value="${roleObj.id}" /> ${roleObj.name}
					</c:forEach>
				</td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit" value="<spring:message code="app.userform.adduser.label"/>" /></td>
			</tr>
		</table>
	</form:form>
	<c:if test="${not empty msg}">
		<script>
			alert("${msg}");
		</script>
	</c:if>
</div>

