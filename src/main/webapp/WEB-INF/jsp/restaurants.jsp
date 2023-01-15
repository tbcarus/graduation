<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>

<html lang="ru">
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script src="resources/js/tbcarus.common.js" defer></script>
<script src="resources/js/tbcarus.restaurants.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <h3><a href="/vote"><spring:message code="app.home"/></a></h3>
        <hr>
        ${DateTimeUtil.toString(DateTimeUtil.getNow())}
        <h2><spring:message code="restaurant.tittle"/></h2>
        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>
        <a href="restaurants/create"><spring:message code="restaurant.add"/> - новая страница</a>
        <br><br>

        <table border="1" class="table table-striped mt-3" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="restaurant.tittle"/></th>
                <th><spring:message code="restaurant.vote"/></th>
                <th><spring:message code="common.delete"/></th>
            </tr>
            </thead>
            <c:forEach var="restaurant" items="${requestScope.restaurants}">
                <jsp:useBean id="restaurant" type="ru.tbcarus.topjava.model.Restaurant"/>
                <tr style="border-top-style: solid; border-top-width: 3px">
                    <td style="vertical-align: middle">
                        ${restaurant.name}-${restaurant.dishes.size()}</a>
                    </td>
                    <td style="vertical-align: middle">
                        <a class="edit" id="${restaurant.id}" style="cursor: pointer" onclick="edit('${restaurant.id}')">
                            <span class="fa fa-pencil"></span><spring:message code="common.update"/> - modal
                        </a>
                        <br>
                        <a href="restaurants/update?id=${restaurant.id}">
                            <span class="fa fa-pencil"><spring:message code="common.update"/> - JSP</a>
                    </td>
                    <td style="vertical-align: middle">
                        <a class="delete" style="cursor: pointer" onclick="deleteRow(${restaurant.id})">
                            <span class="fa fa-remove"></span><spring:message code="common.delete"/> - AJAX</a>
                        <br>
                        <a href="restaurants/delete?id=${restaurant.id}">
                            <span class="fa fa-remove"></span><spring:message code="common.delete"/> - JSP</a>
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
                <h4 class="modal-title"><spring:message code="restaurant.add"/></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="restaurant.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="restaurant.name"/>">
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