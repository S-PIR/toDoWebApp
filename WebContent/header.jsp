<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/style.css" type="text/css" rel="stylesheet"/>
<title>header</title>
</head>
<body>
	<table class="table">
		<c:choose>
			<c:when test="${not empty user}">
				<tr>
					<td><c:out value="User: ${user.login}" /></td>
					<td><a href='<c:url value="/logout"/>'>Logout</a></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td>User: guest</td>
					<td><a href='<c:url value="/login.jsp"/>'>Login</a></td>
					<td><a href='<c:url value="/registrate.jsp"/>'>Registration</a></td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<hr>
	<jsp:include page="error.jsp" />
</body>
</html>