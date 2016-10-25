<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="page.developer.details.title"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href='<spring:url value="/assets/css/style.css"/>'>
    <script src='<spring:url value="/assets/js/lib/jquery-3.1.1.min.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/developerDetails.js"/>' type="application/javascript"></script>
</head>
<body>

<header>
    <h1 class="text-center"><spring:message code="page.developer.details.header"/></h1>
    <div class="container text-center">
        <a href='<spring:url value="/index"/>' class="btn btn-primary">
            <i class="glyphicon glyphicon-arrow-left"></i>
            <spring:message code="common.keyword.backToSearch"/>
        </a>
    </div>
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
        <button id="btnMoreInfo" class="btn btn-warning" data-id="${profile.id}">
            <i class="glyphicon glyphicon-envelope"></i>
            <spring:message code="page.developer.details.moreInfo"/>
        </button>
    </div>
</div>

</body>
</html>