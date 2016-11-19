<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="page.index.title"/></title>
    <%@ include file="header.jsp" %>
</head>
<body>

<%@ include file="navbar.jsp" %>

<header>
    <h1 class="text-center"></h1>
</header>

<nav class="search col-xs-12 col-sm-6 col-md-3">

    <form:form commandName="searchQuery" role="search" id="formSearch">
        <h2>
            <spring:message code="common.keyword.search"/>
            <button id="btnSearch" class="btn btn-primary pull-right">
                <i class="glyphicon glyphicon-search"></i>
            </button>
        </h2>

        <div class="form-group">
            <form:input path="filter.zsNumber" type="text" cssClass="form-control" id="dev-search" />
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="srch-grade"><spring:message code="form.developer.grade"/></label>
            <div>
                <form:select path="filter.rankTypeId" id="srch-grade" cssClass="form-control input-sm">
                    <form:option value="" />
                    <form:options items="${rankTypes}" itemValue="id" itemLabel="name" />
                </form:select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="srch-mpl"><spring:message code="form.developer.mainProgrammingLanguage"/></label>
            <div>
                <form:select path="filter.mainProgrammingLanguageId" id="srch-mpl" cssClass="form-control input-sm">
                    <form:option value="" />
                    <form:options items="${languages}" itemValue="id" itemLabel="name" />
                </form:select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="srch-spl"><spring:message code="form.developer.secondProgrammingLanguage"/></label>
            <div>
                <form:select path="filter.secondProgrammingLanguageId" id="srch-spl" cssClass="form-control input-sm">
                    <form:option value="" />
                    <form:options items="${languages}" itemValue="id" itemLabel="name" />
                </form:select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="srch-frameworks"><spring:message code="form.developer.frameworks"/></label>
            <div>
                <form:select path="filter.knownFrameworkIds" id="srch-frameworks" cssClass="form-control input-sm" multiple="true">
                    <form:options items="${frameworks}" itemValue="id" itemLabel="name" />
                </form:select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="rating-cd"><spring:message code="form.developer.testRating"/></label>
            <div>
                <form:input path="filter.testRating" type="number"
                            min="0"
                            max="100"
                            cssClass="form-control input-sm"
                            id="rating-cd" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="country-origin"><spring:message code="form.developer.originCountry"/></label>
            <div>
                <form:select path="filter.originCountryId" cssClass="form-control input-sm" id="country-origin">
                    <form:option value="" />
                    <form:options items="${countries}" itemValue="id" itemLabel="name" />
                </form:select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="country-preferred"><spring:message code="form.developer.preferredCountries"/></label>
            <div>
                <form:select path="filter.preferredCountryIds" id="country-preferred" cssClass="form-control input-sm"
                                multiple="true">
                    <form:options items="${countries}" itemValue="id" itemLabel="name" />
                </form:select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="permnanent-frelance"><spring:message code="form.developer.contractType"/></label>
            <div>
                <form:select path="filter.contractTypeId" class="form-control input-sm" id="permnanent-frelance">
                    <form:option value="" />
                    <form:options items="${contractTypes}" itemValue="id" itemLabel="name" />
                </form:select>
            </div>
        </div>

        <div class="form-check">
            <label class="control-label"
            ><spring:message code="form.developer.visaNeeded"/></label>
            <div class="radio">
                <label>
                    <form:radiobutton path="filter.visaNeeded" value="" cssClass="form-check-input"/>
                    <!--<input type="radio"
                           name="visa"
                           checked
                           class="form-check-input">-->
                    <spring:message code="common.keyword.any"/>
                </label>
            </div>
            <div class="radio">
                <label>
                    <form:radiobutton path="filter.visaNeeded" value="${true}" cssClass="form-check-input"/>
                    <!--<input type="radio"
                           name="visa"
                           class="form-check-input">-->
                    <spring:message code="common.keyword.yes"/>
                </label>
            </div>
            <div class="radio">
                <label>
                    <form:radiobutton path="filter.visaNeeded" value="${false}" cssClass="form-check-input"/>
                    <!--<input type="radio"
                           name="visa"
                           class="form-check-input">-->
                    <spring:message code="common.keyword.no"/>
                </label>
            </div>
        </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="company-type"><spring:message code="form.developer.preferredCompanyTypes"/></label>
            <div>
                <form:select path="filter.preferredCompanyTypeIds" class="form-control input-sm" id="company-type">
                    <form:option value="" />
                    <form:options items="${companyTypes}" itemValue="id" itemLabel="name"/>
                </form:select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="experience"><spring:message code="form.developer.experience"/></label>
            <div>
                <form:input path="filter.experience" type="number"
                            id="experience"
                            min="0"
                            class="form-control input-sm"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="role"><spring:message code="form.developer.preferredRole"/></label>
            <div>
                <form:select path="filter.roleTypeId" class="form-control input-sm" id="role">
                    <form:option value="" />
                    <form:options items="${roleTypes}" itemValue="id" itemLabel="name" />
                </form:select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="worked-before"><spring:message code="form.developer.workHistory"/></label>
            <div>
                <form:textarea path="filter.workHistory" cssClass="form-control input-sm" id="worked-before"
                               maxlength="500" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="english-level"><spring:message code="form.developer.englishLevel"/></label>
            <div>
                <form:input path="filter.englishLevel" type="number"
                            min="1"
                            max="5"
                            placeholder="level 1-5"
                            cssClass="form-control input-sm"
                            id="english-level" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="travel"><spring:message code="form.developer.travelTime"/></label>
            <div>
                <form:input path="filter.travelTime" type="number"
                            cssClass="form-control input-sm"
                            id="travel"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="place-live"><spring:message code="form.developer.preferredCity"/></label>
            <div>
                <form:textarea path="filter.preferredCity" cssClass="form-control input-sm" id="place-live" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="availability"><spring:message code="form.developer.availability"/></label>
            <div>
                <form:input path="filter.availability" type="number"
                            cssClass="form-control input-sm" id="availability" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label"
                   for="hours-week"><spring:message code="form.developer.hoursPerWeek"/></label>
            <div>
                <form:input path="filter.hoursPerWeek" type="number"
                            class="form-control input-sm" id="hours-week" />
            </div>
        </div>
    </form:form>
</nav>


<div class="col-xs-12 col-sm-6 col-md-9 wrapper dev-list">
    <ul class="list-unstyled">
        <li class="person col-sm-12 col-md-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-12 col-md-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-12 col-md-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-12 col-md-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
        <li class="person col-sm-6">
            <a href="developer-detailed.html" class="btn btn-info custom-tooltip" title="Read more">
                <i class="glyphicon glyphicon-eye-open"></i>
            </a>
            <button class="btn btn-success custom-tooltip" title="Edit">
                <i class="glyphicon glyphicon-pencil"></i>
            </button>

            <table class="table table-striped">
                <tr>
                    <th>Grade</th>
                    <td>junior</td>
                </tr>
                <tr>
                    <th>Main programming language</th>
                    <td>jave SE</td>
                </tr>
                <tr>
                    <th>Second programming language</th>
                    <td>Android Native</td>
                </tr>
                <tr>
                    <th>Rating code doctor test, %</th>
                    <td>84%</td>
                </tr>
                <tr>
                    <th>Country of origin</th>
                    <td>Moldova</td>
                </tr>

            </table>
        </li>
    </ul>
</div>

</body>

<!--
<html>
<head>
    <title>ZooStation database</title>
</head>
<body>
    <h1>Hello World!</h1>
</body>
</html>
-->
