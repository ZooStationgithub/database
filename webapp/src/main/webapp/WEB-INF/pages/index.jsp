<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="page.index.title"/></title>
    <%@ include file="header.jsp" %>
    <link rel="stylesheet" href='<spring:url value="/assets/css/token-input.css"/>' />
    <script src='<spring:url value="/assets/js/lib/jsrender.min.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/lib/jquery.dataTables.min.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/lib/dataTables.bootstrap.min.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/lib/jquery.tokeninput.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/index.js"/>' type="application/javascript"></script>
</head>
<body>

<%@ include file="navbar.jsp" %>

<header>
    <h1 class="text-center"></h1>
</header>

<nav class="search col-xs-12 col-sm-6 col-md-3">

    <form role="search" id="formSearch">
        <h2>
            <spring:message code="common.keyword.search"/>
            <button id="btnSearch" class="btn btn-primary pull-right">
                <i class="glyphicon glyphicon-search"></i>
            </button>
        </h2>

        <sec:authorize access="hasAnyRole('ROLE_SU', 'ROLE_ADMIN', 'ROLE_ZS_USER')">
        <div class="form-group">
            <label class="control-label" for="zsNumber"><spring:message code="form.developer.zsNumber"/></label>
            <div>
                <input type="text" name="zsNumber" value="${sessionScope.searchFilter.zsNumber}"
                       id="zsNumber" class="form-control"/>
            </div>
        </div>
        </sec:authorize>

        <div class="form-group">
            <label class="control-label"
                   for="srch-grade"><spring:message code="form.developer.grade"/></label>
            <div>
                <select name="rankTypeId" id="srch-grade" class="form-control input-sm">
                    <option value=""></option>
                    <c:forEach items="${rankTypes}" var="item">
                        <c:set var="selected" value="${sessionScope.searchFilter.rankTypeId eq item.id}" />
                        <option value="${item.id}" <c:if test="${selected}">selected</c:if>>${item.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="srch-mpl"><spring:message code="form.developer.mainProgrammingLanguage"/></label>
            <div>
                <select name="mainProgrammingLanguageId" id="srch-mpl" class="form-control input-sm">
                    <option value=""></option>
                    <c:forEach items="${languages}" var="item">
                        <c:set var="selected" value="${sessionScope.searchFilter.mainProgrammingLanguageId eq item.id}"/>
                        <option value="${item.id}" <c:if test="${selected}">selected</c:if>>${item.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="srch-spl"><spring:message code="form.developer.secondProgrammingLanguage"/></label>
            <div>
                <select name="secondProgrammingLanguageId" id="srch-spl" class="form-control input-sm">
                    <option value=""></option>
                    <c:forEach items="${languages}" var="item">
                        <c:set var="selected" value="${sessionScope.searchFilter.secondProgrammingLanguageId eq item.id}"/>
                        <option value="${item.id}" <c:if test="${selected}">selected</c:if>>${item.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label" for="srch-frameworks"><spring:message code="form.developer.frameworks"/></label>
            <div>
                <select name="knownFrameworkIds" multiple id="srch-frameworks" class="form-control input-sm">
                    <c:forEach items="${frameworks}" var="item">
                        <c:set var="selected" value="${sessionScope.searchFilter.knownFrameworkIds.contains(item.id)}"/>
                        <option value="${item.id}" <c:if test="${selected}">selected</c:if>>${item.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label" for="rating-cd"><spring:message code="form.developer.testRating"/></label>
            <div>
                <input type="number" name="testRating" value="${sessionScope.searchFilter.testRating}"
                       min="0" max="100" id="rating-cd" class="form-control input-sm" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label" for="country-origin"><spring:message code="form.developer.originCountry"/></label>
            <div>
                <%--<select name="originCountryId" id="country-origin" class="form-control input-sm">--%>
                    <%--<option value=""></option>--%>
                    <%--<c:forEach items="${countries}" var="item">--%>
                        <%--<c:set var="selected" value="${sessionScope.searchFilter.originCountryId eq item.id}"/>--%>
                        <%--<option value="${item.id}" <c:if test="${selected}">selected</c:if>>${item.name}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
                <input type="text" name="originCountryId" id="country-origin">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label" for="country-preferred"><spring:message code="form.developer.preferredCountries"/></label>
            <div>
                <select name="preferredCountryIds" multiple id="country-preferred" class="form-control input-sm">
                    <c:forEach items="${countries}" var="item">
                        <c:set var="selected" value="${sessionScope.searchFilter.preferredCountryIds.contains(item.id)}"/>
                        <option value="${item.id}" <c:if test="${selected}">selected</c:if>>${item.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label">
                <spring:message code="form.developer.contractType"/>
            </label>
            <div>
                <c:forEach items="${contractTypes}" var="item">
                    <c:set var="selected" value="${sessionScope.searchFilter.contractTypeId == item.id}"/>
                    <div class="radio">
                        <label>
                            <input type="radio" name="contractTypeId" value="${item.id}" <c:if test="${selected}">checked</c:if>>
                                ${item.name}
                        </label>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="form-check">
            <label class="control-label"><spring:message code="form.developer.visaNeeded"/></label>
            <div>
                <c:set var="isSet" value="${sessionScope.searchFilter.visaNeeded != null}"/>
                <c:set var="selected" value="${sessionScope.searchFilter.visaNeeded}" />
                <div class="radio">
                    <label>
                        <input type="radio" name="visaNeeded" value="${true}" <c:if test="${isSet and selected}">checked</c:if>>
                        <spring:message code="common.keyword.yes"/>
                    </label>
                </div>
                <div class="radio">
                    <label>
                        <input type="radio" name="visaNeeded" value="${false}" <c:if test="${isSet and not selected}">checked</c:if>>
                        <spring:message code="common.keyword.no"/>
                    </label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label">
                <spring:message code="form.developer.preferredCompanyTypes"/>
            </label>
            <div>
                <c:forEach items="${companyTypes}" var="item">
                    <c:set var="selected" value="${sessionScope.searchFilter.preferredCompanyTypeIds.contains(item.id)}"/>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="preferredCompanyTypeIds" value="${item.id}" <c:if test="${selected}">checked</c:if>>
                                   ${item.name}
                        </label>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label" for="experience"><spring:message code="form.developer.experience"/></label>
            <div>
                <input type="number" name="experience" min="0" value="${sessionScope.searchFilter.experience}"
                       id="experience" class="form-control input-sm" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"><spring:message code="form.developer.preferredRole"/></label>
            <div>
                <c:forEach items="${roleTypes}" var="item">
                    <c:set var="selected" value="${sessionScope.searchFilter.roleTypeId eq item.id}"/>
                    <div class="radio">
                        <label>
                            <input type="radio" name="roleTypeId" value="${item.id}" <c:if test="${selected}">checked</c:if>>
                            ${item.name}
                        </label>
                    </div>
                </c:forEach>
            </div>
        </div>

        <%--
        <div class="form-group">
            <label class="control-label"
                   for="worked-before"><spring:message code="form.developer.workHistory"/></label>
            <div>
                <form:textarea path="workHistory" cssClass="form-control input-sm" id="worked-before"
                               maxlength="500" />
            </div>
        </div>
        --%>

        <div class="form-group">
            <label class="control-label" for="english-level"><spring:message code="form.developer.englishLevel"/></label>
            <div>
                <input type="number" name="englishLevel" min="1" max="5" value="${sessionScope.searchFilter.englishLevel}"
                       id="english-level" class="form-control input-sm" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label" for="travel"><spring:message code="form.developer.travelTime"/></label>
            <div>
                <input type="number" name="travelTime" min="0" value="${sessionScope.searchFilter.travelTime}"
                       id="travel" class="form-control input-sm" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label" for="place-live"><spring:message code="form.developer.preferredCity"/></label>
            <div>
                <textarea name="preferredCity" maxlength="500" id="place-live" class="form-control input-sm">
                    ${sessionScope.searchFilter.preferredCity}
                </textarea>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label" for="availability"><spring:message code="form.developer.availability"/></label>
            <div>
                <input type="number" name="availability" min="0" value="${sessionScope.searchFilter.availability}"
                       id="availability" class="form-control input-sm">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label" for="hours-week"><spring:message code="form.developer.hoursPerWeek"/></label>
            <div>
                <input type="number" name="hoursPerWeek" min="0" value="${sessionScope.searchFilter.hoursPerWeek}"
                       id="hours-week" class="form-control input-sm" />
            </div>
        </div>
    </form>
</nav>

<div class="col-xs-12 col-sm-6 col-md-9 wrapper dev-list">
    <table id="stubGrid" style="display: none"></table>
    <div class="col-xs-12">
        <ul id="cardList" class="list-unstyled" style="display: none"></ul>
    </div>
    <div id="cardItemTemplate" style="display: none">
        <li class="person col-sm-12 col-md-6">
            <button class="btn btn-info custom-tooltip" title="Read more" target="view-details" data-id="{{:id}}">
                <i class="glyphicon glyphicon-eye-open"></i>
            </button>
            <button class="btn btn-success custom-tooltip" title="Edit" target="edit" data-id="{{:id}}">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th><spring:message code="form.developer.grade"/></th>
                    <td>{{:rank}}</td>
                </tr>
                <tr>
                    <th><spring:message code="form.developer.mainProgrammingLanguage"/></th>
                    <td>{{:mainProgrammingLanguage}}</td>
                </tr>
                <tr>
                    <th><spring:message code="form.developer.secondProgrammingLanguage"/></th>
                    <td>{{:secondProgrammingLanguage}}</td>
                </tr>
                <tr>
                    <th><spring:message code="form.developer.testRating"/></th>
                    <td>{{:testRating}}%</td>
                </tr>
                <tr>
                    <th><spring:message code="form.developer.originCountry"/></th>
                    <td>{{:originCountry}}</td>
                </tr>
            </table>
        </li>
    </div>
</div>

</body>
</html>
