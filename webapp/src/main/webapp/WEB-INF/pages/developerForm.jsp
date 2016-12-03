<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        <c:choose>
            <c:when test="${profile.id != null}"><spring:message code="page.developer.form.edit.title"/></c:when>
            <c:otherwise><spring:message code="page.developer.form.new.title"/></c:otherwise>
        </c:choose>
    </title>
    <%@ include file="header.jsp" %>
    <link rel="stylesheet" href='<spring:url value="/assets/css/token-input.css"/>' />
    <link rel="stylesheet" href='<spring:url value="/assets/css/token-input-mac.css"/>' />
    <script src='<spring:url value="/assets/js/lib/jquery.tokeninput.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/lib/jsrender.min.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/developerForm.js"/>' type="application/javascript"></script>
</head>
<body>

<%@ include file="navbar.jsp" %>

<header>
    <h1 class="text-center"></h1>
</header>

<div class="wrapper container">
    <form id="formProfile">

        <input type="hidden" name="id" value="${profile.id}" />

        <div class="form-group row">
            <label class="control-label col-xs-3" for="zsnumber"><spring:message code="form.developer.zsNumber"/></label>
            <div class="col-xs-9">
                <input type="text" name="zsNumber" readonly="readonly" id="zsnumber" class="form-control"
                        value="${profile.zsNumber}" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3" for="grade"><spring:message code="form.developer.grade"/></label>
            <div class="col-xs-9">
                <select name="rankTypeId" class="form-control" id="grade">
                    <option value="${null}"></option>
                    <c:forEach items="${rankTypes}" var="item">
                        <c:set var="selected" value="${item.id == profile.rankTypeId}" />
                        <option value="${item.id}" <c:if test="${selected}">selected</c:if>>${item.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3" for="mpl"><spring:message code="form.developer.mainProgrammingLanguage"/></label>
            <div class="col-xs-9">
                <select name="mainProgrammingLanguageId" class="form-control" id="mpl">
                    <option value="${null}"></option>
                    <c:forEach items="${languages}" var="item">
                        <c:set var="selected" value="${item.id == profile.mainProgrammingLanguageId}" />
                        <option value="${item.id}" <c:if test="${selected}">selected</c:if>>${item.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3" for="spl"><spring:message code="form.developer.secondProgrammingLanguage"/></label>
            <div class="col-xs-9">
                <select name="secondProgrammingLanguageId" class="form-control" id="spl">
                    <option value="${null}"></option>
                    <c:forEach items="${languages}" var="item">
                        <c:set var="selected" value="${item.id == profile.secondProgrammingLanguageId}" />
                        <option value="${item.id}" <c:if test="${selected}">selected</c:if>>${item.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3" for="frameworks"><spring:message code="form.developer.frameworks"/></label>
            <div class="col-xs-9">
                <select name="knownFrameworkIds" class="form-control" id="frameworks" multiple>
                    <c:forEach items="${frameworks}" var="item">
                        <c:set var="selected" value="${profile.knownFrameworkIds.contains(item.id)}" />
                        <option value="${item.id}" <c:if test="${selected}">selected</c:if>>${item.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3" for="rating-cd"><spring:message code="form.developer.testRating"/>, %</label>
            <div class="col-xs-9">
                <input type="number" name="testRating" min="0" max="100" class="form-control" id="rating-cd"
                            value="${profile.testRating}" />
                </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3" for="country-origin"><spring:message code="form.developer.originCountry"/></label>
            <div class="col-xs-9">
                <input type="text" name="originCountryId" id="country-origin" class="form-control input-sm"
                       data-selected='${selectedOriginCountry}' />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3" for="country-preferred"><spring:message code="form.developer.preferredCountries"/></label>
            <div class="col-xs-9">
                <input type="text" name="preferredCountryIds" id="country-preferred" class="form-control input-sm"
                       data-selected='${selectedPreferredCountries}'>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3"><spring:message code="form.developer.contractType"/></label>
            <div class="col-xs-9">
                <c:forEach items="${contractTypes}" var="item">
                    <c:set var="selected" value="${profile.contractTypeId == item.id}"/>
                    <div class="radio">
                        <label>
                            <input type="radio" name="contractTypeId" value="${item.id}" <c:if test="${selected}">checked</c:if>>
                                ${item.name}
                        </label>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="form-check row">
            <label class="control-label col-xs-3"><spring:message code="form.developer.visaNeeded"/></label>
            <div class="col-xs-9">
                <c:set var="isSet" value="${profile.visaNeeded != null}"/>
                <c:set var="selected" value="${profile.visaNeeded}" />
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

        <div class="form-group row">
            <label class="control-label col-xs-3"><spring:message code="form.developer.preferredCompanyTypes"/></label>
            <div class="col-xs-9">
                <c:forEach items="${companyTypes}" var="item">
                    <c:set var="selected" value="${profile.preferredCompanyTypeIds.contains(item.id)}"/>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="preferredCompanyTypeIds" value="${item.id}" <c:if test="${selected}">checked</c:if>>
                                ${item.name}
                        </label>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3" for="experience"><spring:message code="form.developer.experience"/>, years</label>
            <div class="col-xs-9">
                <input type="number" name="experience" min="0" value="${profile.experience}"
                       id="experience" class="form-control input-sm" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3"><spring:message code="form.developer.preferredRole"/></label>
            <div class="col-xs-9">
                <div>
                    <c:forEach items="${roleTypes}" var="item">
                        <c:set var="selected" value="${profile.roleTypeId eq item.id}"/>
                        <div class="radio">
                            <label>
                                <input type="radio" name="roleTypeId" value="${item.id}" <c:if test="${selected}">checked</c:if>>
                                    ${item.name}
                            </label>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3" for="worked-before"><spring:message code="form.developer.workHistory"/></label>
            <div class="col-xs-9">
                <textarea name="workHistory" maxlength="500" class="form-control" id="worked-before">
                    ${profile.workHistory}
                </textarea>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3" for="english-level"><spring:message code="form.developer.englishLevel"/></label>
            <div class="col-xs-9">
                <input type="number" name="englishLevel" min="1" max="5" value="${profile.englishLevel}"
                       id="english-level" class="form-control input-sm" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3" for="travel"><spring:message code="form.developer.travelTime"/></label>
            <div class="col-xs-9">
                <input type="number" name="travelTime" min="0" value="${profile.travelTime}"
                       id="travel" class="form-control input-sm" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3" for="place-live"><spring:message code="form.developer.preferredCity"/></label>
            <div class="col-xs-9">
                <textarea name="preferredCity" maxlength="500" id="place-live" class="form-control input-sm">
                    ${profile.preferredCity}
                </textarea>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3" for="availability"><spring:message code="form.developer.availability"/></label>
            <div class="col-xs-9">
                <input type="number" name="availability" min="0" value="${profile.availability}"
                       id="availability" class="form-control input-sm">
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3" for="hours-week"><spring:message code="form.developer.hoursPerWeek"/></label>
            <div class="col-xs-9">
                <input type="number" name="hoursPerWeek" min="0" value="${profile.hoursPerWeek}"
                       id="hours-week" class="form-control input-sm" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-3" for="reasons"><spring:message code="form.developer.relocationReason"/> </label>
            <div class="col-xs-9">
                <textarea name="relocationReason" maxlength="500" class="form-control" id="reasons">
                    ${profile.relocationReason}
                </textarea>
            </div>
        </div>

    </form>

    <div class="form-group row">
        <label class="control-label col-xs-3">
            <button id="btnMoreFields" class="btn btn-sm btn-primary"><spring:message code="page.developer.form.customField.add"/></button>
        </label>
    </div>

    <div id="containerMore">
        <c:forEach items="${profile.customFields}" var="item">
            <div class="form-group row" data-type="fieldGroup">
                <div class="col-xs-3">
                    <input type="text" data-type="fieldName" class="form-control input-sm" value="${item.key}" />
                </div>
                <div class="col-xs-9 row">
                    <input type="text" data-type="fieldValue" class="form-control input-sm" value="${item.value}" />
                    <button class="btn btn-xs btn-danger" data-type="deleteField">
                        <i class="glyphicon glyphicon-trash"></i>
                    </button>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="text-center">
        <button id="btnSave" class="btn  btn-primary">
            <i class="glyphicon glyphicon-floppy-disk"></i>
            <spring:message code="common.keyword.save"/>
        </button>
        <c:if test="${profile.id != null}">
            <sec:authorize access="hasAnyRole('ROLE_SU', 'ROLE_ADMIN')">
        <button id="btnDelete" class="btn btn-danger" data-id="${profile.id}">
            <i class="glyphicon glyphicon-remove"></i>
            <spring:message code="common.keyword.delete"/>
        </button>
            </sec:authorize>
        </c:if>
    </div>

    <br/>
    <br/>

    <div id="customFieldTemplate" style="display: none">
        <div class="form-group row" data-type="fieldGroup">
            <div class="col-xs-3">
                <input type="text" data-type="fieldName" class="form-control input-sm" />
            </div>
            <div class="col-xs-9">
                <input type="text" data-type="fieldValue" class="form-control input-sm" />
                <button class="btn btn-xs btn-danger" data-type="deleteField">
                    <i class="glyphicon glyphicon-trash"></i>
                </button>
            </div>
        </div>
    </div>

</div>

</body>
</html>