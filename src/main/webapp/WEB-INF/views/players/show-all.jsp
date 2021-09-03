<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<jsp:include page="../common/header.jsp"/>
<title>${pageTitle}</title>
<body>
	<h1>${pageTitle}</h1>
	<table class="table table-hover" >
		<tr>
			<th>ID</th>
			<th>Player Name</th>
			<th>Player team</th>
			<th>Player is captain</th>
			<th>Player expertise</th>
			<th>Player status</th>
			<th>Edit</th>
			<th>Details</th>
		</tr>
		<c:forEach items="${players}" var="player">
			<tr>
				<th>${ player.id }</th>
				<th>${ player.name }</th>
				<th>${ player.team }</th>
				<th>${ player.isCaptain }</th>
				<th>${ player.expertise }</th>
				<th>${ player.status }</th>
				<th><a href="edit?id=${ player.id }">Edit</a></th>
				<th><a href="details?id=${ player.id }">Details</a></th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp"/>
