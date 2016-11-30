/**
 * Created by valentinnastasi on 25/10/2016.
 */
$(document).ready(function () {
    $('#btnMoreInfo').one('click', function(event) {
        var id = event.target.dataset['id'];
        $.ajax({
            url: '/developer/info',
            method: 'GET',
            data: {
                'u': id
            }
        }).done(function() {
            alert("Mail was sent"); // TODO need a pretty popup here
        }).fail(function() {
            alert("An error occurred")
        });
    });

    $('#btnEdit').one('click', function() {
        var id = event.target.dataset['id'];
        window.location.href = contextPath + '/developer/edit?u=' + id;
    });
});