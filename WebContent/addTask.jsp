<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add task</title>
<link href="css/style.css" type="text/css" rel="stylesheet"/>
</head>
<body BGCOLOR="#FDF5E6" class="center">
	<c:if test="${not empty errorMessage}">
		<c:out value="${errorMessage}"/><hr>
	</c:if>
	<form action="<c:url value='/addTask'/>" method="post" name="formEdit">
		<input type="hidden" name="paramEdit" value="ADD">
		<input type="hidden" name="section" value="${param.section}">
		<p>Fill the form, please.</p>
		<p>Task: <textarea rows="10" cols="45" name="contentTask" placeholder="Print your task here..."></textarea></p>
		<c:if test="${(param.section eq 'SOMEDAY')}">
			<p>Date: <input type="date" name="dateTask" placeholder="yyyy-mm-dd"></p>
	    </c:if>
		<input type="submit" name="Add task">
	</form>
</body>
</html>