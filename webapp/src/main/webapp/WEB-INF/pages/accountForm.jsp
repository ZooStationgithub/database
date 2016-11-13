<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="page.accountForm.title"/></title>
    <link rel="stylesheet" href='<spring:url value="/assets/css/style.css"/>'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src='<spring:url value="/assets/js/lib/jquery-3.1.1.min.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/lib/bootstrap.min.js"/>' type="application/javascript"></script>
</head>
<body>

<%@ include file="navbar.jsp" %>

<header>
    <h1 class="text-center"></h1>
</header>

<div class="wrapper container">
    <form:form commandName="account" action="${pageContext.servletContext.contextPath}/account/form" method="post" >

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="username"><spring:message code="form.account.email"/></label>
            <div class="col-xs-10">
                <form:input path="email" cssClass="form-control" id="username"/>
                <form:errors path="email" cssClass="error"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="password"><spring:message code="form.account.password"/></label>
            <div class="col-xs-10">
                <form:password path="password" cssClass="form-control" id="password"/>
                <form:errors path="password" cssClass="error"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="confirm-password"><spring:message code="form.account.password.confirm"/></label>
            <div class="col-xs-10">
                <form:password path="confirmPassword" cssClass="form-control" id="confirm-password"/>
                <form:errors path="confirmPassword" cssClass="error"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="group"><spring:message code="form.account.group"/></label>
            <div class="col-xs-10">
                <form:select path="groupId" cssClass="form-control input-sm" items="${groups}" itemLabel="displayName" itemValue="id"
                             id="group"/>
            </div>
        </div>

        <div class="text-center">
            <button type="submit" class="btn  btn-primary">
                <i class="glyphicon glyphicon-floppy-disk"></i>
                <spring:message code="common.keyword.save"/>
            </button>
        </div>

    </form:form>
</div>

</body>
</html>