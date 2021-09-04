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
			<th>Series Name</th>
			<th>Series start time</th>
			<th>Series end time</th>
			<th>Series Type</th>
			<th>Event list</th>
			<th>Team List</th>
			<th>Edit</th>
			<th>Details</th>
		</tr>
		<c:forEach items="${allSeries}" var="series">
			<tr>
				<th>${ series.id }</th>
				<th>${ series.name }</th>
				<th>${ series.seriesStartDate }</th>
				<th>${ series.seriesEndDate }</th>
				<th>${ series.seriesType }</th>
				<th>
					<c:forEach items="${series.eventList}" var="event">
						${event.name}
					</c:forEach>
				</th>
				<th>
					<c:forEach items="${series.participantTeams}" var="team">
						${team.name}
					</c:forEach>
				</th>
				<th><a href="edit?id=${ series.id }">Edit</a></th>
				<th><a href="details?id=${ series.id }">Details</a></th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp"/>
