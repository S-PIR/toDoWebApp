<%@ taglib uri="/jstl/core" prefix="c"%>
<c:if test="${not empty errorMessage}">
	<div class="error">
		<c:out value="${errorMessage}"/>
	</div> 
</c:if>