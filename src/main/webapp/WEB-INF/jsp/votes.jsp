<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sprinh" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>

<html lang="ru">
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script src="resources/js/tbcarus.common.js" defer></script>
<script src="resources/js/tbcarus.votes.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <jsp:include page="fragments/bodyCommon.jsp"/>
        <h2><spring:message code="vote.title"/></h2>
        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>
        <a href="votes/create"><spring:message code="vote.toVote"/> - новая страница</a>
        <br><br>
        <table class="table table-striped mt-3" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="vote.date"/></th>
                <th><spring:message code="vote.userName"/></th>
                <th><spring:message code="restaurant.restaurant"/></th>
                <th>Revote</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
<%--            <c:forEach var="vote" items="${requestScope.votesTo}">--%>
<%--                <jsp:useBean id="vote" type="ru.tbcarus.topjava.to.VoteTo"/>--%>
<%--                <tr>--%>
<%--                    <td>${vote.date}</td>--%>
<%--                    <c:set var="user" value="${vote.user}"/>--%>
<%--                    <jsp:useBean id="user" type="ru.tbcarus.topjava.model.User"/>--%>
<%--                    <td>${user.name}</td>--%>
<%--                    <c:set var="restaurant" value="${vote.restaurant}"/>--%>
<%--                    <jsp:useBean id="restaurant" type="ru.tbcarus.topjava.model.Restaurant"/>--%>
<%--                    <td>${restaurant.name}</td>--%>
<%--                    <td>--%>
<%--                        <c:if test="${vote.canRevote}">--%>
<%--                            <a class="edit" id = "${restaurant.id}" style="cursor: pointer" onclick="updateRow('${vote.id}')">--%>
<%--                                <span class="fa fa-pencil"></span><spring:message code="common.update"/> - modal--%>
<%--                            </a>--%>
<%--                            <br>--%>
<%--                            <a href="votes/update?id=${vote.id}">--%>
<%--                                <span class="fa fa-pencil"><spring:message code="common.update"/> - JSP</a>--%>
<%--                        </c:if>--%>
<%--                        <c:if test="${!vote.canRevote}">--%>
<%--                            Can't revote--%>
<%--                        </c:if>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <a class="delete" style="cursor: pointer" onclick="deleteRow(${vote.id})">--%>
<%--                            <span class="fa fa-remove"></span><spring:message code="common.delete"/> - AJAX</a>--%>
<%--                        <br>--%>
<%--                        <a href="votes/delete?id=${vote.id}">--%>
<%--                            <span class="fa fa-remove"></span><spring:message code="common.delete"/> - JSP</a>--%>
<%--                    </td>--%>
<%--                </tr>--%>

<%--            </c:forEach>--%>
            </tbody>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="vote.vote"/></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="restaurantId" class="col-form-label"><spring:message code="restaurant.restaurant"/></label>
                        <select class="form-control" id="restaurantId" name="restaurantId">
                            <option value="" selected disabled hidden><spring:message code="common.select"/></option>
                            <c:forEach var="r" items="${requestScope.restaurants}">
                                <jsp:useBean id="r" type="ru.tbcarus.topjava.model.Restaurant"/>
                                <option value="${r.id}">${r.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
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