/**
 * Created by valentinnastasi on 08/12/2016.
 */
ContractTypeGrid = function (tabContext) {

    this.tabContext = tabContext;
    this.grid = null;

};

ContractTypeGrid.prototype = function () {

    var handlers = function (thisObj) {
        return {
            clickNew: function (event) {
                thisObj.tabContext.form.open();
            },
            clickEdit: function(event) {
                var id = $(this).data('id');
                thisObj.tabContext.form.open(id);
            },
            clickDelete: function (event) {
                var id = $(this).data('id');
                $.ajax({
                    url: '/admin/data/contract/' + id,
                    method: 'delete'
                }).done(function(response) {
                    refresh(thisObj);
                });
            }
        }
    };

    var gridSelector = '#contract-grid';

    var columns = {
        id: {
            data: 'id',
            type: 'number',
            className: 'middle'
        },
        name: {
            data: 'name',
            type: 'string',
            className: 'middle'
        },
        edit: {
            data: null,
            type: 'html',
            searchable: false,
            orderable: false,
            render: function (cell, type, row) {
                return '<a href="javascript:void(0)" data-id="' + row.id + '" data-action="edit"><i class="glyphicon glyphicon-edit text-warning"></i></a>'
            }
        },
        delete: {
            data: null,
            type: 'html',
            searchable: false,
            orderable: false,
            render: function (cell, type, row) {
                return '<a href="javascript:void(0)" data-id="' + row.id + '" data-action="delete"><i class="glyphicon glyphicon-trash text-danger"></i></a>'
            }
        }
    };

    var definition = {
        ajax: '/admin/data/contract/grid',
        processing: true,
        serverSide: true,
        filter: false,
        scrollX: true,
        columns: [columns.id, columns.name, columns.edit, columns.delete]
    };

    var init = function (thisObj) {
        thisObj = thisObj || this;
        thisObj.grid = $(gridSelector).DataTable(definition);

        $('#btnNewContractType').on('click', handlers(thisObj).clickNew);
        $(gridSelector).on('click', 'a[data-action="edit"]', handlers(thisObj).clickEdit);
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