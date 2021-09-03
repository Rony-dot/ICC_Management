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
		<form:form action="${pageContext.request.contextPath }/all"
				   modelAttribute="user">

			<div class="col-12">
				<div class="form-group">
					<label>Id</label>
					<form:input path="id" disabled="true"></form:input>
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
					<label>Age</label>
					<form:input path="age"></form:input>
				</div>
			</div>
			<div class="col-12">
				<div class="form-group">
					<label>Gender</label>
					<form:radiobuttons path="gender" items="${genders}"></form:radiobuttons>
				</div>
			</div>
			<div class="col-12">
				<div class="form-group">
					<label>Email</label>
					<form:input path="email"></form:input>
				</div>
			</div>
			<div class="col-12">
				<div class="form-group">
					<label>Email</label>
					<form:input path="password" type="password"></form:input>
				</div>
			</div>
			<div class="col-12">
				<div class="form-group">
					<label>Date of Birth</label>
					<form:input path="dateOfBirth" type="date" ></form:input>
				</div>
			</div>
			<div class="col-12">
				<div class="form-group">
					<label>Salutation</label>
					<form:checkboxes path="salutation"  items="${salutations}"></form:checkboxes>
				</div>
			</div>
			<div class="col-12">
				<div class="form-group">
					<label>Mobile</label>
					<form:input path="mobile"></form:input>
				</div>
			</div>
			<div class="col-12">
				<div class="form-group">
					<label>Home Town</label>
					<select class="form-control" name="homeTown">
						<c:forEach items="${homeTowns}" var="homeTown">
							<option label="${homeTown}" value="${homeTown}"
								${user.homeTown == homeTown? "Selected" : "" }  />
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="col-12" hidden>
				<div class="form-group">
					<label>Select a country</label>
					<select class="form-control" name="countryCode">
						<option label="Please Select a country" disabled></option>
						<c:forEach items="${countries}" var="country">
							<option label="${country.countryName}"
									value="${country.countryCode}"
								<%--									selected="${student.country.countryCode == country.countryCode}">--%>
								${student.country.countryCode == country.countryCode? "selected" : ''}>

							</option>

						</c:forEach>
					</select>
						<%--					<form:select class="form-control" path="countryCode">--%>
						<%--						<form:options items="${countries}" itemLabel="countryName" itemValue="countryCode"></form:options>--%>
						<%--					</form:select>--%>
				</div>
			</div>
			<div class="col-12">
				<div class="form-group">
						<%--					<label>Select a course</label>--%>
						<%--					<form:select class="form-control" multiple="true" path="courseCodes">--%>
						<%--						<form:options items="${courses}" itemLabel="courseName" itemValue="courseCode"></form:options>--%>
						<%--					</form:select>--%>
				</div>
			</div>
			<div class="col-12">
				<input type="submit" name="submit" value="Update Student">
			</div>
		</form:form>
	</div>
</div>
</body>
</html>
<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp"/>
