<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>

<fmt:setBundle basename="messages.app"/>

<html lang="ru">
<head>
    <title><fmt:message key="vote.title"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h3><a href="/vote"><fmt:message key="app.home"/></a></h3>
<hr>
${DateTimeUtil.toString(DateTimeUtil.getNow())}
<h2><fmt:message key="vote.title"/></h2>
<a href="votes?action=create"><fmt:message key="vote.toVote"/></a>
<br><br>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th><fmt:message key="vote.date"/></th>
        <th><fmt:message key="vote.userName"/></th>
        <th><fmt:message key="restaurant.restaurant"/></th>
    </tr>
    </thead>
    <c:forEach var="vote" items="${requestScope.votesTo}">
        <jsp:useBean id="vote" type="ru.tbcarus.topjava.to.VoteTo"/>
        <tr>
            <td>${vote.id}</td>
            <td>${vote.date}</td>
            <c:set var="user" value="${vote.user}"/>
            <jsp:useBean id="user" type="ru.tbcarus.topjava.model.User"/>
            <td>${user.name}</td>
            <c:set var="restaurant" value="${vote.restaurant}"/>
            <jsp:useBean id="restaurant" type="ru.tbcarus.topjava.model.Restaurant"/>
            <td>${restaurant.name}</td>
            <td>
                <c:if test="${vote.canRevote}">
                    <a href="votes?action=update&id=${vote.id}"><fmt:message key="common.update"/></a>
                </c:if>
                <c:if test="${!vote.canRevote}">
                    Can't revote
                </c:if>
            </td>
            <td><a href="votes?action=delete&id=${vote.id}"><fmt:message key="common.delete"/></a>
            </td>
        </tr>

    </c:forEach>
</table>

</body>
</html>