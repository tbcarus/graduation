<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>

<fmt:setBundle basename="messages.app"/>

<html lang="ru">
<head>
    <title><fmt:message key="app.title"/></title>
</head>
<body>
<h3><a href="/vote"><fmt:message key="app.home"/></a></h3>
<hr>
<h2><fmt:message key="user.title"/></h2>
<c:set var="user" value="${requestScope.user}"/>
<jsp:useBean id="user" type="ru.tbcarus.topjava.model.User"/>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th><fmt:message key="user.name"/></th>
        <th><fmt:message key="user.email"/></th>
        <th><fmt:message key="user.active"/></th>
        <th><fmt:message key="user.registered"/></th>
        <th><fmt:message key="user.roles"/></th>
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