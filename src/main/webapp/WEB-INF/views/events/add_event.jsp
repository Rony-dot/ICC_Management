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

<%--&lt;%&ndash;            // error of the form&ndash;%&gt;--%>
<%--    <form:errors path="event.*" cssClass="error" />--%>

<%--    <form:form action="${pageContext.request.contextPath}/events/add"--%>
<%--               modelAttribute="event">--%>
<%--        <form:input path="id" hidden="true"/>--%>

<%--        <label>Name:</label>--%>
<%--        <form:input path="name"/> <br><br>--%>
<%--        event start time:--%>
<%--        <form:input path="startDateTime" type="date"/> <br><br>--%>
<%--        event end time :--%>
<%--        <form:input path="endDateTime" type="date"/> <br><br>--%>

<%--        event type:--%>
<%--        <form:select path="eventType" >--%>
<%--            <form:options items="${eventTypes}"/>--%>
<%--        </form:select>--%>
<%--        <br><br>--%>

<%--        Team 1:--%>
<%--        <select name="idTeam1" >--%>
<%--            <c:forEach items="${teams1}" var="team1">--%>
<%--                <option value="${team1.id}">${team1.name}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--        <br><br>--%>

<%--        Team 1 Captain:--%>
<%--        <select name="idTeam1Captain" >--%>
<%--            <c:forEach items="${team_1_Captain}" var="team1Captain">--%>
<%--                <option value="${team1Captain.id}">${team1Captain.userInfo.name}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--        <br><br>--%>

<%--        Team 2:--%>
<%--        <select name="idTeam2" >--%>
<%--            <c:forEach items="${teams2}" var="team2">--%>
<%--                <option value="${team2.id}">${team2.name}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--        <br><br>--%>

<%--        Team 2 Captain:--%>
<%--        <select name="idTeam2Captain" >--%>
<%--            <c:forEach items="${team_2_Captain}" var="team2Captain">--%>
<%--                <option value="${team2Captain.id}">${team2Captain.userInfo.name}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--        <br><br>--%>

<%--        Select existing Umpires :--%>
<%--        <select name="umpireIds" multiple="multiple" >--%>
<%--            <c:forEach items="${umpires}" var="umpire">--%>
<%--                <option value="${umpire.id}" label="${umpire.name}"> ${umpire.name} </option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>

<%--        Or add new Umpires :--%>
<%--        <select name="newUmpireIds" multiple="multiple" >--%>
<%--            <c:forEach items="${new_umpires}" var="new_umpire">--%>
<%--                <option value="${new_umpire.id}" label="${new_umpire.name}"> ${new_umpire.name} </option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>

<%--        <br><br>--%>

<%--        Score--%>
<%--        <form:input path="score" type="number"/>--%>
<%--        <br><br>--%>
<%--        <input type="submit" name="submit" value="Add User">--%>

<%--    </form:form>--%>


<div class="container body">
    <div class="main_container">
        <!-- page content -->
        <div class="right_col" role="main">
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Add Exciting events ! <small></small></h2>
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

                <form:form class="form-horizontal form-label-left" novalidate="" action="${pageContext.request.contextPath}/events/add"
                           modelAttribute="event">
                    <span class="section text-light text-center">Event Info</span>

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
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="startDateTime">Start date time <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input type="date" id="startDateTime" path="startDateTime" name="startDateTime" required="required"
                                        class="form-control col-md-7 col-xs-12" />
                        </div>
                    </div>
                         <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="endDateTime">End date time <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input type="date" id="endDateTime" path="endDateTime" name="endDateTime" required="required"
                                        class="form-control col-md-7 col-xs-12" />
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Event Type <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:select path="eventType" class="form-control col-md-7 col-xs-12">
                                <form:options items="${eventTypes}"/>
                            </form:select>
                        </div>
                    </div>
                     <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Team 1<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="idTeam1" class="form-control col-md-7 col-xs-12">
                                <c:forEach items="${teams1}" var="team1">
                                    <option value="${team1.id}">${team1.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Team 1 Captain<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="idTeam1Captain" class="form-control col-md-7 col-xs-12">
                                <c:forEach items="${team_1_Captain}" var="team1Captain">
                                    <option value="${team1Captain.id}">${team1Captain.userInfo.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Team 2<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="idTeam2" class="form-control col-md-7 col-xs-12">
                                <c:forEach items="${teams2}" var="team2">
                                    <option value="${team2.id}">${team2.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Team 2 Captain<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="idTeam2Captain" class="form-control col-md-7 col-xs-12">
                                <c:forEach items="${team_2_Captain}" var="team2Captain">
                                    <option value="${team2Captain.id}">${team2Captain.userInfo.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Umpires <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="umpireIds" multiple="multiple"  class="select2_multiple form-control col-md-7 col-xs-12">
                                <c:forEach items="${umpires}" var="umpire">
                                    <option value="${umpire.id}" label="${umpire.name}"> ${umpire.name} </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >New Umpires <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12"  class="form-control col-md-7 col-xs-12">
                            <select name="newUmpireIds" multiple="multiple" class="select2_multiple form-control col-md-7 col-xs-12">
                                <c:forEach items="${new_umpires}" var="new_umpire">
                                    <option value="${new_umpire.id}" label="${new_umpire.name}"> ${new_umpire.name} </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="score">Score <span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:input  type="number" id="score" path="score" name="score" required="required"
                                         class="form-control col-md-7 col-xs-12"/>
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

<%--String referer = request.getHeader("Referer");--%>
<%--return "redirect:"+ referer;--%>