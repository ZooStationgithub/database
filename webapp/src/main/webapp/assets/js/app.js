/**
 * Created by valentinnastasi on 28/01/2017.
 */
var csrf = {
    token: $("meta[name='_csrf']").attr("content"),
    header: $("meta[name='_csrf_header']").attr("content")
};
var contextPath = $("meta[name='_context_path']").attr("content");
$.ajaxPrefilter(function (options, originalOptions, jqXHR) {
    options.url = contextPath + options.url;
    jqXHR.setRequestHeader(csrf.header, csrf.token);
});
var messages = {};