<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <%@ include file="header.jsp" %>
    <dandelion:bundle includes="global" />
</head>

<body>

<header>
    <h1 class="text-center"><spring:message code="page.login.title"/></h1>
</header>

<div class="wrapper container">
    <form action='<spring:url value="/login"/>' method="post">

        <c:if test="${param.error != null}">
            <div class="form-group row">
                <div class="alert alert-danger"><spring:message code="form.login.invalid"/></div>
            </div>
        </c:if>

        <c:if test="${param.logout != null}">
            <div class="form-group row">
                <div class="alert alert-success"><spring:message code="form.login.logout"/></div>
            </div>
        </c:if>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="username"><spring:message code="form.login.username"/></label>
            <div class="col-xs-10">
                <input name="username" type="text" class="form-control" id="username"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="password"><spring:message code="form.login.password"/></label>
            <div class="col-xs-10">
                <input type="password" name="password" class="form-control" id="password"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="remember-me"><spring:message code="form.login.rememberme"/></label>
            <div class="col-xs-10">
                <input type="checkbox" name="remember-me" class="form-check-input" id="remember-me">
            </div>
        </div>

        <div class="text-center">
            <button type="submit" class="btn  btn-primary">
                <i class="glyphicon glyphicon-user"></i>
                <spring:message code="common.keyword.submit"/>
            </button>
        </div>
    </form>
</div>

</body>
</html>