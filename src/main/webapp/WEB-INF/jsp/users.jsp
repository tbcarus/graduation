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
<c:set var="selectedUser" value="${requestScope.user}"/>
<jsp:useBean id="selectedUser" class="ru.tbcarus.topjava.model.User" scope="request"/>
${selectedUser.name} - ${selectedUser.id}
<h2><fmt:message key="user.title"/></h2>
<a href="users/create"><fmt:message key="user.add"/></a>
<br><br>
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
    <c:forEach var="user" items="${requestScope.users}">
        <jsp:useBean id="user" class="ru.tbcarus.topjava.model.User"/>
        <tr>
            <td>${user.id}</td>
            <td><a href="users/${user.id}">${user.name}</a></td>
            <td>${user.email}</td>
            <td>${user.enabled}</td>
            <td>${DateTimeUtil.toString(user.registered)}</td>
            <td>
                <c:forEach var="role" items="${user.roles}">
                    <jsp:useBean id="role" type="ru.tbcarus.topjava.model.Role"/>
                    ${role.name()}
                </c:forEach>
            </td>
            <td><a href="users/update?id=${user.id}"><fmt:message key="common.update"/></a></td>
            <td><a href="users/delete?id=${user.id}"><fmt:message key="common.delete"/></a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>