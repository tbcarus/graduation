<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>

<html>
<head>
    <title>Votes</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Votes</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Date</th>
        <th>User name</th>
        <th>Restaurant</th>
    </tr>
    </thead>
    <c:forEach var="vote" items="${requestScope.votes}">
        <jsp:useBean id="vote" type="ru.tbcarus.topjava.model.Vote"/>
        <tr>
            <td>${vote.id}</td>
            <td>${vote.date}</td>
            <c:set var="user" value="${vote.user}"/>
            <jsp:useBean id="user" type="ru.tbcarus.topjava.model.User"/>
            <td>${user.name}</td>
            <c:set var="restaurant" value="${vote.restaurant}"/>
            <jsp:useBean id="restaurant" type="ru.tbcarus.topjava.model.Restaurant"/>
            <td>${restaurant.name}</td>
        </tr>

    </c:forEach>
</table>

</body>
</html>