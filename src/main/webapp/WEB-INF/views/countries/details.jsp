<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14/08/2021
  Time: 11:46 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${pageTitle}}</title>
</head>
<body>
<h1>${pageTitle}</h1>
<div class="div-12">
   <h1>details page </h1>
    <h1>Country name is ${country.name}</h1>
    <p>Managing director ${country.managingDirector.name}</p>

    <p>Player list</p>
    <c:forEach var="player" items="${country.playerList}">
          <p> ${player.name} </p>
    </c:forEach>

    <br><br>
</div>
</body>
</html>
