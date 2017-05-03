<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:set var="visitor" value="${sessionScope.visitor}"/>
<c:choose>
    <c:when test="${visitor == 'USER' || visitor == 'ADMIN'}">
        <jsp:forward page="/jsp/common/main.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:forward page="/jsp/guest/registration.jsp"/>
    </c:otherwise>
</c:choose>
</body>
</html>