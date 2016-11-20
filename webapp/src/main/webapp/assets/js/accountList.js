/**
 * Created by val on 13/11/16.
 */
$(document).ready(function () {

    var grid = $('#grid').DataTable({
        processing: true,
        serverSide: true,
        filter: false,
        scrollX: true,
        ajax: '/account/grid',
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
                className: 'middle',
                render: function(cell, type, row) {
                    return moment(cell * 1000).format('DD-MM-YYYY HH:mm:ss');
                }
            },
            {
                data: 'activated',
                type: 'html',
                className: 'middle',
                render: function(cell, type, row) {
                    var icon = (cell == 1) ? 'glyphicon-ok text-success' : 'glyphicon-remove text-danger';
                    return '<i class="glyphicon ' + icon + '">' + '</i>';
                }
            },
            {
                data: null,
                type: 'html',
                searchable: false,
                orderable: false,
                render: function(cell, type, row) {
                    if (!row.online && !row.activated) {
                        return '<a target="resend-link" href="javascript:void(0)" title="' + messages['page.accountList.tooltip.link']
                            + '"><i class="glyphicon glyphicon-send" data-id="' + row.id + '"></i></a>';
                    }
                    return '';
                }
            },
            {
                data: null,
                type: 'html',
                searchable: false,
                orderable: false,
                render: function(cell, type, row) {
                    if (row.online) {
                        return '';
                    }
                    return '<a target="delete" href="javascript:void(0)" title="' + messages['page.accountList.tooltip.delete']
                        + '"><i class="glyphicon glyphicon-trash text-danger" data-id="' + row.id + '"></i></a>'
                }
            }
        ]
    });

    $('#grid').on('click', 'a[target="resend-link"]', function(event) {
        var id = $(event.target).data('id');
        $.ajax({
            url: '/account/activate/resend/' + id,
            method: 'get'
        }).done(function(response) {
            alert("Activation link resent"); // TODO create popup
        }).fail(function(response) {
            alert("Something went wrong");
        });
    });

    $('#grid').on('click', 'a[target="delete"]', function(event) {
        var id = $(event.target).data('id');
        $.ajax({
            url: '/account/form/' + id,
            method: 'delete'
        }).done(function(response) {
            grid.draw();
        }).fail(function(response) {
            alert("Something went wrong");
        });
    });

});