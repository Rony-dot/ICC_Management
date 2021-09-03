<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<title>${pageTitle}</title>
<!-- GLOBAL HEADER -->
<jsp:include page="../common/header.jsp"/>
<body>

<h1>${pageTitle}</h1>

<div class="row">
	<div class="col-6">
		<form:form action="${pageContext.request.contextPath }/countries/update"
				   modelAttribute="country">

			<div class="col-12">
				<div class="form-group">
					<label>Id</label>
					<form:input path="id" value="${id}" type="hidden" ></form:input>
				</div>
			</div>
			<div class="col-12">
				<div class="form-group">
					<label>Name</label>
					<form:input path="name"></form:input>
				</div>
			</div>
			<div class="col-12">
				<div class="form-group">
					<label>Managing Director of the country</label>
					<select class="form-control" name="idMD">
						<c:forEach items="${managingDirectors}" var="md">
							<option label="${md.name}" value="${md.id}"
								${country.managingDirector == md? "Selected" : "" }  />
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="col-12">
				<div class="form-group">
					<label>Players of the country</label>
					<select name="playerIds" multiple="multiple" >
						<c:forEach items="${players}" var="player">
							<option value="${player.id}">${player.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="col-12">
				<input type="submit" name="submit" value="Update Country">
			</div>
		</form:form>
	</div>
</div>
</body>
</html>
<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp"/>
