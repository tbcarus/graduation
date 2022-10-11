<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html lang="ru">
<head>
    <title><fmt:message key="vote.vote"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="/vote"><fmt:message key="app.home"/></a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'To vote' : 'Edit vote'}</h2>
    <jsp:useBean id="vote" type="ru.tbcarus.topjava.model.Vote" scope="request"/>
    <form method="post" action="votes">
        <input type="hidden" name="id" value="${vote.id}">
        <dl>
            <dt><fmt:message key="restaurant.restaurant"/>:</dt>
            <dd>
                <select name="restaurant">
                    <c:forEach var="r" items="${requestScope.restaurants}">
                        <jsp:useBean id="r" type="ru.tbcarus.topjava.model.Restaurant"/>
                        <option value="${r.id}">${r.name}</option>
                    </c:forEach>
                </select>
            </dd>
        </dl>
        <button type="submit"><fmt:message key="common.save"/></button>
        <button onclick="window.history.back()" type="button"><fmt:message key="common.cancel"/></button>
    </form>
</section>
</body>
</html>
