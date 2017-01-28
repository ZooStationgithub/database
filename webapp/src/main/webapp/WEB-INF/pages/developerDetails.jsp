<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><spring:message code="page.developer.details.title"/></title>
    <meta name="_msg_page.developer.details.mail" content='<spring:message code='page.developer.details.mail' javaScriptEscape='true'/>'>
    <%@ include file="header.jsp" %>
    <dandelion:bundle includes="developer-details" />
    <%--
    <script>
        var messages = {
            'page.developer.details.mail' : "<spring:message code='page.developer.details.mail' javaScriptEscape='true'/>"
        }
    </script>
    <script src='<spring:url value="/assets/js/developerDetails.js"/>' type="application/javascript"></script>
    -->
</head>
<body>

<%@ include file="navbar.jsp" %>

<header>
    <h1 class="text-center"></h1>
</header>

<div class="wrapper container">

    <table class="table table-striped table-hover">
        <tr>
            <th><spring:message code="form.developer.grade"/></th>
            <td>${profile.rank}</td>
        </tr>
        <tr>
            <th><spring:message code="form.developer.mainProgrammingLanguage"/></th>
            <td>${profile.mainProgrammingLanguage}</td>
        </tr>
        <tr>
            <th><spring:message code="form.developer.secondProgrammingLanguage"/></th>
            <td>${profile.secondProgrammingLanguage}</td>
        </tr>

        <tr>
            <th><spring:message code="form.developer.frameworks"/></th>
            <td>
                <ul class="list-unstyled">
                    <c:forTokens items="${profile.frameworks}" delims=";" var="f">
                        <li>${f}</li>
                    </c:forTokens>
                </ul>
            </td>
        </tr>

        <tr>
            <th><spring:message code="form.developer.testRating"/></th>
            <td>
                <c:if test="${profile.testRating != null}">
                    ${profile.testRating}%
                </c:if>
            </td>
        </tr>
        <tr>
            <th><spring:message code="form.developer.originCountry"/></th>
            <td>${profile.originCountry}</td>
        </tr>

        <tr>
            <th><spring:message code="form.developer.preferredCountries"/></th>
            <td>
                <ul class="list-unstyled">
                    <c:forTokens items="${profile.preferredCountries}" delims=";" var="c">
                        <li>${c}</li>
                    </c:forTokens>
                </ul>
            </td>
        </tr>

        <tr>
            <th><spring:message code="form.developer.contractType"/></th>
            <td>${profile.contractType}</td>
        </tr>

        <tr>
            <th><spring:message code="form.developer.visaNeeded"/></th>
            <td>
                <c:if test="${profile.visaNeeded != null}">
                    <c:choose>
                        <c:when test="${profile.visaNeeded}"><spring:message code="common.keyword.yes"/></c:when>
                        <c:otherwise><spring:message code="common.keyword.no"/></c:otherwise>
                    </c:choose>
                </c:if>
            </td>
        </tr>

        <tr>
            <th><spring:message code="form.developer.preferredCompanyTypes"/></th>
            <td>${profile.preferredCompanyTypes}</td>
        </tr>

        <tr>
            <th><spring:message code="form.developer.experience"/></th>
            <td>
                <c:if test="${profile.experience != null}">
                    ${profile.experience} <spring:message code="common.keyword.years"/>
                </c:if>
            </td>
        </tr>

        <tr>
            <th><spring:message code="form.developer.preferredRole"/></th>
            <td>${profile.roles}</td>
        </tr>

        <tr>
            <th><spring:message code="form.developer.workHistory"/></th>
            <td>
                <p>${profile.workHistory}</p>
            </td>
        </tr>

        <tr>
            <th><spring:message code="form.developer.englishLevel"/></th>
            <td>${profile.englishLevel}</td>
        </tr>

        <tr>
            <th><spring:message code="form.developer.travelTime"/></th>
            <td>
                <c:if test="${profile.travelTime != null}">
                    ${profile.travelTime} <spring:message code="common.keyword.minPerDay"/>
                </c:if>
            </td>
        </tr>

        <tr>
            <th><spring:message code="form.developer.preferredCity"/></th>
            <td>${profile.preferredCity}</td>
        </tr>

        <tr>
            <th><spring:message code="form.developer.availability"/></th>
            <td>
                <c:if test="${profile.availability != null}">
                    ${profile.availability} <spring:message code="common.keyword.weeks"/>
                </c:if>
            </td>
        </tr>

        <tr>
            <th><spring:message code="form.developer.hoursPerWeek"/></th>
            <td>${profile.hoursPerWeek}</td>
        </tr>

        <tr>
            <th><spring:message code="form.developer.relocationReason"/></th>
            <td>${profile.relocationReason}</td>
        </tr>

        <c:if test="${not empty profile.additionalInfo}">
            <c:forEach items="${profile.additionalInfo}" var="entry">
                <tr>
                    <th>${entry.key}</th>
                    <td>${entry.value}</td>
                </tr>
            </c:forEach>
        </c:if>

    </table>


    <div class="text-center">
        <sec:authorize access="hasAnyRole('ROLE_SU', 'ROLE_COMPANY')">
        <button id="btnMoreInfo" class="btn btn-warning" data-id="${profile.id}">
            <i class="glyphicon glyphicon-envelope"></i>
            <spring:message code="page.developer.details.moreInfo"/>
        </button>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_SU', 'ROLE_ADMIN', 'ROLE_ZS_USER')">
            <button id="btnEdit" class="btn btn-default" data-id="${profile.id}">
                <i class="glyphicon glyphicon-pencil"></i>
                <spring:message code="common.keyword.edit"/>
            </button>
        </sec:authorize>
    </div>

</div>

<br/>
<br/>

</body>
</html>