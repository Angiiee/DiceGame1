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
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/profile/styleProfileCreditCard.css">
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
                    <div class="row">
                        <div class="col-md-4 col-md-offset-2 formWrap">
                            <form class="form-horizontal" role="form" action="${pageContext.servletContext.contextPath}/controller" method="POST">
                                <input type="hidden" name="command" value="replenish"/>
                                <input type="submit" name="submit" value="replenish" id="replenishAccount" style="display: none;">
                                <fieldset>
                                    <!-- Text input-->
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="usd">1 <span class="glyphicon glyphicon-usd"></span></label>
                                        <div class="col-sm-7">
                                            <input id="usd" name="usd" type="text" disabled class="form-control" value="${creditCardAccountUSD}">
                                        </div>
                                    </div>

                                    <!-- Text input-->
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="eur">1 <span class="glyphicon glyphicon-euro"></span></label>
                                        <div class="col-sm-7">
                                            <input id="eur" name="eur" type="text" disabled class="form-control" value="${creditCardAccountEUR}">
                                        </div>
                                    </div>

                                    <!-- Text input-->
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="byn">1 BYN</label>
                                        <div class="col-sm-7">
                                            <input type="text" name="byn" id="byn" disabled class="form-control" value="${creditCardAccountBYN}">
                                        </div>
                                    </div>

                                    <!-- Text input-->
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="stRate">Standart rate</label>
                                        <div class="col-sm-7">
                                            <input id="stRate" name="standartRate" type="text" disabled class="form-control" value="${creditCardAccountStandartRate}">
                                        </div>
                                    </div>


                                    <!-- Text input-->
                                    <div class="form-group">
                                        <label class="mr-sm-2" for="inlineFormCustomSelect">Сurrency</label>
                                        <select class="custom-select mb-2 mr-sm-2 mb-sm-0" name="selectCurrency" id="inlineFormCustomSelect">
                                            <option selected value="USD">USD</option>
                                            <option value="EUR">EUR</option>
                                            <option value="BYN">BYN</option>
                                        </select>
                                        <label class="col-sm-3 control-label" for="amount">Amount</label>
                                        <div class="col-sm-4">
                                            <input id="amount" name="addAmount" type="text" class="form-control">
                                        </div>
                                    </div>

                                    <!-- Text input-->
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" for="cardNumber"><span class="glyphicon glyphicon-credit-card"></span> Credit Card Number</label>
                                        <div class="col-sm-7">
                                            <input id="cardNumber" name="cardNumber" type="text" class="form-control" required pattern="(\d){16}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-7">
                                            <div class="pull-right">
                                                <a href="${pageContext.servletContext.contextPath}/jsp/common/profileMain.jsp" class="btn btn-default">Cancel</a>
                                                <label for="replenishAccount" class="btn btn-primary">Save</label>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                        </div><!-- /.col-lg-12 -->
                    </div><!-- /.row -->
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