<%@page import="by.gsu.epamlab.constants.ConstantsJSP"%>
<%@page import="by.gsu.epamlab.constants.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<link href="css/style.css" type="text/css" rel="stylesheet"/>
</head>
<body class="center" BGCOLOR="#FDF5E6">
	<jsp:include page="error.jsp"/>
	<form name="registrationForm" action="<c:url value='/registrate'/>" method="post">
		<h2>Fill in the form please:</h2>
		<input type="text" name="<%= ConstantsJSP.KEY_LOGIN %>" placeholder="Login"><br>
		<input type="text" name="<%= ConstantsJSP.KEY_NAME %>" placeholder="Name"><br>
		<input type="text" name="<%= ConstantsJSP.KEY_EMAIL %>" placeholder="Email"><br>
		<input type="password" name="<%= ConstantsJSP.KEY_PASSWORD %>" placeholder="Password"><br>
		<input type="password" name="<%= ConstantsJSP.KEY_REP_PASSWORD %>" placeholder="Repeat password"><br><br>
		<input type="submit" value="Registrate">
	</form>
</body>
</html>