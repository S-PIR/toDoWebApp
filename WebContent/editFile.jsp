<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<c:choose>
		<c:when test="${section eq 'RECYCLE_BIN'}"/>
		<c:when test="${task.fileName eq 'no file'}">
			<td>
					<form name="fileForm" action="<c:url value='/editFile'/>" method="post" enctype="multipart/form-data">
						<input type="file" name="file" id="file" width="20"> 
						<input type="hidden" value="${task.id}" name="idTask">
						<input type="hidden" value="${task.fileName}" name="fileName">
						<input type="hidden" value="${section}" name="section">
						<input type="submit" name="fileAction" value="UPLOAD"> 
					</form>
			</td>
		</c:when>
		
		<c:otherwise>
	    	<td>${task.fileName}
	        	<form action="<c:url value='/editFile'/>" method="post" >
	            	<input type="hidden" value="${task.id}" name="idTask">
	                <input type="hidden" value="${task.fileName}" name="fileName">
	                <input type="hidden" value="${section}" name="section">
	                <input type="submit" name="fileAction" value="DELETE">
	                <input type="submit" name="fileAction" value="DOWNLOAD">
	            </form></td>
		</c:otherwise>
	</c:choose>
</body>
</html>