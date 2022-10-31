<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="ru">
<jsp:include page="fragments/headTag.jsp"/>
<body>
<section>
    <h3><a href="/vote"><spring:message code="app.home"/></a></h3>
    <hr>
    <h2>${param.id != null ? 'Edit dish' : 'Add dish'}</h2>
    <jsp:useBean id="dish" type="ru.tbcarus.topjava.model.Dish" scope="request"/>
    <form method="post" action="create-or-update">
        <input type="hidden" name="id" value="${dish.id}">
        <dl>
            <dt><spring:message code="dishes.dish"/>:</dt>
        </dl>
        <table border="1">
            <tr>
                <td><spring:message code="dishes.name"/></td>
                <td><input type="text" value="${dish.name}" name="name"></td>
            </tr>
            <tr>
                <td><spring:message code="dishes.price"/></td>
                <td><input type="number" min="1" value=${dish.price} name="price"></td>
            </tr>
            <tr>
                <td><spring:message code="dishes.date"/></td>
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
                <td><spring:message code="restaurant.restaurant"/></td>
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
        <button type="submit" name="button" name="addDish"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
</body>
</html>
