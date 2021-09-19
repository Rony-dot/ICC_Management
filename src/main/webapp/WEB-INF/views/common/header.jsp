<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22/08/2021
  Time: 9:26 pm
  To change this template use File | Settings | File Templates.  \\
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
        <%--    bootstrap--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
            <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
            <!-- NProgress -->
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nprogress.css">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css">
            <!-- Custom Theme Style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.min.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<%--        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>--%>


    <title></title>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <!-- Brand/logo -->
    <a class="navbar-brand" href="#">

        <img src="${pageContext.request.contextPath}/images/logo.jpg" alt="logo" style="width:40px; height: 40px;">
    </a>

    <!-- Links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="/">Home</a>
        </li>
        <!-- Dropdown -->
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                Users
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="/users/all">All Users</a>
                <a class="dropdown-item" href="/users/add">Add User</a>
            </div>

        </li>
        <!-- Dropdown end -->
        <!-- Dropdown -->
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop2" data-toggle="dropdown">
                Country
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="/countries/all">All countries</a>
                <a class="dropdown-item" href="/countries/add">Add country</a>
            </div>

        </li>
        <!-- Dropdown end -->
        <!-- Dropdown -->
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop2" data-toggle="dropdown">
                Player
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="/players/all">All Players</a>
                <a class="dropdown-item" href="/players/add">Add Player</a>
            </div>

        </li>
        <!-- Dropdown end -->
        <!-- Dropdown -->
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop2" data-toggle="dropdown">
                Teams
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="/teams/all">All Teams</a>
                <a class="dropdown-item" href="/teams/add">Add Teams</a>
            </div>

        </li>
        <!-- Dropdown end -->
        <!-- Dropdown -->
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop2" data-toggle="dropdown">
                Events
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="/events/all">All Events</a>
                <a class="dropdown-item" href="/events/add">Add Events</a>
            </div>

        </li>
        <!-- Dropdown end -->
        <!-- Dropdown -->
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop2" data-toggle="dropdown">
                Series
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="/series/all">All Series</a>
                <a class="dropdown-item" href="/series/add">Add Series</a>
            </div>

        </li>
        <!-- Dropdown end -->
        <li class="nav-item ">
            <a class="nav-link" href="${pageContext.request.contextPath}/login">login</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
        </li>
        <li class="nav-item ">
            <a class="nav-link" href="${pageContext.request.contextPath}/register">register</a>
        </li>
    </ul>
</nav>

<c:if test="${not empty errorMsg}">
    <div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMsg}</div>
</c:if>

</body>


