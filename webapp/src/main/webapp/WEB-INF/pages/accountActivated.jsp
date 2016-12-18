<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="page.accountActivated.title"/></title>
    <%@ include file="header.jsp" %>
</head>
<body>

<%@ include file="navbar.jsp" %>

<header>
    <h1 class="text-center"></h1>
</header>
<br/>
<br/>
<div class="wrapper container">
    <h2><spring:message code="page.accountActivated.message.1"/></h2>
    <h3>
        <spring:message code="page.accountActivated.message.2"/>&nbsp;
        <a href='<spring:url value="/index"/>'><spring:message code="page.accountActivated.message.3"/></a>&nbsp;
        <spring:message code="page.accountActivated.message.4"/>
    </h3>
</div>

</body>
</html>
