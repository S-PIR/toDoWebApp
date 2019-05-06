<%@ page import="by.gsu.epamlab.constants.ConstantsJSP" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="css/style.css"/>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body BGCOLOR="#FDF5E6" class="center">
	<c:if test="${not empty errorMessage}">
		<c:out value="${errorMessage}"/><br>
		<hr>
	</c:if>
	<h2>Fill the form please:</h2>
	<form class="header-form" name="loginForm" method="post" action="<c:url value='/login'/>">
		<input type="text" name=<%= ConstantsJSP.KEY_LOGIN %> placeholder="login"><br><br>
		<input type="password" name=<%= ConstantsJSP.KEY_PASSWORD %> placeholder="password"><br><br>
		<input type="submit" value="Enter">
	</form>
<hr>
</body>
</html>