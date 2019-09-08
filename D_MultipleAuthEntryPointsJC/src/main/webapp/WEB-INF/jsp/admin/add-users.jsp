<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-md-12">
		<div class="row" style="margin-top: 5px;">
			<div class="col-md-3" style="font-variant-caps: all-petite-caps; font-size: 25px; font-weight: bolder;">
				<spring:message code="app.menu.addmultipleusers.label"/>
			</div>
			<div class="col-md-2">
				<div onClick="addRow()" class="btn btn-primary">ADD ROW</div>
			</div>
		</div>
		<hr>
		<form:form method="post" action="${pageContext.request.contextPath}/app/admin/add-users" modelAttribute="userListWrapper">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th><spring:message code="app.userform.firstname.label"/></th>
						<th><spring:message code="app.userform.email.label"/></th>
						<th><spring:message code="app.userform.username.label"/></th>
						<th><spring:message code="app.userform.gender.label"/></th>
						<th><spring:message code="app.userform.role.label"/></th>
					</tr>
				</thead>
				<tbody id="tbodyContainer">
					<c:forEach items="${userListWrapper.list}" var="user" varStatus="loop">
						<tr>
							<td><form:input path="list[${loop.index}].firstName" /></td>
							<td><form:input path="list[${loop.index}].email" /></td>
							<td><form:input path="list[${loop.index}].userName" /></td>
							<td>
								<span>
									<form:radiobutton path="list[${loop.index}].gender" value="MALE" /><spring:message code="app.userform.gender.male.label"/>
								</span>
								<span>
									<form:radiobutton path="list[${loop.index}].gender" value="FEMALE" /><spring:message code="app.userform.gender.female.label"/>
								</span>
							</td>
							<td>
								<c:forEach items="${roleList}" var="roleObj">
									<span>
										<form:radiobutton path="list[${loop.index}].roleId" value="${roleObj.id}" /> ${roleObj.name}
									</span>
								</c:forEach>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="offset-11 col-md-1">
				<button type="submit" class="btn btn-primary">SAVE ALL</button>		
			</div>
		</form:form>
		<c:if test="${not empty msg}">
			<script>
				alert("${msg}");
			</script>
		</c:if>
	</div>
</div>

<script>
var currentIndex = 3;
function addRow()
{
	var rowConstructed = constructRow(currentIndex++);
	$("#tbodyContainer").append(rowConstructed);
}

function constructRow(index)
{
	return '<tr>'+
	'<td><input id="list'+ index +'.firstName" name="list['+ index +'].firstName" type="text" value=""/></td>'+
	'<td><input id="list'+ index +'.email" name="list['+ index +'].email" type="text" value=""/></td>'+
	'<td><input id="list'+ index +'.userName" name="list['+ index +'].userName" type="text" value=""/></td>'+
	'<td>'+
		'<span>'+
			'<input id="list'+ index +'.gender1" name="list['+ index +'].gender" type="radio" value="MALE" checked="checked"/>Male'+
		'</span>'+
		'<span>'+
			'<input id="list'+ index +'.gender'+ index +'" name="list['+ index +'].gender" type="radio" value="FEMALE"/>Female'+
		'</span>'+
	'</td>'+
	'<td>'+
		'<span>'+
			'<input id="list'+ index +'.roleId1" name="list['+ index +'].roleId" type="radio" value="1"/> ROLE_ADMIN'+
		'</span>'+
		'<span>'+
			'<input id="list'+ index +'.roleId'+ index +'" name="list['+ index +'].roleId" type="radio" value="'+ index +'" checked="checked"/> ROLE_USER'+
		'</span>'+
		'<span>'+
			'<input id="list'+ index +'.roleId3" name="list['+ index +'].roleId" type="radio" value="3"/> ROLE_APIUSER'+
		'</span>'+
	'</td>'+
'</tr>';
}
</script>
