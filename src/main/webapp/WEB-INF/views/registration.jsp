<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13/09/2021
  Time: 11:13 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="common/header.jsp"/>
<html>
<head>
    <title>Register now</title>
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
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                            aria-hidden="true">×</span>
                    </button>
                    <p>please consider the following</p>
                        ${errorMsg}
                </div>
            </div>
        </div>
    </div>
</c:if>

<%--    <form:errors path="user.*" cssClass="error" />--%>
<div class="container mt-3 mb-3">
    <div class="row">
        <div class="col-md-12 mx-auto bg-light">
<%--            <h3>Register now!</h3>--%>
<%--            <form:form action="${pageContext.request.contextPath}/register"--%>
<%--                       modelAttribute="user">--%>
<%--                <label>Name:</label>--%>
<%--                Name : <form:input path="name"/> <br><br>--%>
<%--                Email : <form:input path="email"/> <br><br>--%>
<%--                Age : <form:input path="age"/> <br><br>--%>
<%--                Username : <form:input path="username"/> <br><br>--%>
<%--                Password : <form:input path="password"/> <br><br>--%>
<%--                Mobile : <form:input path="mobile"/> <br><br>--%>
<%--                Gender :<form:radiobuttons path="gender" items="${genders}"></form:radiobuttons> <br><br>--%>
<%--                Date of Birth: <form:input type="date" path="dateOfBirth"/> <br><br>--%>
<%--                Salutation: <form:checkboxes path="salutation" items="${salutations}"/> <br><br>--%>
<%--                HomeTown:--%>
<%--                <form:select path="homeTown">--%>
<%--                    <form:options items="${homeTowns}"/>--%>
<%--                </form:select>--%>
<%--                <br><br>--%>
<%--                <br><br>--%>

<%--                <input type="submit" name="submit" value="register" class="btn btn-primary float-right">--%>

<%--            </form:form>--%>

            <form:form class="form-horizontal form-label-left" novalidate="" action="${pageContext.request.contextPath}/register"
                       modelAttribute="user">
                <span class="section text-light text-center">Personal Info</span>

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
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Email <span
                            class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <form:input type="email" id="email" path="email" name="email" required="required"
                               class="form-control col-md-7 col-xs-12" />
                    </div>
                </div>
<%--                <div class="item form-group">--%>
<%--                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Confirm Email <span--%>
<%--                            class="required">*</span>--%>
<%--                    </label>--%>
<%--                    <div class="col-md-6 col-sm-6 col-xs-12">--%>
<%--                        <input type="email" id="email2" name="confirm_email" data-validate-linked="email"--%>
<%--                               required="required" class="form-control col-md-7 col-xs-12">--%>
<%--                    </div>--%>
<%--                </div>--%>

                <div class="item form-group">
                    <label for="username" class="control-label col-md-3">Username</label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <form:input path="username" id="username" type="text" name="username" data-validate-length="6,8"
                                    class="form-control col-md-7 col-xs-12" required="required" />
                    </div>
                </div>
                <div class="item form-group">
                    <label for="password" class="control-label col-md-3">Password</label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <form:input path="password" id="password" type="password" name="password" data-validate-length="6,8"
                                    class="form-control col-md-7 col-xs-12" required="required" />
                    </div>
                </div>
                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">Number <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <form:input type="number" id="number" path="mobile" name="number" required="required"
                               class="form-control col-md-7 col-xs-12" />
                    </div>
                </div>
                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="age">Age <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <form:input type="number" id="age" path="age" name="age" required="required"
                                    class="form-control col-md-7 col-xs-12" />
                    </div>
                </div>
                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dateOfBirth">Date of Birth <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <form:input type="date" id="dateOfBirth" path="dateOfBirth" name="dateOfBirth" required="required"
                                    class="form-control col-md-7 col-xs-12" />
                    </div>
                </div>
                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="gender">Gender <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
<%--                        <p>--%>
<%--                            M:--%>
<%--                        <div class="iradio_flat-green checked" style="position: relative;">--%>
<%--                        <input type="radio" class="flat" name="gender" id="genderM" value="M" checked="" required="" data-parsley-multiple="gender" style="position: absolute; opacity: 0;">--%>
<%--                        <ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;">--%>

<%--                        </ins>--%>
<%--                    </div>--%>
<%--                        F:--%>
<%--                        <div class="iradio_flat-green" style="position: relative;">--%>
<%--                            <input type="radio" class="flat" name="gender" id="genderF" value="F" data-parsley-multiple="gender" style="position: absolute; opacity: 0;">--%>
<%--                            <ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;">--%>
<%--                        </ins>--%>
<%--                        </div>--%>
<%--                        </p>--%>
                        <form:radiobuttons path="gender" items="${genders}" id="gender" name="gender" ></form:radiobuttons>
                    </div>
                </div>
                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="salutation">Salutaion <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <form:checkboxes path="salutation" items="${salutations}" id="salutation" name="salutation" />
                    </div>
                </div>  <div class="item form-group">
                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="homeTown">HomeTown <span class="required">*</span>
                </label>
                <div class="col-md-6 col-sm-6 col-xs-12">
                <form:select path="homeTown" class="form-control col-md-7 col-xs-12">
                        <form:options items="${homeTowns}"/>
                    </form:select>
                </div>
            </div>



                <%--                <div class="item form-group">--%>
<%--                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="website">Website URL <span--%>
<%--                            class="required">*</span>--%>
<%--                    </label>--%>
<%--                    <div class="col-md-6 col-sm-6 col-xs-12">--%>
<%--                        <input type="url" id="website" name="website" required="required" placeholder="www.website.com"--%>
<%--                               class="form-control col-md-7 col-xs-12">--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="item form-group">--%>
<%--                    <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12">Repeat Password</label>--%>
<%--                    <div class="col-md-6 col-sm-6 col-xs-12">--%>
<%--                        <input id="password2" type="password" name="password2" data-validate-linked="password"--%>
<%--                               class="form-control col-md-7 col-xs-12" required="required">--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="item form-group">--%>
<%--                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="telephone">Telephone <span--%>
<%--                            class="required">*</span>--%>
<%--                    </label>--%>
<%--                    <div class="col-md-6 col-sm-6 col-xs-12">--%>
<%--                        <input type="tel" id="telephone" name="phone" required="required"--%>
<%--                               data-validate-length-range="8,20" class="form-control col-md-7 col-xs-12">--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="item form-group">--%>
<%--                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">Textarea <span--%>
<%--                            class="required">*</span>--%>
<%--                    </label>--%>
<%--                    <div class="col-md-6 col-sm-6 col-xs-12">--%>
<%--                        <textarea id="textarea" required="required" name="textarea"--%>
<%--                                  class="form-control col-md-7 col-xs-12"></textarea>--%>
<%--                    </div>--%>
<%--                </div>--%>
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

<jsp:include page="common/footer.jsp"/>
