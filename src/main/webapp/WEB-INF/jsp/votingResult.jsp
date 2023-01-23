<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="ru">
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <jsp:include page="fragments/bodyCommon.jsp"/>
        <table class="table table-striped mt-3 table-bordered" style="width: 300px; margin-left:auto;margin-right:auto">
            <thead>
            <tr>
                <th style="text-align: center"><spring:message code="restaurant.winner"/></th>
            </tr>
            </thead>
            <tr style="text-align: center">
                <td>
                    <c:set var="restaurant" value="${requestScope.winner}"/>

                    <c:if test="${restaurant == null}">
                        Победитель не определён
                    </c:if>
                    <c:if test="${restaurant != null}">
                        <jsp:useBean id="restaurant" type="ru.tbcarus.topjava.model.Restaurant"/>
                        ${restaurant.name}
                    </c:if>
                </td>
            </tr>
        </table>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>