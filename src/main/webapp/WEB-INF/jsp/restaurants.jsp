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
<h2><spring:message code="restaurant.tittle"/></h2>
<a href="restaurants/create"><spring:message code="restaurant.add"/></a>
   <br><br>
<table border="1">
    <thead>
    <tr>
        <th><spring:message code="restaurant.tittle"/></th>
        <th><spring:message code="restaurant.menu"/></th>
        <th><spring:message code="restaurant.vote"/></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach var="restaurant" items="${requestScope.restaurants}">
        <jsp:useBean id="restaurant" type="ru.tbcarus.topjava.model.Restaurant"/>
        <tr>
            <td><a href="restaurants/update?id=${restaurant.id}">${restaurant.name}</a></td>
            <td>
                <table border="1">
                    <thead>
                    <tr>
                        <th width="300px"><spring:message code="restaurant.dishName"/></th>
                        <th><spring:message code="restaurant.price"/></th>
                        <th><spring:message code="restaurant.date"/></th>
                    </tr>
                    </thead>
                    <c:forEach var="dish" items="${restaurant.dishes}">
                        <jsp:useBean id="dish" type="ru.tbcarus.topjava.model.Dish"/>

                        <tr>
                            <td>${dish.name}</td>
                            <td>${dish.price}</td>
                            <td>${dish.inputDate}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
            <td>
                <form method="post" action="votes/create-or-update">
                    <input type="hidden" name="restaurant" value="${restaurant.id}">
                    <button type="submit"><spring:message code="restaurant.vote"/></button>
                </form>
            </td>
            <td>
                <form method="get" action="dishes/create">
                    <input type="hidden" name="restaurant" value="${restaurant.id}">
                    <button type="submit"><spring:message code="dishes.add"/></button>
                </form>
            </td>
            <td>
                <a href="restaurants/delete?id=${restaurant.id}"><spring:message code="common.delete"/></a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>