<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.userLocale}"/>
<fmt:bundle basename="pagecontent">
<html>
<head>
    <title><fmt:message key="registration"/></title>
    <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
    <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:600'>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/menu.css"/>
</head>
<body>
<c:import url="${pageContext.servletContext.contextPath}/jsp/guest/jsp/header.jsp"/>
<div class="design">
    <div>
        <div class="container">
            <div class="back side">
                <div class="dot center"></div>
            </div>
            <div class="left side">
                <div class="dot dtop dleft"></div>
                <div class="dot dbottom dright"></div>
            </div>
            <div class="right side">
                <div class="dot dtop dleft"></div>
                <div class="dot center"></div>
                <div class="dot dbottom dright"></div>
            </div>
            <div class="top side">
                <div class="dot dtop dleft"></div>
                <div class="dot dtop dright"></div>
                <div class="dot dbottom dleft"></div>
                <div class="dot dbottom dright"></div>
            </div>
            <div class="bottom side">
                <div class="dot center"></div>
                <div class="dot dtop dleft"></div>
                <div class="dot dtop dright"></div>
                <div class="dot dbottom dleft"></div>
                <div class="dot dbottom dright"></div>
            </div>
            <div class="front side">
                <div class="dot dtop dleft"></div>
                <div class="dot dtop dright"></div>
                <div class="dot dbottom dleft"></div>
                <div class="dot dbottom dright"></div>
                <div class="dot center dleft"></div>
                <div class="dot center dright"></div>
            </div>
        </div>
    </div>
    <div class="login-wrap">
        <div class="login-html">
            <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
            <label for="tab-1" class="tab"><fmt:message key="signIn"/></label>
            <input id="tab-2" type="radio" name="tab" class="sign-up">
            <label for="tab-2" class="tab"><fmt:message key="signUp"/></label>
            <div class="login-form">
                <form name="sign_in" class="sign-in-htm" method="POST" action="${pageContext.servletContext.contextPath}/controller">
                    <input type="hidden" name="command" value="signin"/>
                    <div class="group">
                        <label for="user" class="label"><fmt:message key="login"/></label>
                        <input id="user" type="text" class="input" name="login">
                    </div>
                    <div class="group">
                        <label for="pas" class="label"><fmt:message key="password"/></label>
                        <input id="pas" type="password" class="input" data-type="password" name="password">
                    </div>
                    <div class="end-group">
                        <div class="group">
                            <input type="submit" class="button" value="<fmt:message key="signIn"/>">
                            <br/>
                            ${errorLoginPassMessage}
                            <br/>
                        </div>
                        <div class="hr"></div>
                        <div class="foot-lnk">
                            <a href="#"><fmt:message key="forgotPassword"/></a>
                        </div>
                    </div>
                </form>
                <form name="sign_up" class="sign-up-htm" onsubmit="return validateSignUpForm()" method="POST" action="${pageContext.servletContext.contextPath}/controller">
                    <input type="hidden" name="command" value="signup"/>
                    <div class="group">
                        <label for="login" class="label mark"><fmt:message key="login"/></label>
                        <input id="login" type="text" class="input" required name="login"
                               pattern="((?.*\d)(?=.*[a-zа-я])(?=.*[A-ZА-Я]).{5,})">
                    </div>
                    <div class="group name">
                        <div><label for="fname" class="label mark"><fmt:message key="firstName"/></label>
                            <input id="fname" type="text" class="input" required name="firstname"
                                   pattern="((?!.*\d)(?=.*[a-zA-Zа-яА-Я]).{2,})"></div>
                        <div><label for="lname" class="label mark"><fmt:message key="lastName"/></label>
                            <input id="lname" type="text" class="input" required name="lastname"
                                   pattern="((?!.*\d)(?=.*[a-zA-Zа-яА-Я]).{2,})"></div>
                    </div>
                    <div class="group password">
                        <div><label for="pass" class="label mark"><fmt:message key="password"/></label>
                            <input id="pass" type="password" class="input" data-type="password" required
                                   pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,})" name="password"></div>
                        <div><label for="rpass" class="label mark"><fmt:message key="repeatPassword"/></label>
                            <input id="rpass" type="password" class="input" data-type="password" required
                                   pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,})"></div>
                    </div>
                    <div class="group">
                        <label for="email" class="label mark"><fmt:message key="email"/></label>
                        <input id="email" type="email" class="input" required name="email"
                               placeholder="my_email@example.com">
                    </div>
                    <div class="group">
                        <label for="date" class="label mark"><fmt:message key="birthDate"/></label>
                        <input id="date" type="date" class="input" name="birthday"
                               min="1898-01-01" max="2010-01-01" required>
                    </div>
                    <div class="end-group">
                        <div class="group">
                            <input type="submit" class="button" value="<fmt:message key="signUp"/>">
                        </div>
                        <div class="hr"></div>
                        <div class="foot-lnk">
                            <label for="tab-1"><fmt:message key="alreadyMember"/></label>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
</fmt:bundle>