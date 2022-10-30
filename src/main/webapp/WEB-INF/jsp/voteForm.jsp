<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="ru">
<head>
    <title><spring:message code="vote.vote"/></title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<section>
    <h3><a href="/vote"><spring:message code="app.home"/></a></h3>
    <hr>
    <h2>${param.id != null ? 'Edit vote' : 'To vote'}</h2>
    <jsp:useBean id="vote" type="ru.tbcarus.topjava.model.Vote" scope="request"/>
    <form method="post" action="create-or-update">
        <input type="hidden" name="id" value="${vote.id}">
        <dl>
            <dt><spring:message code="restaurant.restaurant"/>:</dt>
            <dd>
                <select name="restaurant">
                    <c:forEach var="r" items="${requestScope.restaurants}">
                        <jsp:useBean id="r" type="ru.tbcarus.topjava.model.Restaurant"/>
                        <option value="${r.id}">${r.name}</option>
                    </c:forEach>
                </select>
            </dd>
        </dl>
        <button type="submit" name="button" name="vote" ><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
</body>
</html>
