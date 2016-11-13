<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="page.accountList.title"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href='<spring:url value="/assets/css/style.css"/>'>
    <script src='<spring:url value="/assets/js/lib/jquery-3.1.1.min.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/lib/jquery.dataTables.min.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/lib/dataTables.bootstrap.min.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/accountList.js"/>' type="application/javascript"></script>
</head>
<body>

<%@ include file="navbar.jsp" %>

<header>
    <h1 class="text-center"></h1>
</header>

<div class="wrapper container">

    <div class="col-xs-12">
        <div class="col-xs-12">
            <div class="panel panel-grey">
                <div class="panel-heading">
                    <span></span>
                    <a href='<spring:url value="/account/form"/>' class="btn btn-xs btn-danger pull-right">
                        <spring:message code="common.keyword.new"/>
                    </a>
                </div>
                <div class="panel-body">
                    <table id="grid" class="table table-hover">
                        <thead>
                        <tr>
                            <th><spring:message code="page.accountList.col.login"/></th>
                            <th><spring:message code="page.accountList.col.group"/></th>
                            <th><spring:message code="page.accountList.col.creationDate"/></th>
                            <th><spring:message code="page.accountList.col.activated"/></th>
                            <th><spring:message code="page.accountList.col.commands"/></th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>