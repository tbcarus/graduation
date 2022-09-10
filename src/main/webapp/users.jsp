<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>

<html lang="ru">
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Users</h2>
<c:set var="user" value="${requestScope.user}"/>
<jsp:useBean id="user" type="ru.tbcarus.topjava.model.User"/>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>E-mail</th>
        <th>Enabled</th>
        <th>Registered</th>
        <th>Roles</th>
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