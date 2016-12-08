<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="col-xs-12">
    <form:form commandName="account" method="post" >

        <div class="form-group row">
            <label class="control-label col-xs-12 col-sm-3" for="username"><spring:message code="form.account.email"/></label>
            <div class="col-xs-12 col-sm-9">
                <form:input path="email" cssClass="form-control input-sm" cssErrorClass="form-control input-sm error" id="username"/>
                <form:errors path="email" cssClass="error"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-12 col-sm-3" for="password"><spring:message code="form.account.password"/></label>
            <div class="col-xs-12 col-sm-9">
                <form:password path="password" cssClass="form-control input-sm" cssErrorClass="form-control input-sm error" id="password"/>
                <form:errors path="password" cssClass="error"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-12 col-sm-3" for="confirm-password"><spring:message code="form.account.password.confirm"/></label>
            <div class="col-xs-12 col-sm-9">
                <form:password path="confirmPassword" cssClass="form-control input-sm" cssErrorClass="form-control input-sm error" id="confirm-password"/>
                <form:errors path="confirmPassword" cssClass="error" />
            </div>
        </div>

        <div class="form-group row">
            <label class="control-label col-xs-12 col-sm-3" for="group"><spring:message code="form.account.group"/></label>
            <div class="col-xs-12 col-sm-9">
                <form:select path="groupId" cssClass="form-control input-sm" items="${groups}" itemLabel="displayName" itemValue="id"
                             id="group" />
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

