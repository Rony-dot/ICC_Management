<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13/09/2021
  Time: 10:46 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success page</title>
</head>
<body>
    <h1>Success Page! </h1>
<c:if test="${msg ne null}">
    <h2>${msg}</h2>
</c:if>

</body>
</html>
