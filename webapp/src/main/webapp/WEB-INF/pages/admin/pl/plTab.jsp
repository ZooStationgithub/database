<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="col-xs-12">
    <br/>
    <br/>
    <div class="panel panel-default">
        <div class="panel-heading clearfix">
            <span class="panel-title"><spring:message code="common.keyword.list"/></span>
            <label id="btnNewPl" class="btn btn-xs btn-danger pull-right">
                <spring:message code="common.keyword.new"/>
            </label>
        </div>
        <div class="panel-body">
            <table id="pl-grid" class="table table-hover col-xs-12">
                <thead>
                <tr>
                    <th><spring:message code="common.keyword.id"/></th>
                    <th><spring:message code="common.keyword.name"/></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </div>

    <br/>
    <br/>

    <div id="panelFormPl" class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><spring:message code="common.keyword.form"/></h3>
        </div>
        <div class="panel-body collapse">
        </div>
    </div>

</div>
