/**
 * Created by valentinnastasi on 25/10/2016.
 */
$(document).ready(function () {
    $('#btnMoreInfo').one('click', function(event) {
        $.ajax({
            url: '/zsd/developer/info',
            method: 'GET',
            data: {
                'u': $(event.target).data('id')
            }
        }).done(function() {
            alert("Mail was sent"); // TODO need a pretty popup here
        }).fail(function() {
            alert("An error occurred")
        });
    });
});