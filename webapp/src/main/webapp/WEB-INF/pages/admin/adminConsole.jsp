<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<html>
<head>
    <title><spring:message code="page.admin.title"/></title>
    <meta name="_msg_page.accountList.tooltip.delete" content='<spring:message code='page.accountList.tooltip.delete' javaScriptEscape='true'/>'>
    <meta name="_msg_page.accountList.tooltip.link" content='<spring:message code='page.accountList.tooltip.link' javaScriptEscape='true'/>'>

    <%@ include file="../header.jsp" %>

    <jwr:style src="/bundles/admin.css"/>
    <jwr:script src="/bundles/admin.js"/>
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
                    </div>
                    <div id="programming-languages" class="tab-pane fade" role="tabpanel">
                    </div>
                    <div id="frameworks" class="tab-pane fade" role="tabpanel">
                    </div>
                    <div id="contract-types" class="tab-pane fade" role="tabpanel">
                    </div>
                    <div id="company-types" class="tab-pane fade" role="tabpanel">
                    </div>
                    <div id="role-types" class="tab-pane fade" role="tabpanel">
                    </div>
                    <div id="rank-types" class="tab-pane fade" role="tabpanel">
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
