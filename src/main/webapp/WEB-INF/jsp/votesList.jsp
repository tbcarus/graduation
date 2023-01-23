<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sprinh" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>

<html lang="ru">
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <jsp:include page="fragments/bodyCommon.jsp"/>
        <h2><spring:message code="vote.today"/></h2>
        <table class="table table-striped mt-3" style="width: 400px; margin-left:auto;margin-right:auto">
            <thead>
            <tr style="text-align: center">
                <th><spring:message code="vote.userName"/></th>
                <th><spring:message code="restaurant.restaurant"/></th>
            </tr>
            </thead>
            <c:forEach var="vote" items="${requestScope.votes}">
                <jsp:useBean id="vote" type="ru.tbcarus.topjava.model.Vote"/>
                <tr style="text-align: center">
                    <td>${vote.user.name}</td>
                    <td>${vote.restaurant.name}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>