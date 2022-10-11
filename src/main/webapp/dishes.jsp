<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>
<%@ page import="ru.tbcarus.topjava.web.SecurityUtil" %>

<fmt:setBundle basename="messages.app"/>

<html lang="ru">
<head>
    <title><fmt:message key="dishes.tittle"/></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h3><a href="/vote"><fmt:message key="app.home"/></a></h3>
<hr>
${DateTimeUtil.toString(DateTimeUtil.getNow())}
<br>
UserId - ${SecurityUtil.authUserId()}
<h2><fmt:message key="dishes.tittle"/></h2>
<table border="1">
    <thead>
    <tr>
        <th colspan="3">
            <c:set var="restaurant" value="${requestScope.restaurant}"/>
            <jsp:useBean id="restaurant" type="ru.tbcarus.topjava.model.Restaurant"/>
            ${restaurant.name} - ${restaurant.id()}
        </th>
    </tr>
    <tr>
        <th><fmt:message key="dishes.date"/></th>
        <th><fmt:message key="dishes.price"/></th>
        <th><fmt:message key="dishes.date"/></th>
    </tr>
    </thead>
    <c:forEach var="dish" items="${requestScope.dishes}">
        <jsp:useBean id="dish" type="ru.tbcarus.topjava.model.Dish"/>
        <tr>
            <td>
                ${dish.name}
            </td>
            <td>
                ${dish.price}
            </td>
            <td>
                ${dish.inputDate}
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>