<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>
<%@ page import="ru.tbcarus.topjava.web.SecurityUtil" %>

<html lang="ru">
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script src="resources/js/tbcarus.common.js" defer></script>
<script src="resources/js/tbcarus.dishes.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
<h3><a href="/vote"><spring:message code="app.home"/></a></h3>
<hr>
${DateTimeUtil.toString(DateTimeUtil.getNow())}
<br>
UserId - ${SecurityUtil.authUserId()}
<h2><spring:message code="dishes.tittle"/></h2>
        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>
<a href="dishes/create"><spring:message code="dishes.add"/> - новая страница</a>
<table class="table table-striped mt-3" id="datatable">
    <thead>
    <tr>
        <th colspan="3">
            <c:set var="restaurant" value="${requestScope.restaurant}"/>
            <jsp:useBean id="restaurant" type="ru.tbcarus.topjava.model.Restaurant"/>
            ${restaurant.name} - ${restaurant.id()}
        </th>
    </tr>
    <tr>
        <th><spring:message code="dishes.name"/></th>
        <th><spring:message code="dishes.price"/></th>
        <th><spring:message code="dishes.date"/></th>
        <th><spring:message code="common.delete"/></th>
    </tr>
    </thead>
    <c:forEach var="dish" items="${requestScope.dishes}">
        <jsp:useBean id="dish" type="ru.tbcarus.topjava.model.Dish"/>
        <tr>
            <td>
                <a href="dishes/update?id=${dish.id}">${dish.name}</a>
            </td>
            <td>
                ${dish.price}
            </td>
            <td>
                ${dish.inputDate}
            </td>
            <td>
                <a href="dishes/delete?id=${dish.id}&restaurantId=${restaurant.id}"><spring:message code="common.delete"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="user.add"/></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input hidden type="text" id="id" name="restaurantId" value="${restaurant.id()}">

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="dishes.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="dishes.name"/>">
                    </div>

                    <div class="form-group">
                        <label for="price" class="col-form-label"><spring:message code="dishes.price"/></label>
                        <input type="number" min="1" class="form-control" id="price" name="price"
                               placeholder="<spring:message code="dishes.price"/>">
                    </div>

                    <div class="form-group">
                        <label for="price" class="col-form-label"><spring:message code="dishes.date"/></label>
                        <input type="date" class="form-control" id="date" name="date" value="${DateTimeUtil.getNow().toLocalDate()}"/>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>