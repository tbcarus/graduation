<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>
<%@ page import="ru.tbcarus.topjava.web.SecurityUtil" %>

<html lang="ru">
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
<h3><a href="/vote"><spring:message code="app.home"/></a></h3>
<hr>
${DateTimeUtil.toString(DateTimeUtil.getNow())}
<br>
UserId - ${SecurityUtil.authUserId()}
<h2><spring:message code="dishes.tittle"/></h2>
<a href="dishes/create"><spring:message code="dishes.add"/></a>
<table class="table table-striped mt-3">
    <thead>
    <tr>
        <th colspan="3">
            <c:set var="restaurant" value="${requestScope.restaurant}"/>
            <jsp:useBean id="restaurant" type="ru.tbcarus.topjava.model.Restaurant"/>
            ${restaurant.name} - ${restaurant.id()}
        </th>
    </tr>
    <tr>
        <th><spring:message code="dishes.name"/></th>
        <th><spring:message code="dishes.price"/></th>
        <th><spring:message code="dishes.date"/></th>
        <th><spring:message code="common.delete"/></th>
    </tr>
    </thead>
    <c:forEach var="dish" items="${requestScope.dishes}">
        <jsp:useBean id="dish" type="ru.tbcarus.topjava.model.Dish"/>
        <tr>
            <td>
                <a href="dishes/update?id=${dish.id}">${dish.name}</a>
            </td>
            <td>
                ${dish.price}
            </td>
            <td>
                ${dish.inputDate}
            </td>
            <td>
                <a href="dishes/delete?id=${dish.id}&restaurantId=${restaurant.id}"><spring:message code="common.delete"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>