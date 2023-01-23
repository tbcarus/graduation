<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>

<html lang="ru">
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyCommon.jsp"/>
<hr>
<c:set var="user" value="${requestScope.user}"/>
<jsp:useBean id="user" class="ru.tbcarus.topjava.model.User" scope="request"/>
<h2><spring:message code="user.user"/></h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th><spring:message code="user.name"/></th>
        <th><spring:message code="user.email"/></th>
        <th><spring:message code="user.active"/></th>
        <th><spring:message code="user.registered"/></th>
        <th><spring:message code="user.roles"/></th>
    </tr>
    </thead>
    <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.email}</td>
        <td>${user.enabled}</td>
        <td>${DateTimeUtil.toString(user.registered)}</td>
        <td>
            <c:forEach var="role" items="${user.roles}">
                <jsp:useBean id="role" type="ru.tbcarus.topjava.model.Role"/>
                ${role.name()}
            </c:forEach>
        </td>
    </tr>
</table>
</body>
</html>