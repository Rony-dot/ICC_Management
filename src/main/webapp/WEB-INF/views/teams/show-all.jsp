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
			<th>team Name</th>
			<th>team country</th>
			<th>team coach</th>
			<th>players</th>
			<th>Edit</th>
			<th>Details</th>
		</tr>
		<c:forEach items="${teams}" var="team">
			<tr>
				<th>${ team.id }</th>
				<th>${ team.name }</th>
				<th>${ team.country.name }</th>
				<th>${ team.coach.name }</th>
				<th>
					<c:forEach var="player" items="${team.playerList}">
						${player.userInfo.name}
					</c:forEach>
				</th>

				<th><a href="edit?id=${ team.id }">Edit</a></th>
				<th><a href="details?id=${ team.id }">Details</a></th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp"/>
