<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24/08/2021
  Time: 12:21 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp" />

<html>
<head>
    <title>Title</title>
    <style type="text/css">
        .error {
            color: #ff0000;
        }
        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>
<body>
<c:if test="${not empty errorMsg}">
    <div class="container mt-3">
        <div class="row">
            <div class="col-md-6 mx-auto">
                <div class="alert alert-danger">
                        ${errorMsg}
                </div>
            </div>
        </div>
    </div>
</c:if>

<%--            // error of the form--%>
    <form:errors path="series.*" cssClass="error" />

    <form:form action="${pageContext.request.contextPath}/series/add"
               modelAttribute="series">

<%--        // alternative error --%>
<%--        <form:errors path="*" cssClass="error" element="div" />--%>

        <form:input path="id" hidden="true"/>

        <label>Series Name:</label>
        <form:input path="name"/> <br><br>
        Series start time:
        <form:input path="seriesStartDate" type="date"/> <br><br>
        Series end time :
        <form:input path="seriesEndDate" type="date"/> <br><br>

        Series type:
        <form:select path="seriesType" >
            <form:options items="${seriesTypes}"/>
        </form:select>
        <br><br>

        Event List:
        <select name="eventIds" multiple="multiple" >
            <c:forEach items="${events}" var="event">
                <option value="${event.id}">${event.name}</option>
            </c:forEach>
        </select>
        <br><br>

        Participant Team List:
        <select name="teamIds" multiple="multiple">
            <c:forEach items="${teams}" var="team">
                <option value="${team.id}">${team.name}</option>
            </c:forEach>
        </select>
        <br><br>

        <input type="submit" name="submit" value="Add Series">

    </form:form>
</body>
</html>

<jsp:include page="../common/footer.jsp" />