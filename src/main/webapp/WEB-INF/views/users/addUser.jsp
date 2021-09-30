<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24/08/2021
  Time: 12:21 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp"/>

<html>
<head>
    <title>Title</title>
    <style type="text/css">
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>
<body class="nav-md">
<c:if test="${not empty errorMsg}">
    <div class="container mt-3">
        <div class="row">
            <div class="col-md-6 mx-auto">
                <div class="alert alert-danger">
                        ${errorMsg}
                </div>
            </div>
        </div>
    </div>
</c:if>

<div class="container body">
    <div class="main_container">
        <!-- page content -->
        <div class="right_col" role="main">
<%--            // error of the form--%>
<form:errors path="user.*" cssClass="error"/>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Add User ! <small>register users to give exciting news</small></h2>
                <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">Settings 1</a>
                            </li>
                            <li><a href="#">Settings 2</a>
                            </li>
                        </ul>
                    </li>
                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">

                <form:form class="form-horizontal form-label-left" novalidate=""
                           action="${pageContext.request.contextPath}/register"
                           modelAttribute="user">
                    <span class="section text-light text-center">Personal Info</span>

                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Name <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input id="name" path="name" class="form-control col-md-7 col-xs-12"
                                        data-validate-length-range="6"
                                        data-validate-words="2" name="name" placeholder="both name(s) e.g Jon Doe"
                                        type="text" required="required"/>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Email <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input type="email" id="email" path="email" name="email" required="required"
                                        class="form-control col-md-7 col-xs-12"/>
                        </div>
                    </div>

                    <div class="item form-group">
                        <label for="username" class="control-label col-md-3">Username</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input path="username" id="username" type="text" name="username" data-validate-length="6,8"
                                        class="form-control col-md-7 col-xs-12" required="required"/>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label for="password" class="control-label col-md-3">Password</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input path="password" id="password" type="password" name="password"
                                        data-validate-length="6,8"
                                        class="form-control col-md-7 col-xs-12" required="required"/>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">Number <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input type="number" id="number" path="mobile" name="number" required="required"
                                        class="form-control col-md-7 col-xs-12"/>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="age">Age <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input type="number" id="age" path="age" name="age" required="required"
                                        class="form-control col-md-7 col-xs-12"/>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dateOfBirth">Date of Birth <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input type="date" id="dateOfBirth" path="dateOfBirth" name="dateOfBirth"
                                        required="required"
                                        class="form-control col-md-7 col-xs-12"/>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="gender">Gender <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:radiobuttons path="gender" items="${genders}" id="gender"
                                               name="gender"/>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="salutation">Salutaion <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:checkboxes path="salutation" items="${salutations}" id="salutation" name="salutation"/>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="homeTown">HomeTown <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:select path="homeTown" class="form-control col-md-7 col-xs-12">
                                <form:options items="${homeTowns}"/>
                            </form:select>
                        </div>
                    </div> <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="homeTown">HomeTown <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:select path="homeTown" class="form-control col-md-7 col-xs-12">
                                <form:options items="${homeTowns}"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="ln_solid"></div>
                    <div class="form-group">
                        <div class="col-md-6 col-md-offset-3">
                            <button type="submit" class="btn btn-primary">Cancel</button>
                            <button id="send" type="submit" class="btn btn-success">Submit</button>
                        </div>
                    </div>
                </form:form>

            </div>
        </div>
    </div>
</div>
        </div>
    </div>
</div>

</body>
</html>

<jsp:include page="../common/footer.jsp"/>