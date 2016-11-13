/**
 * Created by val on 13/11/16.
 */
$(document).ready(function () {

    $('#grid').DataTable({
        processing: true,
        serverSide: true,
        scrollX: true,
        ajax: '/zsd/account/grid',
        columns: [
            {
                data: 'login',
                type: 'string',
                className: 'middle'
            },
            {
                data: 'group',
                type: 'string',
                className: 'middle'
            },
            {
                data: 'creationDate',
                type: 'date',
                className: 'middle'
            },
            {
                data: 'activated',
                type: 'html',
                className: 'middle',
                render: function(cell, type, row) {
                    var icon = (cell == 1) ? 'glyphicon-ok' : 'glyphicon-remove';
                    return '<i class="glyphicon ' + icon + '">' + '</i>'
                }
            },
            {
                data: null,
                type: 'html',
                render: function(cell, type, row) {
                    return '';
                }
            }
        ]
    })

});