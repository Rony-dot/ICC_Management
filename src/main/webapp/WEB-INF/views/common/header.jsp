<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22/08/2021
  Time: 9:26 pm
  To change this template use File | Settings | File Templates.  \\
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="${pageContext.request.contextPath}/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- NProgress -->
<link href="${pageContext.request.contextPath}/vendors/nprogress/nprogress.css" rel="stylesheet">

<!-- Custom Theme Style -->
<link href="{pageContext.request.contextPath}/build/css/custom.min.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link href="${pageContext.request.contextPath}/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- bootstrap-progressbar -->
<link href="${pageContext.request.contextPath}/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
<!-- PNotify -->
<link href="${pageContext.request.contextPath}/vendors/pnotify/dist/pnotify.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/vendors/pnotify/dist/pnotify.buttons.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/vendors/pnotify/dist/pnotify.nonblock.css" rel="stylesheet">
        <!-- Custom Theme Style -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.min.css">
<!-- Datatables -->
<link href="${pageContext.request.contextPath}/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
<!-- Custom Theme Style -->
<link href="${pageContext.request.contextPath}/build/css/custom.min.css" rel="stylesheet">

            <title></title>
</head>
<body>

<%--<nav class="navbar navbar-expand-sm bg-dark navbar-dark">--%>
<%--    <!-- Brand/logo -->--%>
<%--    <a class="navbar-brand" href="#">--%>

<%--        <img src="${pageContext.request.contextPath}/images/logo.jpg" alt="logo" style="width:40px; height: 40px;">--%>
<%--    </a>--%>

<%--    <!-- Links -->--%>
<%--    <ul class="navbar-nav">--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link" href="/">Home</a>--%>
<%--        </li>--%>
<%--        <!-- Dropdown -->--%>
<%--        <li class="nav-item dropdown">--%>
<%--            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">--%>
<%--                Users--%>
<%--            </a>--%>
<%--            <div class="dropdown-menu">--%>
<%--                <a class="dropdown-item" href="/users/all">All Users</a>--%>
<%--                <a class="dropdown-item" href="/users/add">Add User</a>--%>
<%--            </div>--%>

<%--        </li>--%>
<%--        <!-- Dropdown end -->--%>
<%--        <!-- Dropdown -->--%>
<%--        <li class="nav-item dropdown">--%>
<%--            <a class="nav-link dropdown-toggle" href="#" id="navbardrop2" data-toggle="dropdown">--%>
<%--                Country--%>
<%--            </a>--%>
<%--            <div class="dropdown-menu">--%>
<%--                <a class="dropdown-item" href="/countries/all">All countries</a>--%>
<%--                <a class="dropdown-item" href="/countries/add">Add country</a>--%>
<%--            </div>--%>

<%--        </li>--%>
<%--        <!-- Dropdown end -->--%>
<%--        <!-- Dropdown -->--%>
<%--        <li class="nav-item dropdown">--%>
<%--            <a class="nav-link dropdown-toggle" href="#" id="navbardrop2" data-toggle="dropdown">--%>
<%--                Player--%>
<%--            </a>--%>
<%--            <div class="dropdown-menu">--%>
<%--                <a class="dropdown-item" href="/players/all">All Players</a>--%>
<%--                <a class="dropdown-item" href="/players/add">Add Player</a>--%>
<%--            </div>--%>

<%--        </li>--%>
<%--        <!-- Dropdown end -->--%>
<%--        <!-- Dropdown -->--%>
<%--        <li class="nav-item dropdown">--%>
<%--            <a class="nav-link dropdown-toggle" href="#" id="navbardrop2" data-toggle="dropdown">--%>
<%--                Teams--%>
<%--            </a>--%>
<%--            <div class="dropdown-menu">--%>
<%--                <a class="dropdown-item" href="/teams/all">All Teams</a>--%>
<%--                <a class="dropdown-item" href="/teams/add">Add Teams</a>--%>
<%--            </div>--%>

