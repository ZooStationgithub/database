<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="page.admin.title"/></title>
    <%@ include file="../header.jsp" %>
    <link rel="stylesheet" href='<spring:url value="/assets/css/dataTables.bootstrap.css"/>'>
    <script type="application/javascript">
        var messages = {
            'page.accountList.tooltip.delete' : "<spring:message code='page.accountList.tooltip.delete' javaScriptEscape='true'/>",
            'page.accountList.tooltip.link' : "<spring:message code='page.accountList.tooltip.link' javaScriptEscape='true'/>"
        };
    </script>
    <script src='<spring:url value="/assets/js/lib/jquery.dataTables.min.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/lib/dataTables.bootstrap.min.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/lib/moment.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/account/accountGrid.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/account/accountForm.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/account/accountTab.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/pl/plGrid.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/pl/plForm.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/pl/plTab.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/framework/frameworkGrid.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/framework/frameworkForm.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/framework/frameworkTab.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/contract/contractGrid.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/contract/contractForm.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/contract/contractTab.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/company/companyGrid.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/company/companyForm.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/company/companyTab.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/role/roleGrid.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/role/roleForm.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/role/roleTab.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/rank/rankGrid.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/rank/rankForm.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/rank/rankTab.js"/>' type="application/javascript"></script>
    <script src='<spring:url value="/assets/js/admin/adminConsole.js"/>' type="application/javascript"></script>
</head>
<body>

<%@ include file="../navbar.jsp" %>

<header>
    <h1 class="text-center"></h1>
</header>

<div class="wrapper container">

    <div class="row mbl">
        <div class="col-lg-12">
            <div class="col-lg-12">

                <ul id="adminTabBar" class="nav nav-tabs responsive">
                    <li>
                        <a href="#accounts" data-toggle="tab" role="tab" data-tab-index="0">
                            <spring:message code="page.admin.tab.accounts"/>
                        </a>
                    </li>
                    <li>
                        <a href="#programming-languages" data-toggle="tab" role="tab" data-tab-index="1">
                            <spring:message code="page.admin.tab.languages" />
                        </a>
                    </li>
                    <li>
                        <a href="#frameworks" data-toggle="tab" role="tab" data-tab-index="2">
                            <spring:message code="page.admin.tab.frameworks" />
                        </a>
                    </li>
                    <li>
                        <a href="#contract-types" data-toggle="tab" role="tab" data-tab-index="3">
                            <spring:message code="page.admin.tab.contract" />
                        </a>
                    </li>
                    <li>
                        <a href="#company-types" data-toggle="tab" role="tab" data-tab-index="4">
                            <spring:message code="page.admin.tab.company" />
                        </a>
                    </li>
                    <li>
                        <a href="#role-types" data-toggle="tab" role="tab" data-tab-index="5">
                            <spring:message code="page.admin.tab.role" />
                        </a>
                    </li>
                    <li>
                        <a href="#rank-types" data-toggle="tab" role="tab" data-tab-index="6">
                            <spring:message code="page.admin.tab.rank" />
                        </a>
                    </li>
                </ul>

                <div id="adminTabContent" class="tab-content">
                    <div id="accounts" class="tab-pane fade" role="tabpanel">
                        <h1>This is account tab</h1>
                    </div>
                    <div id="programming-languages" class="tab-pane fade" role="tabpanel">
                        <h1>This is programming languages tab</h1>
                    </div>
                    <div id="frameworks" class="tab-pane fade" role="tabpanel">
                        <h1>This is frameworks tab</h1>
                    </div>
                    <div id="contract-types" class="tab-pane fade" role="tabpanel">
                        <h1>This is contract-types tab</h1>
                    </div>
                    <div id="company-types" class="tab-pane fade" role="tabpanel">
                        <h1>This is company-types tab</h1>
                    </div>
                    <div id="role-types" class="tab-pane fade" role="tabpanel">
                        <h1>This is role-types tab</h1>
                    </div>
                    <div id="rank-types" class="tab-pane fade" role="tabpanel">
                        <h1>This is rank-types tab</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
