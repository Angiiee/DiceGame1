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
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css">
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/listHelper.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/menu.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/mail/styleMessageList.css"/>
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

                        <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>To</th>
                                <th>Theme</th>
                                <th>Message</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>To</th>
                                <th>Theme</th>
                                <th>Message</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${sentMailList}" var="list">
                                <tr>
                                    <td>${list.recipientLogin}</td>
                                    <td>${list.theme}</td>
                                    <td>${list.text}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

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