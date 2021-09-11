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
<%--    <c:choose>--%>
<%--        <c:when test="${errorMsg != null}" >--%>
<%--            <div class="alert alert-danger" role="alert">--%>
<%--                <p>error msg</p>--%>
<%--                    ${errorMsg}--%>

<%--            </div>--%>
<%--        </c:when>--%>
<%--        <c:when test="${error != null}" >--%>
<%--            <div class="alert alert-danger" role="alert">--%>
<%--                <p>error</p>--%>
<%--                ${error}--%>

<%--            </div>--%>
<%--        </c:when>--%>
<%--        <c:otherwise>--%>
<%--            you are good to go!--%>

<%--        </c:otherwise>--%>
<%--    </c:choose>--%>


<%--            // error of the form--%>
    <form:errors path="player.*" cssClass="error" />

    <form:form action="${pageContext.request.contextPath}/events/add"
               modelAttribute="event">

<%--        // alternative error --%>
<%--        <form:errors path="*" cssClass="error" element="div" />--%>

        <form:input path="id" hidden="true"/>

        <label>Name:</label>
        <form:input path="name"/> <br><br>
        event start time:
        <form:input path="startDateTime" type="date"/> <br><br>
        event end time :
        <form:input path="endDateTime" type="date"/> <br><br>

        event type:
        <form:select path="eventType" >
            <form:options items="${eventTypes}"/>
        </form:select>
        <br><br>

        Team 1:
        <select name="idTeam1" >
            <c:forEach items="${teams1}" var="team1">
                <option value="${team1.id}">${team1.name}</option>
            </c:forEach>
        </select>
        <br><br>

        Team 2:
        <select name="idTeam2" >
            <c:forEach items="${teams2}" var="team2">
                <option value="${team2.id}">${team2.name}</option>
            </c:forEach>
        </select>
        <br><br>

        Select existing Umpires :
        <select name="umpireIds" multiple="multiple" >
            <c:forEach items="${umpires}" var="umpire">
                <option value="${umpire.id}" label="${umpire.name}"> ${umpire.name} </option>
            </c:forEach>
        </select>

        <a href="${pageContext.request.contextPath}/users/add">Or Add an Umpire</a>
        <br><br>

        Score
        <form:input path="score" type="number"/>
        <br><br>
        <input type="submit" name="submit" value="Add User">

    </form:form>
</body>
</html>

<jsp:include page="../common/footer.jsp" />

<%--String referer = request.getHeader("Referer");--%>
<%--return "redirect:"+ referer;--%>