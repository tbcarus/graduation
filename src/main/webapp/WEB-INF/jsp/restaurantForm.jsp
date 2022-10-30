<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.tbcarus.topjava.model.Role" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>

<fmt:setBundle basename="messages.app"/>

<html lang="ru">
<head>
    <title><fmt:message key="restaurant.restaurant"/></title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<section>
    <h3><a href="/vote"><fmt:message key="app.home"/></a></h3>
    <hr>
    <h2>${param.id != null ? 'Edit restaurant' : 'Add restaurant'}</h2>
    <jsp:useBean id="restaurant" type="ru.tbcarus.topjava.model.Restaurant" scope="request"/>
    <form method="post" action="create-or-update">
        <input type="hidden" name="id" value="${restaurant.id}">
        <table border="0">
            <tr>
                <td><fmt:message key="user.name"/>:</td>
                <td><input type="text" name="name" value="${restaurant.name}"></td>
            </tr>
        </table>
        <button type="submit" name="button" name="user"><fmt:message key="common.save"/></button>
        <button onclick="window.history.back()" type="button"><fmt:message key="common.cancel"/></button>
    </form>
</section>
</body>
</html>
