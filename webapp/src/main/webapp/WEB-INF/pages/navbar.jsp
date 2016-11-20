<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a target="_blank" href="javascript:void(0)" class="navbar-brand"><spring:message
                    code="navbar.app.name"/></a>
        </div>
        <div class="collapse navbar-collapse">
            <sec:authorize access="isAuthenticated()">
                <ul class="nav navbar-nav">
                    <li>
                        <a href='<spring:url value="/index"/>'>
                            <span class="glyphicon glyphicon-home"></span>
                            <spring:message code="navbar.home"/>
                        </a>
                    </li>
                    <sec:authorize access="hasAnyRole('ROLE_SU', 'ROLE_ADMIN', 'ROLE_ZS_USER')">
                        <li>
                            <a href='<spring:url value="/developer/edit"/>'>
                                <span class="glyphicon glyphicon-wrench"></span>
                                <spring:message code="navbar.newDeveloper"/>
                            </a>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_SU', 'ROLE_ADMIN')">
                        <li>
                            <a href='<spring:url value="/account/list"/>'>
                                <span class="glyphicon glyphicon-th-list"></span>
                                <spring:message code="navbar.accounts"/>
                            </a>
                        </li>
                    </sec:authorize>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-user"></span>
                            <strong><sec:authentication property="principal.username"/></strong>
                            <span class="glyphicon glyphicon-chevron-down"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <form action='<spring:url value="/performlogout"/>' method="post">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <input type="submit" id="btn-logout" class="hidden">
                                </form>
                                <a href="javascript:void(0)" onclick="$('#btn-logout').click()">
                                    Sign Out <span class="glyphicon glyphicon-log-out pull-right"></span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </sec:authorize>
        </div>
    </div>
</div>