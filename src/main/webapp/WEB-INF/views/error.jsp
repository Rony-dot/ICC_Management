<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25/08/2021
  Time: 8:45 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <!-- page content -->
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-md-12">
                    <h1>Resource already exists!</h1>
                    <c:if test="${not empty errorMsg}">
                        <div class="container mt-3">
                            <div class="row">
                                <div class="col-md-6 mx-auto">
                                    <div class="alert alert-danger">
                                            ${errorMsg}
                                                    ${error}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
