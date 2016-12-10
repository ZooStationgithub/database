<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="col-xs-12">
    <form:form commandName="contractType" method="post">

        <form:hidden path="id" />

        <div class="form-group row">
            <label class="control-label col-xs-12 col-sm-3" for="contract-name"><spring:message code="common.keyword.name"/></label>
            <div class="col-xs-12 col-sm-9">
                <form:input path="name" cssClass="form-control input-sm" cssErrorClass="form-control input-sm error" id="contract-name"/>
                <form:errors path="name" cssClass="error"/>
            </div>
        </div>

        <div class="text-center">
            <button type="submit" class="btn  btn-primary">
                <i class="glyphicon glyphicon-floppy-disk"></i>
                <spring:message code="common.keyword.save"/>
            </button>
        </div>

    </form:form>

</div>

