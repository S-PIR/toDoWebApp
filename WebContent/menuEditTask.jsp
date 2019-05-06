<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/jstl/core" prefix="c"%>  
<html>
<head>
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/javaScripts.js"></script>
</head>
<body>
		<table class="menu">
			<tr>
				<c:if test="${(section eq 'TODAY') or (section eq 'TOMORROW') or (section eq 'SOMEDAY')}">
					<c:set var="section" value="${section}" scope="request"/>
					<td><a href="addTask.jsp?section=${section}">Add task</a></td>
					<td><a href="JavaScript:sendEditFunction('FIX')">Fix task</a></td>
					<td><a href="JavaScript:sendEditFunction('RECYCLE')">Recycle task</a></td>
				</c:if>
				<c:if test="${section eq 'RECYCLE_BIN'}">
		            <td><a href="JavaScript:sendEditFunction('REMOVE')">Remove task</a></td>
		            <td><a href="JavaScript:sendEditFunction('RESTORE')">Restore task</a></td>
	       		</c:if>
	       		<c:if test="${section eq 'FIXED'}">
		            <td><a href="JavaScript:sendEditFunction('UNFIX')">Unfix task</a></td>
	       			<td><a href="JavaScript:sendEditFunction('RECYCLE')">Recycle task</a></td>
	       		</c:if>
			</tr>
		</table>
	
</body>
</html>