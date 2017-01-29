<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="page.error.title"/></title>
    <%@ include file="../header.jsp" %>
    <jwr:style src="/bundles/main.css"/>
    <jwr:script src="/bundles/main.js"/>
</head>
<body>

<%@ include file="../navbar.jsp" %>

<header>
    <h1 class="text-center"></h1>
</header>

<div class="wrapper container">
    <h1><spring:message code="page.error.message.general"/></h1>
    <h2><spring:message code="page.error.message.403"/></h2>
    <h3><spring:message code="page.error.feedback"/></h3>
</div>

</body>
</html>
