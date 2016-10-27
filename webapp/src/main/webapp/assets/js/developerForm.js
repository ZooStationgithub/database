/**
 * Created by valentinnastasi on 25/10/2016.
 */
$(document).ready(function() {

    $('#btnSave').one('click', function(event) {
        $.ajax({
            url: '/zsd/developer/edit',
            method: 'post',
            data: $('form').serialize()
        }).done(function(data) {
            location.replace('/zsd/index'); // TODO add popup
        }).fail(function(data) {
            alert("Failure :(");
        });
    });

    $('#btnDelete').one('click', function(event) {
        $.ajax({
            url: '/zsd/developer/delete',
            method: 'post',
            data: {
                'u': $(event.target).data('id')
            },
            beforeSend: function(xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            }
        }).done(function(data) {
            location.replace('/zsd/index'); // TODO add popup
        }).fail(function(data) {
            alert("Failure :(");
        });
    });

});