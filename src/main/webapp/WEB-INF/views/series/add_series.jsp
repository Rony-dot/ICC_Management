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
<jsp:include page="../common/header.jsp" />

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
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Add Exciting Series ! <small></small></h2>
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

                <form:form class="form-horizontal form-label-left" novalidate="" action="${pageContext.request.contextPath}/series/add"
                           modelAttribute="series">
                    <span class="section text-light text-center">Series Info</span>

                    <form:input path="id" hidden="true"/>

                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Name <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input id="name" path="name" class="form-control col-md-7 col-xs-12" data-validate-length-range="6"
                                        data-validate-words="2" name="name" placeholder="both name(s) e.g Jon Doe" type="text" required="required" />
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="seriesStartDate">Start date time <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input type="date" id="seriesStartDate" path="seriesStartDate" name="seriesStartDate" required="required"
                                        class="form-control col-md-7 col-xs-12" />
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="seriesEndDate">End date time <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input type="date" id="seriesEndDate" path="seriesEndDate" name="seriesEndDate" required="required"
                                        class="form-control col-md-7 col-xs-12" />
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Series Type <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:select path="seriesType" class="form-control col-md-7 col-xs-12">
                                <form:options items="${seriesTypes}"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Event List<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="eventIds" multiple="multiple"  class="select2_multiple form-control col-md-7 col-xs-12" >
                                <c:forEach items="${events}" var="event">
                                    <option value="${event.id}">${event.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Participants Team List<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="teamIds" multiple="multiple"  class="select2_multiple form-control col-md-7 col-xs-12">
                                <c:forEach items="${teams}" var="team">
                                    <option value="${team.id}">${team.name}</option>
                                </c:forEach>
                            </select>
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

<jsp:include page="../common/footer.jsp" />