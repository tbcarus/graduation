<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>

<html>
<head>
    <title>Restaurants</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
${DateTimeUtil.toString(DateTimeUtil.getNow())}
<h2>Restaurants</h2>
<table border="1">
    <thead>
    <tr>
        <th>Restaurants</th>
        <th>Menu</th>
        <th>Vote</th>
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
                        <th width="500px">Name</th>
                        <th>Price</th>
                    </tr>
                    </thead>
                    <c:forEach var="dish" items="${restaurant.dishes}">
                        <jsp:useBean id="dish" type="ru.tbcarus.topjava.model.Dish"/>

                        <tr>
                            <td>${dish.name}</td>
                            <td>${dish.price}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
            <td><a href="votes?action=vote&restaurantId=${restaurant.id}">Vote</a></td>
        </tr>

    </c:forEach>
</table>

</body>
</html>