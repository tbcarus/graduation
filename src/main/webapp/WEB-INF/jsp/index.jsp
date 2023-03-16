<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

        <sec:authorize access="hasRole('ADMIN')">
            <form method="post" action="users?${_csrf.parameterName}=${_csrf.token}">
                <b style="font-size: large"><spring:message code="user.title"/></b>
                <button type="submit" name="button" value="toUsers"><spring:message code="common.go"/></button>
            </form>
        </sec:authorize>

        <form method="post" action="votes">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <b style="font-size: large"><spring:message code="vote.title"/></b>
            <button type="submit" name="button" value="toVotes"><spring:message code="common.go"/></button>
        </form>

        <sec:authorize access="hasRole('ADMIN')">
            <form:form method="post" action="restaurants">
                <b style="font-size: large"><spring:message code="restaurant.tittle"/></b>
                <button type="submit"><spring:message code="common.go"/></button>
            </form:form>
        </sec:authorize>

        <form:form method="post" action="restaurants/voting">
            <b style="font-size: large"><spring:message code="restaurant.tittle"/></b>
            <button type="submit"><spring:message code="vote.vote"/></button>
        </form:form>

        <sec:authorize access="hasRole('ADMIN')">
            <form:form method="post" action="dishes">
                <b style="font-size: large"><spring:message code="dishes.tittle"/></b>
                <select name="restaurantId">
                    <c:forEach var="restaurant" items="${requestScope.restaurants}">
                        <jsp:useBean id="restaurant" type="ru.tbcarus.topjava.model.Restaurant"/>
                        <option value="${restaurant.id}">${restaurant.name}</option>
                    </c:forEach>
                </select>
                <button type="submit"><spring:message code="common.go"/></button>
            </form:form>
        </sec:authorize>

        <hr>
        <h4><a href="votes/today"><spring:message code="vote.today"/></a></h4>
        <h4><a href="restaurants/result"><spring:message code="common.results"/></a></h4>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>