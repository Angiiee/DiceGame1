<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body class="menuContent">
<form action="${pageContext.servletContext.contextPath}/controller" method="GET" id="showCreditCardAccount" style="display: none;">
    <input type="hidden" name="command" value="showcreditaccount"/>
    <input type="submit" name="submit" value="showcreditaccount" id="showAccount">
</form><nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle pull-left" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/jsp/common/main.jsp">Lucky DICE</a>
            <div class="pull-right flag">
                <form action="${pageContext.servletContext.contextPath}/controller" method="GET" class="wrap">
                    <input type="hidden" name="command" value="locale"/>
                    <input type="text" value="ru-RU" class="localeLabel">
                    <input type="submit" name="submit" value="" class="button-localeRU">
                </form>
            </div>
            <div class="pull-right flag">
                <form action="${pageContext.servletContext.contextPath}/controller" method="GET" class="wrap">
                    <input type="hidden" name="command" value="locale"/>
                    <input type="text" value="en-EN" class="localeLabel">
                    <input type="submit" name="submit" value="" class="button-localeEN">
                </form>
            </div>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="menuItem"><a href="${pageContext.servletContext.contextPath}/jsp/common/main.jsp">Home</a></li>
                <li class="menuItem"><a href=""><label for="showAccount"><span class="glyphicon glyphicon-piggy-bank"></span> ${sessionScope.userScore}</label></a></li>
                <li class="menuItem"><a href="${pageContext.servletContext.contextPath}/jsp/admin/creditCardSetting.jsp">Settings</a></li>
                <li class="menuItem"><a href="#">Complaints</a></li>
                <li class="menuItem"><a href="${pageContext.servletContext.contextPath}/jsp/common/game/gameMain.jsp">Game</a></li>
                <li class="menuItem"><a href="${pageContext.servletContext.contextPath}/jsp/common/mail/newMail.jsp">Mail</a></li>
                <li class="menuItem"><a href="${pageContext.servletContext.contextPath}/jsp/common/profileMain.jsp">Profile</a></li>
                <li class="menuItem"><a href="${pageContext.servletContext.contextPath}/controller?command=logout&submit=logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>