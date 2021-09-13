<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05/09/2021
  Time: 8:41 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="common/header.jsp"/>
    <title>Login Page</title>
</head>
<body>


    <c:if test="${msg ne null}">
        <div class="container mt-3">
            <div class="row">
                <div class="col-md-6 mx-auto">
                    <div class="alert alert-danger">
                            ${msg}
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <div class="container mt-4 ">
        <div class="row">
<%--            <div class="col-md-3"></div>--%>
            <div class="col-md-6 mx-auto shadow border border-primary">
    <c:if test="${not empty errorMessge}"><div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMessge}</div></c:if>
<%--                <form action="${pageContext.request.contextPath}/login" method="post">--%>
                <form action="${pageContext.request.contextPath}/login-processing" method="post">

<%--    <form action="@{/login-processing}" method="post">--%>
<%--    <form action="/login" method="post">--%>
                    <div class="form-group">
                        <label for="email">Email address</label>
<%--                        <input type="text" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">--%>
                        <input type="text" name="email" class="form-control" id="email"  placeholder="Enter email">
                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                    </div>

                    <button type="submit" class="btn btn-primary">Submit</button>

<%--                    <input type="hidden" name="${_csrf}" value="${_csrf.token}" />--%>
                </form>
            </div>
<%--            <div class="col-md-3"></div>--%>
        </div>
    </div>
</body>
</html>

<!-- GLOBAL FOOTER -->
<jsp:include page="common/footer.jsp"/>