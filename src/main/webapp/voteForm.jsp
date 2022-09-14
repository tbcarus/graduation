<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Vote</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'To vote' : 'Edit vote'}</h2>
    <jsp:useBean id="vote" type="ru.tbcarus.topjava.model.Vote" scope="request"/>
    <form method="post" action="votes">
        <input type="hidden" name="id" value="${vote.id}">
        <dl>
            <dt>Restaurant:</dt>
            <dd>
                <select name="restaurant">
                    <c:forEach var="r" items="${requestScope.restaurants}">
                        <jsp:useBean id="r" type="ru.tbcarus.topjava.model.Restaurant"/>
                        <option value="${r.id}">${r.name}</option>
                    </c:forEach>
                </select>
            </dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
