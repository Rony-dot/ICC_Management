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
			<th>Event Name</th>
			<th>Event start time</th>
			<th>Event end time</th>
			<th>Team 1</th>
			<th>Team 2</th>
			<th>Umpire List</th>
			<th>score</th>
			<th>Edit</th>
			<th>Details</th>
		</tr>
		<c:forEach items="${events}" var="event">
			<tr>
				<th>${ event.id }</th>
				<th>${ event.name }</th>
				<th>${ event.startDateTime }</th>
				<th>${ event.endDateTime }</th>
				<th>${ event.eventType }</th>
				<th>${ event.team1 }</th>
				<th>${ event.team2 }</th>
				<th>
					<c:forEach items="${event.umpires}" var="umpire">
						${umpire.name},&nbsp
					</c:forEach>
				</th>
				<th><a href="edit?id=${ event.id }">Edit</a></th>
				<th><a href="details?id=${ event.id }">Details</a></th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp"/>
