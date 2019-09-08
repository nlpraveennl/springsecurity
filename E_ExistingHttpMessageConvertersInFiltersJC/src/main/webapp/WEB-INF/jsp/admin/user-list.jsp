<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">First Name</th>
      <th scope="col">Last Name</th>
      <th scope="col">Email</th>
      <th scope="col">User Name</th>
      <th scope="col">Gender</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${userList}" var="user">
  		<tr>
	      <th scope="row">${user.id }</th>
	      <td>${user.firstName }</td>
	      <td>${user.lastName }</td>
	      <td>${user.email }</td>
	      <td>${user.userName }</td>
	      <td>${user.gender }</td>
	    </tr>
  	</c:forEach>
  </tbody>
</table>