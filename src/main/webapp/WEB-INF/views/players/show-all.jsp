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
			<th>Country Name</th>
			<th>Managing Director</th>
			<th>Players</th>
			<th>Edit</th>
			<th>Details</th>
		</tr>
		<c:forEach items="${countries}" var="country">
			<tr>
				<th>${ country.id }</th>
				<th>${ country.name }</th>
				<th>${ country.managingDirector.name }</th>
				<th>

					<c:forEach var="player" items="${country.playerList}">
						${player.name}
					</c:forEach>
				</th>
				<th><a href="edit?id=${ country.id }">Edit</a></th>
				<th><a href="details?id=${ country.id }">Details</a></th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp"/>