<%--        </li>--%>
<%--        <!-- Dropdown end -->--%>
<%--        <!-- Dropdown -->--%>
<%--        <li class="nav-item dropdown">--%>
<%--            <a class="nav-link dropdown-toggle" href="#" id="navbardrop2" data-toggle="dropdown">--%>
<%--                Events--%>
<%--            </a>--%>
<%--            <div class="dropdown-menu">--%>
<%--                <a class="dropdown-item" href="/events/all">All Events</a>--%>
<%--                <a class="dropdown-item" href="/events/add">Add Events</a>--%>
<%--            </div>--%>

<%--        </li>--%>
<%--        <!-- Dropdown end -->--%>
<%--        <!-- Dropdown -->--%>
<%--        <li class="nav-item dropdown">--%>
<%--            <a class="nav-link dropdown-toggle" href="#" id="navbardrop2" data-toggle="dropdown">--%>
<%--                Series--%>
<%--            </a>--%>
<%--            <div class="dropdown-menu">--%>
<%--                <a class="dropdown-item" href="/series/all">All Series</a>--%>
<%--                <a class="dropdown-item" href="/series/add">Add Series</a>--%>
<%--            </div>--%>

<%--        </li>--%>
<%--        <!-- Dropdown end -->--%>
<%--        <li class="nav-item ">--%>
<%--            <a class="nav-link" href="${pageContext.request.contextPath}/login">login</a>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>--%>
<%--        </li>--%>
<%--        <li class="nav-item ">--%>
<%--            <a class="nav-link" href="${pageContext.request.contextPath}/register">register</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
<%--</nav>--%>

<nav class="navbar navbar-inverse mt-5">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-left" href="/">
                <img src="${pageContext.request.contextPath}/images/logo.jpg" alt="logo" style="width:40px; height: 40px;">
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/">Home <span class="sr-only">(current)</span></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Users <span class="caret"></span></a>
                    <ul class="dropdown-menu">

                        <li><a  href="/users/all">All Users</a></li>
                        <li><a  href="/users/add">Add Users</a></li>
                    </ul>
                </li>
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Countries <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/countries/all">All Countries</a></li>
                        <li><a href="/countries/add">Add Country</a></li>
                    </ul>
                </li>
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Players <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/players/all">All Players</a></li>
                        <li><a href="/players/add">Add Player</a></li>
                    </ul>
                </li>
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Teams <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/teams/all">All Teams</a></li>
                        <li><a href="/teams/add">Add Team</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Events <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/events/all">All Events</a></li>
                        <li><a href="/events/add">Add Event</a></li>
                    </ul>
                </li>
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Series <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/series/all">All Series</a></li>
                        <li><a href="/series/add">Add Series</a></li>
                    </ul>
                </li>

            </ul>
<%--            <form class="navbar-form navbar-left">--%>
<%--                <div class="form-group">--%>
<%--                    <input type="text" class="form-control" placeholder="Search">--%>
<%--                </div>--%>
<%--                <button type="submit" class="btn btn-default">Submit</button>--%>
<%--            </form>--%>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                <li><a href="${pageContext.request.contextPath}/register">Register</a></li>
<%--                <li class="dropdown">--%>
<%--                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>--%>
<%--                    <ul class="dropdown-menu">--%>
<%--                        <li role="separator" class="divider"></li>--%>
<%--                        <li><a href="#">Separated link</a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
            </ul>


        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<%--        <c:if test="${not empty errorMsg}">--%>
<%--            <div class="container mt-3">--%>
<%--                <div class="row">--%>
<%--                    <div class="col-md-6 mx-auto">--%>
<%--                        <div class="alert alert-danger alert-dismissible fade show" role="alert">--%>
<%--                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>--%>
<%--                            </button>--%>
<%--                            <p>please consider the following</p>--%>
<%--                                ${errorMsg}--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </c:if>--%>

</body>


