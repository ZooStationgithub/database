/**
 * Created by valentinnastasi on 07/12/2016.
 */
AccountGrid = function (tabContext) {

    this.tabContext = tabContext;
    this.grid = null;

};

AccountGrid.prototype = function () {

    var handlers = function (thisObj) {
        return {
            clickNew: function (event) {
                thisObj.tabContext.form.open();
            },
            clickResendLink: function (event) {
                var id = $(this).data('id');
                $.ajax({
                    url: '/admin/account/activate/resend/' + id,
                    method: 'get'
                }).done(function(response) {
                    refresh(thisObj);
                });
            },
            clickDelete: function (event) {
                var id = $(this).data('id');
                $.ajax({
                    url: '/admin/account/' + id,
                    method: 'delete'
                }).done(function(response) {
                    refresh(thisObj);
                });
            }
        }
    };

    var gridSelector = '#account-grid';

    var columns = {
        id: {
            data: 'login',
            type: 'string',
            className: 'middle'
        },
        group: {
            data: 'group',
            type: 'string',
            className: 'middle'
        },
        creationDate: {
            data: 'creationDate',
            type: 'date',
            className: 'middle',
            render: function (cell, type, row) {
                return moment(cell * 1000).format('DD-MM-YYYY HH:mm:ss');
            }
        },
        activated: {
            data: 'activated',
            type: 'html',
            className: 'middle',
            render: function (cell, type, row) {
                var icon = (cell == 1) ? 'glyphicon-ok text-success' : 'glyphicon-remove text-danger';
                return '<i class="glyphicon ' + icon + '">' + '</i>';
            }
        },
        resendLink: {
            data: null,
            type: 'html',
            searchable: false,
            orderable: false,
            render: function (cell, type, row) {
                if (!row.online && !row.activated) {
                    return '<a data-action="resend-link" data-id="' + row.id + '" href="javascript:void(0)" title="'
                        + messages['page.accountList.tooltip.link']
                        + '"><i class="glyphicon glyphicon-send"></i></a>';
                }
                return '';
            }
        },
        delete: {
            data: null,
            type: 'html',
            searchable: false,
            orderable: false,
            render: function (cell, type, row) {
                if (row.online) {
                    return '';
                }
                return '<a href="javascript:void(0)" data-id="' + row.id + '" data-action="delete" title="'
                    + messages['page.accountList.tooltip.delete']
                    + '"><i class="glyphicon glyphicon-trash text-danger"></i></a>'
            }
        }
    };

    var definition = {
        ajax: '/admin/account/grid',
        processing: true,
        serverSide: true,
        filter: false,
        scrollX: true,
        columns: [columns.id, columns.group, columns.creationDate, columns.activated, columns.resendLink, columns.delete]
    };

    var init = function (thisObj) {
        thisObj = thisObj || this;
        thisObj.grid = $(gridSelector).DataTable(definition);

        $('#btnNewAccount').on('click', handlers(thisObj).clickNew);
        $(gridSelector).on('click', 'a[data-action="resend-link"]', handlers(thisObj).clickResendLink);
        $(gridSelector).on('click', 'a[data-action="delete"]', handlers(thisObj).clickDelete);
        $(gridSelector).trigger('resize');
    };

    var refresh = function (thisObj) {
        thisObj = thisObj || this;
        thisObj.grid.draw();
    };

    return {
        init: init,
        refresh: refresh
    }
}();