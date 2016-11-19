<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="_context_path" content="${pageContext.servletContext.contextPath}"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href='<spring:url value="/assets/css/style.css"/>'>
<script src='<spring:url value="/assets/js/lib/jquery-3.1.1.min.js"/>' type="application/javascript"></script>
<script src='<spring:url value="/assets/js/lib/bootstrap.min.js"/>' type="application/javascript"></script>
<script type="application/javascript">
    var csrf = {
        token: $("meta[name='_csrf']").attr("content"),
        header: $("meta[name='_csrf_header']").attr("content")
    };
    var contextPath = $("meta[name='_context_path']").attr("content");
    $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
        options.url = contextPath + options.url;
        jqXHR.setRequestHeader(csrf.header, csrf.token);
    });
</script>