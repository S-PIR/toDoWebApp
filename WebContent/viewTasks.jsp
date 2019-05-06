<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<link href="css/style.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="js/javaScripts.js"></script>

</head>
<body>
	<h2>List of tasks</h2>
	<h3 class="title">${section}</h3>
	<c:choose>
		<c:when test="${not empty tasks}">
			<form action="<c:url value='/editTask'/>" name="formEdit" method="post">
				<input type="hidden" name="paramEdit" value="">
				<input type="hidden" name="section" value="${section}">
				<table class="tasks">
					<tr>
						<td width="75">#</td>
						<td>Task</td>
						<c:choose>
								<c:when test="${section eq 'TODAY'}"/>
								<c:when test="${section eq 'TOMORROW'}"/>
								<c:otherwise>
									<td width="100">Date</td>
								</c:otherwise>
						</c:choose>
						<td width="75">Edit task</td>
						<c:choose>
							<c:when test="${section eq 'RECYCLE_BIN'}"/>
							<c:otherwise>
								<td>Operation with file</td>
							</c:otherwise>
						</c:choose>
					</tr>
					
					<c:forEach items="${tasks}" var="task">
						<tr>
							<td>
								<input type="checkbox" name="checkEditTask" value='<c:out value="${task.id}"/>'>
							</td>
							</form>
							<td align="left" width="300">
								<c:out value="${task.contentTask}"/>
							</td>
							<c:choose>
								<c:when test="${section eq 'TODAY'}"/>
								<c:when test="${section eq 'TOMORROW'}"/>
								<c:otherwise>
									<td width="100"><c:out value="${task.date}"/></td>
								</c:otherwise>
							</c:choose>
							<td width="80">
								<!-- <input type="button" value="Edit" onclick="JavaScript:sendToEditJsp('${task.id}', '${task.contentTask}')">  -->
								<!--<a href="editTask.jsp?date=${task.date}&id=${task.id}&content=${task.contentTask}">Edit</a>  -->
								<!-- <a href="<c:url value='/editTask.jsp'>
										<c:param name='date' value='${task.date}'/>
										<c:param name='id' value='${task.id}'/>
										<c:param name='content' value='${task.contentTask}'/>
									</c:url>"> 
								Edit
								</a> -->
								<form action="<c:url value='/editTask.jsp'/>" method="post" name="editTask">
									<input type="hidden" name="date" value="${task.date}">
									<input type="hidden" name="id" value="${task.id}">
									<input type="hidden" name="content" value="${task.contentTask}">
									<input type="submit" name="Edit task" value="Edit">
								</form>
							</td>
						
						<%@ include file="editFile.jsp" %>
						<!-- <jsp:include page="editFile.jsp"/> -->
						</tr>
					</c:forEach>
				
				</table><br>
			
				
			<!-- %@ include file="menuEditTask.jsp --> 
			<jsp:include page="menuEditTask.jsp"/>
				
		</c:when>
		<c:otherwise>
			<h3 class="error">There are no tasks.</h3>
			<c:if test="${(section eq 'TODAY') or (section eq 'TOMORROW') or (section eq 'SOMEDAY')}">
				<h3><a href="addTask.jsp?section=${section}">Add task</a></h3>
			</c:if>
		</c:otherwise>
	</c:choose><hr>
</body>
</html>