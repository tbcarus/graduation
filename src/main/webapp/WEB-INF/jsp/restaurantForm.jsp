<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="ru">
<head>
    <title><spring:message code="restaurant.restaurant"/></title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<section>
    <h3><a href="/vote"><spring:message code="app.home"/></a></h3>
    <hr>
    <h2>${param.id != null ? 'Edit restaurant' : 'Add restaurant'}</h2>
    <jsp:useBean id="restaurant" type="ru.tbcarus.topjava.model.Restaurant" scope="request"/>
    <form method="post" action="create-or-update">
        <input type="hidden" name="id" value="${restaurant.id}">
        <table border="0">
            <tr>
                <td><spring:message code="user.name"/>:</td>
                <td><input type="text" name="name" value="${restaurant.name}"></td>
            </tr>
        </table>
        <button type="submit" name="button" name="user"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
</body>
</html>
