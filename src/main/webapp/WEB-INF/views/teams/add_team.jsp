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
    <form:errors path="user.*" cssClass="error" />

    <form:form action="${pageContext.request.contextPath}/teams/add"
               modelAttribute="team">

        <form:input path="id" hidden="true"/>

        <label>Name:</label>
        Name : <form:input path="name"/>  <br><br>

        Country :
        <select name="countryId" >
            <c:forEach items="${countries}" var="country">
                <option value="${country.id}" label="${country.name}"> ${country.name} </option>
            </c:forEach>
        </select>
        <br><br>

        players list :
        <select name="playerIds" multiple="multiple">
            <c:forEach items="${players}" var="player">
                <option label="${player.name}" value="${player.id}">${player.name} </option>
            </c:forEach>
        </select>
        <br><br>

        Coach :
        <select name="coachId" >
           <c:forEach items="${coaches}" var="coach">
               <option value="${coach.id}" label="${coach.name}"> ${coach.name} </option>
           </c:forEach>
        </select>
        <br><br>

        <input type="submit" name="submit" value="Add User">

    </form:form>
</body>
</html>

<jsp:include page="../common/footer.jsp" />