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
    <h1>My name is ${user.name}</h1>
    <p>email ${user.email}</p>
    <p>age ${user.age}</p>
    <p>password ${user.password}</p>
    <p>homeTown ${user.homeTown}</p>
    <p>mobile ${user.mobile}</p>
    <p>gender ${user.gender}</p>
    <c:forEach items="${user.salutation}" var="salutation">
        <p>salutation ${salutation}</p>
    </c:forEach>
    <%--        <p>dateOfBirth <fmt:formatDate value="${user.dob}" pattern="yyyy-MM-dd"/> </p>--%>
    <p>date of Birth: ${user.dateOfBirth}</p>
    <p>Role: ${user.role}</p>
    <br><br>
</div>
</body>
</html>
