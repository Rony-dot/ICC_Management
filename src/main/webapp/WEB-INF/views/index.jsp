<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22/08/2021
  Time: 9:11 pm
  To change this template use File | Settings | File Templates.
--%>
<html>
<jsp:include page="common/header.jsp"/>
<title>${pageTitle}</title>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <!-- page content -->
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                   <img src="${pageContext.request.contextPath}/images/logo.jpg" width="100%" >
                  </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>


<jsp:include page="common/footer.jsp" />
