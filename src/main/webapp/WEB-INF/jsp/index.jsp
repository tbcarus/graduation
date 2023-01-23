<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
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
        <form method="post" action="restaurants/voting">
            <b style="font-size: large"><spring:message code="restaurant.tittle"/></b>
            <select name="userId">
                <c:forEach var="user" items="${requestScope.users}">
                    <option value="${user.id}">${user.name}</option>
                </c:forEach>
            </select>
            <button type="submit"><spring:message code="vote.vote"/></button>
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
        <hr>
        <h4><a href="votes/today"><spring:message code="vote.today"/></a></h4>
        <h4><a href="restaurants/result"><spring:message code="common.results"/></a></h4>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>