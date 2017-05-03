<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.userLocale}"/>
<fmt:bundle basename="pagecontent">
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/profile/styleProfileMain.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/menu.css"/>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/imgHelper.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
</head>
<body class="pageContent">
<c:set var="visitor" value="${sessionScope.visitor}"/>
<c:choose>
    <c:when test="${visitor == 'ADMIN'}">
        <c:import url="/jsp/admin/jsp/header.jsp" />
    </c:when>
    <c:otherwise>
        <c:import url="/jsp/user/jsp/header.jsp" />
    </c:otherwise>
</c:choose>
<form action="${pageContext.servletContext.contextPath}/controller" method="GET" id="showProfileForm">
    <input type="hidden" name="command" value="showprofile"/>
    <input type="submit" name="submit" value="showprofile" id="profile-show">
</form>
<form action="${pageContext.servletContext.contextPath}/controller" method="GET" id="showCreditCardAccount">
    <input type="hidden" name="command" value="showcreditaccount"/>
    <input type="submit" name="submit" value="showcreditaccount" id="showAccount">
</form>
<div id="wrapper">
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebarItem">
                <a href="${pageContext.servletContext.contextPath}/jsp/common/profileMain.jsp"><span class="glyphicon glyphicon-pencil"></span> Avatar</a>
            </li>
            <li class="sidebarItem">
                <a href=""><label for="profile-show"><span class="glyphicon glyphicon-user"></span> Personal information</label></a>
            </li>
            <li class="sidebarItem">
                <a href=""><label for="showAccount"><span class="glyphicon glyphicon-credit-card"></span> Credit Card</label></a>
            </li>
        </ul>
    </div>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">
                        <span class="glyphicon glyphicon-menu-hamburger btnMenu"></span>
                    </a>
                    <form class="avatar-wrap" action="${pageContext.servletContext.contextPath}/controller" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="command" value="load"/>
                        <div class="imgContainer">
                            <img src="${pageContext.servletContext.contextPath}/img/avatar/${sessionScope.profileImg}" id="iavatar" alt="User avatar">
                        </div>
                        <div class="group">
                            <label class="btn btn-info" for="lavatar">Choose</label>
                            <input type="submit" id="saveAvatar" class="btn btn-info" value="<fmt:message key="load"/>" name="command">
                        </div>
                        <br>
                        ${errorSaveAvatarMessage}
                        <br>
                        ${errorEditMessage}
                        <br>
                        <input type="file" name="avatarLoader" id="lavatar" style="display: none">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>
</body>
</html>
</fmt:bundle>