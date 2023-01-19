<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>
<%@ page import="ru.tbcarus.topjava.web.SecurityUtil" %>

<html lang="ru">
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script src="resources/js/tbcarus.common.js" defer></script>
<script src="resources/js/tbcarus.restaurantsVoting.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <h3><a href="/vote"><spring:message code="app.home"/></a></h3>
        <hr>
        ${DateTimeUtil.toString(DateTimeUtil.getNow())}
        <br>
        UserId - ${SecurityUtil.authUserId()}
        <h2><spring:message code="restaurant.tittle"/></h2>
        <table border="1" class="table table-striped mt-3" id="datatable">
            <thead>
            <tr>
                <th>Текущий выбор</th>
                <th>Можно переголосовать?</th>
                <th>Дата</th>
            </tr>
            </thead>
            <tr>
                <c:set var="currentChoice" value="${requestScope.currentChoice}"/>
                <jsp:useBean id="currentChoice" class="ru.tbcarus.topjava.model.Vote"/>
                <td>${currentChoice.restaurant.name}</td>
                <td></td>
                <td>${currentChoice.date}</td>
            </tr>
        </table>
        <br><br>

        <table border="1" class="table table-striped mt-3">
            <thead>
            <tr>
                <th><spring:message code="restaurant.restaurant"/></th>
                <th><spring:message code="dishes.dish"/></th>
                <th><spring:message code="dishes.price"/></th>
                <th><spring:message code="dishes.date"/></th>
                <th><spring:message code="restaurant.vote"/></th>
            </tr>
            </thead>
            <c:forEach var="restaurant" items="${requestScope.restaurants}">
                <jsp:useBean id="restaurant" type="ru.tbcarus.topjava.model.Restaurant"/>
                <c:set var="rows" value="${restaurant.dishes.size()}"/>
                <c:if test="${rows != 0}">
                    <tr style="border-top-style: solid; border-top-width: 3px">
                    <td style="vertical-align: middle" rowspan="${rows}">
                            ${restaurant.name}-${restaurant.dishes.size()}</a>
                    </td>

                    <c:set var="isFirst" value="${true}"/>
                    <c:forEach var="dish" items="${restaurant.dishes}">
                        <c:if test="${!isFirst}">
                            <tr>
                        </c:if>

                        <td>${dish.name}</td>
                        <td>${dish.price}</td>
                        <td>${dish.inputDate}</td>

                        <c:if test="${!isFirst}">
                            </tr>
                        </c:if>
                        <c:if test="${isFirst}">
                            <td style="vertical-align: middle" rowspan="${rows}">
                                <a class="vote" style="cursor: pointer" onclick="vote('${restaurant.id}', '${currentChoice.id}')">
                                    <spring:message code="vote.toVote"/> - AJAX</a>
                                <br>
                                <a href="restaurants/voting?restaurantId=${restaurant.id}&id=${currentChoice.id}">
                                    <spring:message code="vote.toVote"/> - JSP</a>
                            </td>
                            </tr>
                            <c:set var="isFirst" value="${false}"/>
                        </c:if>
                    </c:forEach>

                </c:if>
            </c:forEach>
        </table>

    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>