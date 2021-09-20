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
    <form:errors path="country.*" cssClass="error" />

    <div class="container mt-3 mb-3">
        <div class="row">
            <div class="col-md-12 mx-auto bg-light">
                <form:form class="form-horizontal form-label-left" novalidate="" action="${pageContext.request.contextPath}/countries/add"
                           modelAttribute="country">
                    <span class="section text-light text-center">Country Details</span>
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
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Managing Director <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="idMD"  class="form-control col-md-7 col-xs-12">
                                <c:forEach items="${managingDirectors}" var="md">
                                    <option value="${md.id}">${md.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" >Players list<span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="playerIds" multiple="multiple" class="select2_multiple form-control col-md-7 col-xs-12">
                                <c:forEach items="${players}" var="player">
                                    <option value="${player.id}">${player.name}</option>
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
</body>
</html>

<jsp:include page="../common/footer.jsp" />