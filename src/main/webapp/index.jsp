<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="app.title"/></title>
</head>
<body>
<h3>Проект <a href="https://github.com/JavaWebinar/topjava" target="_blank">Java Enterprise (Topjava)</a></h3>
<hr>
<form method="post" action="users">
    <b style="font-size: large"><fmt:message key="user.title"/></b>
    <select name="userId">
        <option value="100000">Admin</option>
        <option value="100001">Ivan</option>
        <option value="100002">Maria</option>
        <option value="100003">Vasily</option>
    </select>
    <button type="submit" name="button" value="toUsers"><fmt:message key="common.select"/></button>
</form>

<form method="post" action="users">
    <b style="font-size: large"><fmt:message key="vote.title"/></b>
    <select name="userId">
        <option value="100000">Admin</option>
        <option value="100001">Ivan</option>
        <option value="100002">Maria</option>
        <option value="100003">Vasily</option>
    </select>
    <button type="submit" name="button" value="toVotes"><fmt:message key="common.select"/></button>
</form>
<form method="post" action="restaurants">
    <b style="font-size: large"><fmt:message key="restaurant.tittle"/></b>
    <select name="userId">
        <option value="100000">Admin</option>
        <option value="100001">Ivan</option>
        <option value="100002">Maria</option>
        <option value="100003">Vasily</option>
    </select>
    <button type="submit"><fmt:message key="common.select"/></button>
</form>
<form method="post" action="dishes">
    <b style="font-size: large"><fmt:message key="dishes.tittle"/></b>
    <select name="userId">
        <option value="100000">Admin</option>
        <option value="100001">Ivan</option>
        <option value="100002">Maria</option>
        <option value="100003">Vasily</option>
    </select>
    <select name="restaurantId">
        <option value="100004">KFC</option>
        <option value="100005">McDonalds</option>
        <option value="100006">Burger King</option>
    </select>
    <button type="submit"><fmt:message key="common.select"/></button>
</form>
</body>
</html>