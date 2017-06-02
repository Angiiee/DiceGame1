<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <%--<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/profile/styleProfileMain.css">--%>
            <%--<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/profile/styleProfileInfo.css">--%>
            <%--<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/profile/styleProfileCreditCard.css">--%>
    </head>
    <body class="pageContent">
    <c:set var="visitor" value="${sessionScope.visitor}"/>
    <c:choose>
        <c:when test="${visitor == 'ADMIN'}">
            <c:import url="/jsp/admin/jsp/header.jsp"/>
        </c:when>
        <c:otherwise>
            <c:import url="/jsp/user/jsp/header.jsp"/>
        </c:otherwise>
    </c:choose>
    <div class="row">
        <div class="col-md-4 col-md-offset-2 formWrap">
            <form class="form-horizontal" role="form" action="${pageContext.servletContext.contextPath}/controller"
                  method="POST">
                <input type="hidden" name="command" value="moneysetting"/>
                <input type="submit" name="submit" value="moneysetting" id="submitsetting" style="display: none;">
                <fieldset>
                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="usd">1 <span class="glyphicon glyphicon-usd"></span></label>
                        <div class="col-sm-7">
                            <input id="usd" name="usd" required type="text" pattern="(\d+\.(\d){0,2})" class="form-control" value="0.00">
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="eur">1 <span class="glyphicon glyphicon-euro"></span></label>
                        <div class="col-sm-7">
                            <input id="eur" name="eur" required pattern="(\d+\.(\d){0,2})" type="text" class="form-control" value="0.00">
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="byn">1 BYN</label>
                        <div class="col-sm-7">
                            <input type="text" name="byn" required id="byn" pattern="(\d+\.(\d){0,2})" class="form-control" value="0.00">
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="stRate">Standart rate</label>
                        <div class="col-sm-7">
                            <input id="stRate" name="standartRate" pattern="(\d+\.(\d){0,2})" type="text" required class="form-control" value="0.00">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-7">
                            <div class="pull-right">
                                <a href="${pageContext.servletContext.contextPath}/jsp/common/main.jsp"
                                   class="btn btn-default">Cancel</a>
                                <label for="submitsetting" class="btn btn-primary">Save</label>
                            </div>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div><!-- /.col-lg-12 -->
    </div><!-- /.row -->
    </body>
    </html>
</fmt:bundle>