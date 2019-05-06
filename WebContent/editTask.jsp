<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="css/style.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="js/javaScripts.js"></script>
</head>
<body BGCOLOR="#FDF5E6" class="center">
	<c:if test="${not empty errorMessage}">
		<c:out value="${errorMessage}"/><hr>
	</c:if>
	<form action="<c:url value='/editContent'/>" method="post" name="formEdit">
		<input type="hidden" name="paramEdit" value="EDIT">
		<input type="hidden" name="id" value="${param.id}">
		<p>Edit your task, please:</p>
		<p>Task: <textarea rows="5" cols="45" name="contentTask">${param.content}</textarea></p>
		<p>Date: <input type="date" name="dateTask" value="${param.date}" placeholder="${param.date}"></p>
		<input type="submit" name="Edit task">
	</form>
	
</body>
</html>