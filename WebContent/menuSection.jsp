<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/javaScripts.js"></script>
</head>
<body>
	<form name="sects" method="get" action="<c:url value='/task'/>">
		<input name="section" type="hidden" value="">
		<table class="menu">
			<tr>
				<td><a href="JavaScript:sendSection('TODAY')">Today</a></td>
				<td><a href="JavaScript:sendSection('TOMORROW')">Tomorrow</a></td>
				<td><a href="JavaScript:sendSection('SOMEDAY')">Someday</a></td>
				<td><a href="JavaScript:sendSection('FIXED')">Fixed</a></td>
				<td><a href="JavaScript:sendSection('RECYCLE_BIN')">Recycle	Bin</a></td>
			</tr>
		</table>
	
	</form>
</body>
</html>