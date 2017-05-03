<%--
  Created by IntelliJ IDEA.
  User: angelina
  Date: 12.03.2017
  Time: 8:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/menu.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="visitor" value="${sessionScope.visitor}"/>
<c:choose>
<c:when test="${visitor == 'ADMIN'}">
    <c:import url="/jsp/admin/jsp/header.jsp" />
</c:when>
<c:otherwise>
    <c:import url="/jsp/user/jsp/header.jsp" />
</c:otherwise>
</c:choose>
${sessionScope.username} welcome!
</body>
</html>
