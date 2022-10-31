<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>

<html lang="ru">
<jsp:include page="fragments/headTag.jsp"/>
<body>
<h3><a href="/vote"><spring:message code="app.home"/></a></h3>
<hr>
${DateTimeUtil.toString(DateTimeUtil.getNow())}
<h2><spring:message code="vote.title"/></h2>
<a href="votes/create"><spring:message code="vote.toVote"/></a>
<br><br>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th><spring:message code="vote.date"/></th>
        <th><spring:message code="vote.userName"/></th>
        <th><spring:message code="restaurant.restaurant"/></th>
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
                    <a href="votes/update?id=${vote.id}"><spring:message code="common.update"/></a>
                </c:if>
                <c:if test="${!vote.canRevote}">
                    Can't revote
                </c:if>
            </td>
            <td><a href="votes/delete?id=${vote.id}"><spring:message code="common.delete"/></a>
            </td>
        </tr>

    </c:forEach>
</table>

</body>
</html>