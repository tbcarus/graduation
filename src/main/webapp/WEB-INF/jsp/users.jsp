<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>

<html lang="ru">
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script src="resources/js/tbcarus.common.js" defer></script>
<script src="resources/js/tbcarus.users.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3><a href="/vote"><spring:message code="app.home"/></a></h3>
        <hr>
        <c:set var="selectedUser" value="${requestScope.user}"/>
        <jsp:useBean id="selectedUser" class="ru.tbcarus.topjava.model.User" scope="request"/>
        ${selectedUser.name} - ${selectedUser.id}
        <h3 class="text-center"><spring:message code="user.title"/></h3>
        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>
        <a href="users/create"><spring:message code="user.add"/> - новая страница</a>
        <br><br>
        <table class="table table-striped mt-3" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="user.name"/></th>
                <th><spring:message code="user.email"/></th>
                <th><spring:message code="user.active"/></th>
                <th><spring:message code="user.registered"/></th>
                <th><spring:message code="user.roles"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${requestScope.users}">
                <jsp:useBean id="user" class="ru.tbcarus.topjava.model.User"/>
                <tr id="${user.id}">
                    <td><a href="users/${user.id}">${user.name}</a></td>
                    <td>${user.email}</td>
                    <td>${user.enabled} <input type="checkbox" <c:if test="${user.enabled}">checked</c:if>></td>
                    <td>${DateTimeUtil.toString(user.registered)}</td>
                    <td>
                        <c:forEach var="role" items="${user.roles}">
                            <jsp:useBean id="role" type="ru.tbcarus.topjava.model.Role"/>
                            ${role.name()}
                        </c:forEach>
                    </td>
                    <td><a href="users/update?id=${user.id}"><span class="fa fa-pencil"></span><spring:message
                            code="common.update"/></a></td>
                        <%--                    <td><a href="users/delete?id=${user.id}"><span class="fa fa-remove"></span><spring:message code="common.delete"/></a></td>--%>
                    <td><a class="delete"><span class="fa fa-remove"></span><spring:message code="common.delete"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
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
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="user.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="user.name"/>">
                    </div>

                    <div class="form-group">
                        <label for="email" class="col-form-label"><spring:message code="user.email"/></label>
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="<spring:message code="user.email"/>">
                    </div>

                    <div class="form-group">
                        <label for="password" class="col-form-label"><spring:message code="user.password"/></label>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="<spring:message code="user.password"/>">
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