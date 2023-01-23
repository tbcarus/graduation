<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="ru.tbcarus.topjava.util.DateTimeUtil" %>
<%@ page import="ru.tbcarus.topjava.web.SecurityUtil" %>

<h3><a href="/vote"><spring:message code="app.home"/></a></h3>
<h4><a href="restaurants/result"><spring:message code="common.results"/></a></h4>
<hr>
${DateTimeUtil.toString(DateTimeUtil.getNow())}
<br>
UserId - ${SecurityUtil.authUserId()}