<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.userLocale}"/>
<fmt:bundle basename="pagecontent">
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/profile/styleProfileMain.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/mail/styleNewMail.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/menu.css">
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
<form action="${pageContext.servletContext.contextPath}/controller" method="GET" id="showInboxMessage">
    <input type="hidden" name="command" value="showinbox"/>
    <input type="submit" name="submit" value="showinbox" id="showInbox">
</form>
<form action="${pageContext.servletContext.contextPath}/controller" method="GET" id="showSentMail">
    <input type="hidden" name="command" value="showsentmail"/>
    <input type="submit" name="submit" value="showsentmail" id="showSent">
</form>
<div id="wrapper">
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebarItem">
                <a href="${pageContext.servletContext.contextPath}/jsp/common/mail/newMail.jsp"><span class="glyphicon glyphicon-send"></span> Compose</a>
            </li>
            <li class="sidebarItem">
                <a href=""><label for="showInbox"><span class="glyphicon glyphicon-arrow-down"></span> Inbox</label></a>
            </li>
            <li class="sidebarItem">
                <a href=""><label for="showSent"><span class="glyphicon glyphicon-arrow-up"></span> Sent Mail</label></a>
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
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6">
                                <form action="${pageContext.servletContext.contextPath}/controller" method="post" class="form">
                                    <div class="get-in-touch">
                                        <h3 class="text-center">
                                            New Message</h3>
                                        <div class="form-group">
                                            <input type="text" name="recipientLogin" class="form-control" id="login" placeholder="Login" required/>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" name="theme" class="form-control" id="theme" placeholder="Theme" required/>
                                        </div>
                                        <div class="form-group">
                                            <textarea class="form-control" name="textMessage" rows="10" placeholder="Message" required></textarea>
                                        </div>

                                        <div>
                                            <button type="submit" class="btn btn-default">Cancel</button>
                                            <input type="hidden" name="command" value="newmessage"/>
                                            <input type="submit" class="btn btn-primary" value="Send">
                                            ${errorSentMessage}
                                        </div>
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