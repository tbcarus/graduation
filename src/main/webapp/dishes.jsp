<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>
<%@ page import="ru.tbcarus.topjava.web.SecurityUtil" %>

<html>
<head>
    <title>Dishes</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
${DateTimeUtil.toString(DateTimeUtil.getNow())}
<br>
UserId - ${SecurityUtil.authUserId()}
<h2>Dishes</h2>
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
        <th>Dish</th>
        <th>Price</th>
        <th>Date</th>
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