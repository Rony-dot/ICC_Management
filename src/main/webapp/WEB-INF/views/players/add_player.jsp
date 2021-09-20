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
<body>
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
<%--            // error of the form--%>
<%--    <form:errors path="player.*" cssClass="error" />--%>

<%--    <form:form action="${pageContext.request.contextPath}/players/add"--%>
<%--               modelAttribute="player">--%>
<%--        <form:input path="id" hidden="true"/>--%>

<%--        <input name="cid" value="${cid}">--%>
<%--        User Name:--%>
<%--        <select name="userId" >--%>
<%--            <c:forEach items="${player_users}" var="user">--%>
<%--                <option value="${user.id}">${user.name}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>

<%--        Player status:--%>
<%--        <form:select path="playerStatus" >--%>
<%--            <form:options items="${playerStatus}"/>--%>
<%--        </form:select>--%>

<%--        Player expertise:--%>
<%--        <form:select path="expertise" >--%>
<%--            <form:options items="${playerExpertise}"/>--%>
<%--        </form:select>--%>

<%--        <input type="submit" name="submit" value="Add User">--%>

<%--    </form:form>--%>

<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Add Strongest Players ! <small></small></h2>
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

                <form:form class="form-horizontal form-label-left" novalidate=""  action="${pageContext.request.contextPath}/players/add"
                           modelAttribute="player">
                    <span class="section text-light text-center">Player Info</span>

                    <form:input path="id" hidden="true"/>
                    <input name="cid" value="${cid}" hidden="true" >

                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Player Name <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="userId" class="form-control col-md-7 col-xs-12"  >
                                <c:forEach items="${player_users}" var="user">
                                    <option value="${user.id}">${user.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Player Status<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:select path="playerStatus" class="form-control col-md-7 col-xs-12" >
                                <form:options items="${playerStatus}"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Player Expertise<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <form:select path="expertise" class=" form-control col-md-7 col-xs-12">
                                <form:options items="${playerExpertise}"/>
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

</body>
</html>

<jsp:include page="../common/footer.jsp" />