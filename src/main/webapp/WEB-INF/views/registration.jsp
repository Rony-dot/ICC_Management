<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13/09/2021
  Time: 11:13 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMsg}</div>
    </c:if>
    <form:errors path="user.*" cssClass="error" />

    <form:form action="${pageContext.request.contextPath}/register"
               modelAttribute="user">

        <%--        // alternative error --%>
        <%--        <form:errors path="*" cssClass="error" element="div" />--%>


        <label>Name:</label>
        Name : <form:input path="name"/>  <br><br>
        Email : <form:input path="email"/>  <br><br>
        Age : <form:input path="age"/>  <br><br>
        Username : <form:input path="username" /> <br><br>
        Password : <form:input path="password"/>  <br><br>
        Mobile : <form:input path="mobile"/>  <br><br>
        Gender :<form:radiobuttons path="gender" items="${genders}" ></form:radiobuttons> <br><br>
        Date of Birth: <form:input type="date" path="dateOfBirth" />  <br><br>
        Salutation: <form:checkboxes path="salutation" items="${salutations}"/> <br><br>
        HomeTown:
        <form:select path="homeTown" >
            <form:options items="${homeTowns}"/>
        </form:select>
        <br><br>
<%--        Role:--%>
<%--        <form:select path="role" >--%>
<%--            <form:options items="${roles}"/>--%>
<%--        </form:select>--%>
        <br><br>
        <%--        // alternative error --%>
        <%--        <form:errors path="name" cssClass="error" />--%>

        <input type="submit" name="submit" value="register">

    </form:form>


</body>
</html>
