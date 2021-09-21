<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 19/09/2021
  Time: 12:37 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <%--    bootstrap--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <!-- NProgress -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nprogress.css">
    <!-- Custom Theme Style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.min.css">
</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <!-- page content -->
        <div class="col-md-12">
            <div class="col-middle">
                <div class="text-center">
                    <h1 class="error-number">500</h1>
                    <h2>Internal Server Error</h2>
                    <p>We track these errors automatically, but if the problem persists feel free to contact us. In the meantime, try refreshing. <a href="#">Report this?</a>
                    </p>
                    <div class="mid_center">
                        <h3>Search</h3>
                        <form>
                            <div class="col-xs-12 form-group pull-right top_search">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search for...">
                                    <span class="input-group-btn">
                              <button class="btn btn-default" type="button">Go!</button>
                          </span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- /page content -->
    </div>
</div>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="${pageContext.request.contextPath}/vendors/nprogress/nprogress.js"></script>

<!-- Custom Theme Scripts -->
<script src="${pageContext.request.contextPath}/build/js/custom.min.js"></script>
</body>
</html>

