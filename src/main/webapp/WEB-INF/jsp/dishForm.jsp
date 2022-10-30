<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html lang="ru">
<head>
    <title><fmt:message key="dishes.add"/></title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<section>
    <h3><a href="/vote"><fmt:message key="app.home"/></a></h3>
    <hr>
    <h2>${param.id != null ? 'Edit dish' : 'Add dish'}</h2>
    <jsp:useBean id="dish" type="ru.tbcarus.topjava.model.Dish" scope="request"/>
    <form method="post" action="create-or-update">
        <input type="hidden" name="id" value="${dish.id}">
        <dl>
            <dt><fmt:message key="dishes.dish"/>:</dt>
        </dl>
        <table border="1">
            <tr>
                <td><fmt:message key="dishes.name"/></td>
                <td><input type="text" value="${dish.name}" name="name"></td>
            </tr>
            <tr>
                <td><fmt:message key="dishes.price"/></td>
                <td><input type="number" min="1" value=${dish.price} name="price"></td>
            </tr>
            <tr>
                <td><fmt:message key="dishes.date"/></td>
                <td>
                    <c:if test="${param.id == null}">
                        <input type="date" value="${dish.inputDate}" name="date">
                    </c:if>
                    <c:if test="${param.id != null}">
                        <input type="date" value="${dish.inputDate}" name="date" disabled>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="restaurant.restaurant"/></td>
                <td>
                    <c:if test="${param.id != null}">
                        <input type="text" value=${dish.restaurant.name} disabled>
                        <input type="hidden" value="${dish.restaurant.id}" name="restaurantId">
                    </c:if>
                    <c:if test="${param.id == null}">
                        <select name="restaurantId">
                            <c:forEach var="r" items="${requestScope.restaurants}">
                                <jsp:useBean id="r" type="ru.tbcarus.topjava.model.Restaurant"/>
                                <option value="${r.id}">${r.name}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                </td>
            </tr>
        </table>
        <button type="submit" name="button" name="addDish"><fmt:message key="common.save"/></button>
        <button onclick="window.history.back()" type="button"><fmt:message key="common.cancel"/></button>
    </form>
</section>
</body>
</html>
