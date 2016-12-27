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
            alert(messages['page.developer.details.mail']);
        }).fail(function(xhr) {
            alert(xhr.responseJSON.message);
        });
    });

    $('#btnEdit').one('click', function() {
        var id = event.target.dataset['id'];
        window.location.href = contextPath + '/developer/edit?u=' + id;
    });
});