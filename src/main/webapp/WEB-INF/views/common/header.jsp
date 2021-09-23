<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22/08/2021
  Time: 9:26 pm
  To change this template use File | Settings | File Templates.  \\
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="${pageContext.request.contextPath}/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- NProgress -->
<link href="${pageContext.request.contextPath}/vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- Animate.css -->
<link href="${pageContext.request.contextPath}/vendors/animate.css/animate.min.css" rel="stylesheet">
<!-- iCheck -->
<link href="${pageContext.request.contextPath}/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
<!-- bootstrap-progressbar -->
<link href="${pageContext.request.contextPath}/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
<!-- JQVMap -->
<link href="${pageContext.request.contextPath}/vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>
<!-- bootstrap-daterangepicker -->
<link href="${pageContext.request.contextPath}/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
<!-- PNotify -->
<link href="${pageContext.request.contextPath}/vendors/pnotify/dist/pnotify.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/vendors/pnotify/dist/pnotify.buttons.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/vendors/pnotify/dist/pnotify.nonblock.css" rel="stylesheet">

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

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>Gentelella Alela!</span></a>
                </div>

                <div class="clearfix"></div>

                <!-- menu profile quick info -->
                <div class="profile clearfix">
                    <div class="profile_pic">
                        <img src="${pageContext.request.contextPath}/images/img.jpg" alt="..." class="img-circle profile_img">
                    </div>
                    <div class="profile_info">
                        <span>Welcome,</span>
                        <h2>John Doe</h2>
                    </div>
                </div>
                <!-- /menu profile quick info -->

                <br />

                <!-- sidebar menu -->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                    <div class="menu_section">
                        <h3>General</h3>
                        <ul class="nav side-menu">
                            <li><a class="active" href="/"><i class="fa fa-home"></i> Home <span class="fa fa-chevron-down"></span></a></li>

                            <li><a><i class="fa fa-edit"></i> Users <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a  href="/users/all">All Users</a></li>
                                    <li><a  href="/users/add">Add Users</a></li>
                                </ul>
                            </li>

                            <li><a><i class="fa fa-desktop"></i> Countries <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="/countries/all">All Countries</a></li>
                                    <li><a href="/countries/add">Add Country</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-table"></i> Players <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="/players/all">All Players</a></li>
                                    <li><a href="/players/add">Add Player</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-bar-chart-o"></i> Teams <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="/teams/all">All Teams</a></li>
                                    <li><a href="/teams/add">Add Team</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-clone"></i>Events <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="/events/all">All Events</a></li>
                                    <li><a href="/events/add">Add Event</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-clone"></i>Series <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="/series/all">All Series</a></li>
                                    <li><a href="/series/add">Add Series</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-clone"></i>Setting <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                                    <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                                    <li><a href="${pageContext.request.contextPath}/register">Register</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <div class="menu_section">
                        <h3>Live On</h3>
                        <ul class="nav side-menu">
                            <li><a><i class="fa fa-bug"></i> Additional Pages <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="e_commerce.html">E-commerce</a></li>
                                    <li><a href="projects.html">Projects</a></li>
                                    <li><a href="project_detail.html">Project Detail</a></li>
                                    <li><a href="contacts.html">Contacts</a></li>
                                    <li><a href="profile.html">Profile</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-windows"></i> Extras <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="page_403.html">403 Error</a></li>
                                    <li><a href="page_404.html">404 Error</a></li>
                                    <li><a href="page_500.html">500 Error</a></li>
                                    <li><a href="plain_page.html">Plain Page</a></li>
                                    <li><a href="login.html">Login Page</a></li>
                                    <li><a href="pricing_tables.html">Pricing Tables</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-sitemap"></i> Multilevel Menu <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="#level1_1">Level One</a>
                                    <li><a>Level One<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li class="sub_menu"><a href="level2.html">Level Two</a>
                                            </li>
                                            <li><a href="#level2_1">Level Two</a>
                                            </li>
                                            <li><a href="#level2_2">Level Two</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li><a href="#level1_2">Level One</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a href="javascript:void(0)"><i class="fa fa-laptop"></i> Landing Page <span class="label label-success pull-right">Coming Soon</span></a></li>
                        </ul>
                    </div>

                </div>
                <!-- /sidebar menu -->

                <!-- /menu footer buttons -->
                <div class="sidebar-footer hidden-small">
                    <a data-toggle="tooltip" data-placement="top" title="Settings">
                        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                        <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="Lock">
                        <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="Logout" href="login.html">
                        <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                    </a>
                </div>
                <!-- /menu footer buttons -->
            </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
            <div class="nav_menu">
                <nav>
                    <div class="nav toggle">
                        <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                    </div>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="">
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <img src="${pageContext.request.contextPath}/images/img.jpg" alt="">John Doe
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu pull-right">
                                <li><a href="javascript:;"> Profile</a></li>
                                <li>
                                    <a href="javascript:;">
                                        <span class="badge bg-red pull-right">50%</span>
                                        <span>Settings</span>
                                    </a>
                                </li>
                                <li><a href="javascript:;">Help</a></li>
                                <li><a href="login.html"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                            </ul>
                        </li>

                        <li role="presentation" class="dropdown">
                            <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                                <i class="fa fa-envelope-o"></i>
                                <span class="badge bg-green">6</span>
                            </a>
                            <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                                <li>
                                    <a>
                                        <span class="image"><img src="${pageContext.request.contextPath}/images/img.jpg" alt="Profile Image" /></span>
                                        <span>
                          <span>John Smith</span>
                          <span class="time">3 mins ago</span>
                        </span>
                                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                                    </a>
                                </li>
                                <li>
                                    <a>
                                        <span class="image"><img src="${pageContext.request.contextPath}/images/img.jpg" alt="Profile Image" /></span>
                                        <span>
                          <span>John Smith</span>
                          <span class="time">3 mins ago</span>
                        </span>
                                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                                    </a>
                                </li>
                                <li>
                                    <a>
                                        <span class="image"><img src="${pageContext.request.contextPath}/images/img.jpg" alt="Profile Image" /></span>
                                        <span>
                          <span>John Smith</span>
                          <span class="time">3 mins ago</span>
                        </span>
                                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                                    </a>
                                </li>
                                <li>
                                    <a>
                                        <span class="image"><img src="${pageContext.request.contextPath}/images/img.jpg" alt="Profile Image" /></span>
                                        <span>
                          <span>John Smith</span>
                          <span class="time">3 mins ago</span>
                        </span>
                                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                                    </a>
                                </li>
                                <li>
                                    <div class="text-center">
                                        <a>
                                            <strong>See All Alerts</strong>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <!-- /top navigation -->




    </div>
</div>

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

</html>
