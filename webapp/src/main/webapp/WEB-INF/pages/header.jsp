<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="_context_path" content="${pageContext.servletContext.contextPath}"/>

<link rel="apple-touch-icon" sizes="180x180" href='<spring:url value="/assets/img/fav/apple-touch-icon.png"/>'>
<link rel="icon" type="image/png" href='<spring:url value="/assets/img/fav/favicon-32x32.png"/>' sizes="32x32">
<link rel="icon" type="image/png" href='<spring:url value="/assets/img/fav/favicon-16x16.png"/>' sizes="16x16">
<link rel="manifest" href='<spring:url value="/assets/img/fav/manifest.json"/>'>
<link rel="mask-icon" href='<spring:url value="/safari-pinned-tab.svg"/>' color="#5bbad5">
<meta name="theme-color" content="#ffffff">

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