<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24/08/2021
  Time: 12:25 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp" />
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>All users list</h1>
<c:forEach items="${users}" var="user">
    <h1>${user.name}</h1>
</c:forEach>
</body>
</html>

<jsp:include page="../common/footer.jsp" />
