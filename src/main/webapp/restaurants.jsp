<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>

<fmt:setBundle basename="messages.app"/>

<html lang="ru">
<head>
    <title><fmt:message key="restaurant.tittle"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h3><a href="/vote"><fmt:message key="app.home"/></a></h3>
<hr>
${DateTimeUtil.toString(DateTimeUtil.getNow())}
<h2><fmt:message key="restaurant.tittle"/></h2>
<table border="1">
    <thead>
    <tr>
        <th><fmt:message key="restaurant.tittle"/></th>
        <th><fmt:message key="restaurant.menu"/></th>
        <th><fmt:message key="restaurant.vote"/></th>
    </tr>
    </thead>
    <c:forEach var="restaurant" items="${requestScope.restaurants}">
        <jsp:useBean id="restaurant" type="ru.tbcarus.topjava.model.Restaurant"/>
        <tr>
            <td>${restaurant.name}</td>
            <td>
                <table border="1">
                    <thead>
                    <tr>
                        <th width="300px"><fmt:message key="restaurant.dishName"/></th>
                        <th><fmt:message key="restaurant.price"/></th>
                        <th><fmt:message key="restaurant.date"/></th>
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
            <td><a href="votes?action=vote&restaurantId=${restaurant.id}"><fmt:message key="restaurant.vote"/></a></td>
        </tr>

    </c:forEach>
</table>

</body>
</html>