<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="ru">
<jsp:include page="fragments/headTag.jsp"/>
<body>
<h3>Проект <a href="https://github.com/JavaWebinar/topjava" target="_blank">Java Enterprise (Topjava)</a></h3>
<hr>
<c:set var="activeUser" value="${requestScope.activeUser}"/>
<jsp:useBean id="activeUser" type="ru.tbcarus.topjava.model.User"/>
Active User: ${activeUser.id()} - ${activeUser.name}
<br><br>
<form method="post" action="users">
    <b style="font-size: large"><spring:message code="user.title"/></b>
    <select name="userId">
            <c:forEach var="user" items="${requestScope.users}">
                <option value="${user.id}">${user.name}</option>
            </c:forEach>
    </select>
    <button type="submit" name="button" value="toUsers"><spring:message code="common.select"/></button>
</form>

<form method="post" action="votes">
    <b style="font-size: large"><spring:message code="vote.title"/></b>
    <select name="userId">
        <c:forEach var="user" items="${requestScope.users}">
            <option value="${user.id}">${user.name}</option>
        </c:forEach>
    </select>
    <button type="submit" name="button" value="toVotes"><spring:message code="common.select"/></button>
</form>
<form method="post" action="restaurants">
    <b style="font-size: large"><spring:message code="restaurant.tittle"/></b>
    <select name="userId">
        <c:forEach var="user" items="${requestScope.users}">
            <option value="${user.id}">${user.name}</option>
        </c:forEach>
    </select>
    <button type="submit"><spring:message code="common.select"/></button>
</form>
<form method="post" action="dishes">
    <b style="font-size: large"><spring:message code="dishes.tittle"/></b>
    <select name="userId">
        <c:forEach var="user" items="${requestScope.users}">
            <option value="${user.id}">${user.name}</option>
        </c:forEach>
    </select>
    <select name="restaurantId">
        <c:forEach var="restaurant" items="${requestScope.restaurants}">
            <jsp:useBean id="restaurant" type="ru.tbcarus.topjava.model.Restaurant"/>
            <option value="${restaurant.id}">${restaurant.name}</option>
        </c:forEach>
    </select>
    <button type="submit"><spring:message code="common.select"/></button>
</form>
</body>
</html>