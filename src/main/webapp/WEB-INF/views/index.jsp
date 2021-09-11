<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22/08/2021
  Time: 9:11 pm
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="common/header.jsp" />

<%--<img class="rounded-circle border-dark " src="${pageContext.request.contextPath}/images/Topology.png">--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1 class="text-center text-danger text-uppercase">this is home page</h1>

<c:if test="${msg ne null}">
    <h2>${msg}</h2>
</c:if>

<a class="text-primary" href="/users/all" a> all user </a>
<a class="text-primary" href="/users/add" a> add user </a>



</body>
</html>

<jsp:include page="common/footer.jsp" />
