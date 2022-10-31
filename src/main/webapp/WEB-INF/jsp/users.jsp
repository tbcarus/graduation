<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>

<html lang="ru">
<jsp:include page="fragments/headTag.jsp"/>
<body>
<h3><a href="/vote"><spring:message code="app.home"/></a></h3>
<hr>
<c:set var="selectedUser" value="${requestScope.user}"/>
<jsp:useBean id="selectedUser" class="ru.tbcarus.topjava.model.User" scope="request"/>
${selectedUser.name} - ${selectedUser.id}
<h2><spring:message code="user.title"/></h2>
<a href="users/create"><spring:message code="user.add"/></a>
<br><br>
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
            <td><a href="users/update?id=${user.id}"><spring:message code="common.update"/></a></td>
            <td><a href="users/delete?id=${user.id}"><spring:message code="common.delete"/></a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>