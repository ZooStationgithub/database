<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="page.error.title"/></title>
    <%@ include file="../header.jsp" %>
    <link rel="stylesheet" href='<spring:url value="/assets/css/select2.min.css"/>'/>
</head>
<body>

<%@ include file="../navbar.jsp" %>

<header>
    <h1 class="text-center"></h1>
</header>

<div class="wrapper container">
    <h1><spring:message code="page.error.message.general"/></h1>
    <h2>${message}</h2>

    <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SU')">
        <c:if test="${not empty exceptionMessage}">
            <div id="panelFormAccount" class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="page.error.panel.header"/></h3>
                </div>
                <div class="panel-body collapse">
                    <div class="col-xs-12" style="font-family: monospace; white-space: pre">${exceptionMessage}</div>
                    <br/>
                    <c:if test="${not empty stackTrace}">
                        <div class="col-xs-12" style="font-family: monospace; white-space: pre">${stackTrace}</div>
                    </c:if>
                </div>
            </div>
        </c:if>
    </sec:authorize>

    <h3><spring:message code="page.error.feedback"/></h3>
</div>

</body>
</html>
