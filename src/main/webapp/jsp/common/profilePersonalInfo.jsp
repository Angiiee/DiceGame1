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
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/menu.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/profile/styleProfileMain.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/profile/styleProfileInfo.css">
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
                    <div class="profileWrap">
                        <div class="profile" id="profileblock">
                            <input id="tab-1" type="radio" name="tab" class="profile-data" checked><label for="tab-1" class="tab" >Personal information</label>
                            <input id="tab-2" type="radio" name="tab" class="profile-edit"><label for="tab-2" class="tab">Edit profile</label>
                            <div class="profile-data-form">
                                <form name="profile-data" class="profile-data-htm" action="${pageContext.servletContext.contextPath}/controller" method="GET">
                                    <div class="group">
                                        <label for="user" class="label">Login</label>
                                        <input id="user" type="text" class="input" disabled value="${profileLogin}">
                                    </div>
                                    <div class="group name">
                                        <div> <label for="fname" class="label"><span class="glyphicon glyphicon-pencil"></span> First name</label>
                                            <input id="fname" type="text" class="input" disabled value="${profileFirstName}"></div>
                                        <div><label for="lname" class="label"><span class="glyphicon glyphicon-pencil"></span> Last name</label>
                                            <input id="lname" type="text" class="input" disabled value="${profileLastName}"></div>
                                    </div>
                                    <div class="group">
                                        <label for="email" class="label">Email Address</label>
                                        <input id="email" type="email" class="input" name="mail" disabled value="${profileEmail}">
                                    </div>
                                    <div class="group">
                                        <label for="date" class="label">Birth date</label>
                                        <input id="date" type="date" class="input" name="birth" disabled value="${profileBirthDate}">
                                    </div>
                                    <div class="group">
                                        <label for="credit" class="label"><span class="glyphicon glyphicon-pencil"></span> Credit card number</label>
                                        <input id="credit" type="text" class="input" name="crCard" disabled value="${profileCard}">
                                    </div>
                                    <div class="foot-lnk">
                                        <label class="btn btn-info" for="tab-2"><span class="glyphicon glyphicon-edit"></span> Edit</label>
                                    </div>
                                </form>
                                <form name="profile-edit" action="${pageContext.servletContext.contextPath}/controller" method="POST" class="profile-edit-htm">
                                    <input type="hidden" name="command" value="editprofile"/>
                                    <div class="group name">
                                        <div> <label for="fnameEd" class="label mark">First name</label>
                                            <input id="fnameEd" name="fnameEd" type="text" class="input" required pattern="((?!.*\d)(?=.*[a-zA-Zа-яА-Я]).{2,})"></div>
                                        <div><label for="lnameEd" class="label mark">Last name</label>
                                            <input id="lnameEd" name="lnameEd" type="text" class="input" required pattern="((?!.*\d)(?=.*[a-zA-Zа-яА-Я]).{2,})"></div>
                                    </div>
                                    <div class="group">
                                        <label for="creditEd" class="label mark">Credit card number</label>
                                        <input id="creditEd" type="text" class="input" required name="crCard" pattern="(\d){16}">
                                    </div>

                                    <div class="group">
                                        <input type="submit" class="btn btn-info" value="Save changes">
                                    </div>
                                    <div class="foot-lnk">
                                        <label for="tab-1" class="btn btn-info">Return</label>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
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