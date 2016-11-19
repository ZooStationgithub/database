/**
 * Created by valentinnastasi on 25/10/2016.
 */
$(document).ready(function () {

    var $form = $('#formSearch');

    var serializeForm = function () {
        var o = {};
        $('#formSearch').find('input, select, textarea')
            .filter(function (i, e) {
                return $(e).attr('name').indexOf('_') != 0;
            }).each(function (i, e) {
            var $e = $(e);
            var type = $(e).attr('type');
            var key = $e.attr('name');
            var value = $e.val();

            if (type == 'radio' || type == 'checkbox') {
                if (!$e.is(':checked')) {
                    return;
                }

                if (key in o) {
                    if (!(o[key] instanceof Array)) {
                        var temp = o[key];
                        o[key] = [];
                        o[key].push(temp);
                    }
                    o[key].push(value);
                } else {
                    o[key] = value;
                }
            } else {
                o[key] = value;
            }
        });

        // $form.find('input, select, textarea').filter(function (i, e) {
        //     return $(e).attr('name').indexOf('_') != 0;
        // }).each(function (i, e) {
        //     var $e = $(e);
        //     var key = $e.attr('name');
        //     var value = $e.val();
        //     if (key in o) {
        //         if (!(o[key] instanceof Array)) {
        //             var temp = o[key];
        //             o[key] = [];
        //             o[key].push(temp);
        //         }
        //         o[key].push(value);
        //     } else {
        //         o[key] = value;
        //     }
        // });

        return o;
    };

    var grid = $('#stubGrid').DataTable({
        processing: true,
        serverSide: true,
        scrollX: true,
        ajax: {
            url: '/profile/grid',
            data: function (data) {
                data['searchFilter'] = JSON.stringify(serializeForm());
            }
        },
        columns: [
            {
                title: 'id',
                data: 'id'
            }
        ],
        initComplete: function (settings, data) {
            var $cardList = $('#cardList');
            $cardList.insertBefore('#stubTable');
            $cardList.show();
        },
        rowCallback: function (row, data, index) {
            console.log("Row callback called");
            console.log(data);
        },
        preDrawCallback: function (settings) {
            $('#cardList').empty();
        }
    });


});