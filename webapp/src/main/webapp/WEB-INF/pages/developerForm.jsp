<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>
        <c:choose>
            <c:when test="${profile.id != null}"><spring:message code="page.developer.form.edit.title"/></c:when>
            <c:otherwise><spring:message code="page.developer.form.new.title"/></c:otherwise>
        </c:choose>
    </title>
    <link rel="stylesheet" href='<spring:url value="/assets/css/style.css"/>'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src='<spring:url value="/assets/js/lib/jquery-3.1.1.min.js"/>' type="application/javascript"></script>
    <script>
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    </script>
    <script src='<spring:url value="/assets/js/developerForm.js"/>' type="application/javascript"></script>
</head>
<body>

<header>
    <h1 class="text-center">
        <c:choose>
            <c:when test="${profile.id != null}"><spring:message code="page.developer.form.edit.title"/></c:when>
            <c:otherwise><spring:message code="page.developer.form.new.title"/></c:otherwise>
        </c:choose>
    </h1>
    <div class="container text-center">
        <a href='<spring:url value="/index"/>' class="btn btn-primary">
            <i class="glyphicon glyphicon-arrow-left"></i>
            <spring:message code="common.keyword.backToSearch"/>
        </a>
    </div>
</header>

<div class="wrapper container">
    <form:form commandName="profile">

        <form:hidden path="id" />

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="zsnumber"><spring:message code="form.developer.zsNumber"/></label>
            <div class="col-xs-10">
                <form:input path="zsNumber" readonly="true" id="zsnumber" cssClass="form-control" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="grade"><spring:message code="form.developer.grade"/></label>
            <div class="col-xs-10">
                <form:select path="rankTypeId" cssClass="form-control" id="grade">
                    <form:option value="${null}">&#160;</form:option>
                    <form:options items="${rankTypes}" itemLabel="name" itemValue="id" />
                </form:select>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="mpl"><spring:message code="form.developer.mainProgrammingLanguage"/></label>
            <div class="col-xs-10">
                <form:select path="mainProgrammingLanguageId" cssClass="form-control" id="mpl">
                    <form:option value="${null}">&#160;</form:option>
                    <form:options items="${languages}" itemLabel="name" itemValue="id" />
                </form:select>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="spl"><spring:message code="form.developer.secondProgrammingLanguage"/></label>
            <div class="col-xs-10">
                <form:select path="secondProgrammingLanguageId" cssClass="form-control" id="spl">
                    <form:option value="${null}">&#160;</form:option>
                    <form:options items="${languages}" itemLabel="name" itemValue="id" />
                </form:select>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="frameworks"><spring:message code="form.developer.frameworks"/></label>
            <div class="col-xs-10">
                <form:select path="knownFrameworkIds" cssClass="form-control" id="frameworks" multiple="true">
                    <form:options items="${frameworks}" itemLabel="name" itemValue="id" />
                </form:select>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="rating-cd"><spring:message code="form.developer.testRating"/>, %</label>
            <div class="col-xs-10">
                <form:input path="testRating" type="number" min="0" max="100" cssClass="form-control" id="rating-cd"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="country-origin"><spring:message code="form.developer.originCountry"/></label>
            <div class="col-xs-10">
                <form:select path="originCountryId" cssClass="form-control" id="country-origin">
                    <form:option value="${null}">&#160;</form:option>
                    <form:options items="${countries}" itemLabel="name" itemValue="id" />
                </form:select>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="country-preferred"><spring:message code="form.developer.preferredCountries"/></label>
            <div class="col-xs-10">
                <form:select path="preferredCountryIds" items="${countries}" itemLabel="name" itemValue="id"
                                cssClass="form-control" id="country-preferred" multiple="true" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="permnanent-frelance"><spring:message code="form.developer.contractType"/></label>
            <div class="col-xs-10">
                <form:radiobuttons path="contractTypeId" items="${contractTypes}" itemLabel="name" itemValue="id"
                                    cssClass="" id="permnanent-frelance" />
            </div>
        </div>

        <div class="form-check row">
            <label class="control-label col-xs-2"
                   for="visa"><spring:message code="form.developer.visaNeeded"/></label>
            <div class="col-xs-10">
                <form:checkbox path="visaNeeded" cssClass="form-check-input" id="visa" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="company-type"><spring:message code="form.developer.preferredCompanyTypes"/></label>
            <div class="col-xs-10">
                <form:radiobuttons path="preferredCompanyTypeIds" items="${companyTypes}" itemLabel="name" itemValue="id"
                                    cssClass="" id="company-type" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="experience"><spring:message code="form.developer.experience"/>, years</label>
            <div class="col-xs-10">
                <form:input path="experience" type="number" min="0" cssClass="form-control" id="experience" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="role"><spring:message code="form.developer.preferredRole"/></label>
            <div class="col-xs-10">
                <form:radiobuttons path="roleTypeId" items="${roleTypes}" itemLabel="name" itemValue="id"
                                       cssClass="" id="role" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="worked-before"><spring:message code="form.developer.workHistory"/></label>
            <div class="col-xs-10">
                <form:textarea path="workHistory" maxlength="500" cssClass="form-control" id="worked-before" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="english-level"><spring:message code="form.developer.englishLevel"/></label>
            <div class="col-xs-10">
                <form:input path="englishLevel" type="number" min="1" max="5"
                            cssClass="form-control" id="english-level" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="travel"><spring:message code="form.developer.travelTime"/></label>
            <div class="col-xs-10">
                <form:input path="travelTime" type="number" cssClass="form-control" id="travel" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="place-live"><spring:message code="form.developer.preferredCity"/></label>
            <div class="col-xs-10">
                <form:input path="preferredCity" cssClass="form-control" id="place-live" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="availability"><spring:message code="form.developer.availability"/></label>
            <div class="col-xs-10">
                <form:input path="availability" type="number" cssClass="form-control" id="availability" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="hours-week"><spring:message code="form.developer.hoursPerWeek"/></label>
            <div class="col-xs-10">
                <form:input path="hoursPerWeek" type="number" cssClass="form-control" id="hours-week" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-2"
                   for="reasons"><spring:message code="form.developer.relocationReason"/> </label>
            <div class="col-xs-10">
                <form:textarea path="relocationReason" maxlength="500" cssClass="form-control" id="reasons" />
            </div>
        </div>

    </form:form>

    <div class="text-center">
        <button id="btnSave" class="btn  btn-primary">
            <i class="glyphicon glyphicon-floppy-disk"></i>
            <spring:message code="common.keyword.save"/>
        </button>
        <c:if test="${profile.id != null}">
        <button id="btnDelete" class="btn btn-danger" data-id="${profile.id}">
            <i class="glyphicon glyphicon-remove"></i>
            <spring:message code="common.keyword.delete"/>
        </button>
        </c:if>
    </div>


</div>

</body>
</html>