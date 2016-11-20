/**
 * Created by valentinnastasi on 25/10/2016.
 */
$(document).ready(function () {

    var $tokenOriginCountry = $('input#country-origin').tokenInput('/profile/country/tokens', {
        searchDelay: 500,
        minChars: 3
    });

    var $cardList = $('#cardList');

    var serializeForm = function () {
        var o = {};
        $('#formSearch').find('input, select, textarea')
            .filter(function (i, e) {
                var $e = $(e);
                return $e.attr('id') != 'country-origin';
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

        var tokens = $tokenOriginCountry.tokenInput('get');
        if (!$.isEmptyObject(tokens)) {
            o['originCountryId'] = tokens[0].id;
        }

        return o;
    };

    var template = $.templates($('#cardItemTemplate').html());

    var grid = $('#stubGrid').DataTable({
        processing: true,
        serverSide: true,
        scrollX: true,
        searching: false,
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
            $cardList.insertBefore('#stubTable');
            $cardList.show();
        },
        rowCallback: function (row, data, index) {
            var html = template.render(data);
            $cardList.append(html);
        },
        preDrawCallback: function (settings) {
            $cardList.empty();
        }
    });

    $cardList.on('click', 'button[target="view-details"]', function(event) {
        setTimeout(function() {
            var id = event.target.dataset['id'];
            if (id == undefined) {
                id = event.target.parentNode.dataset['id'];
            }
            window.location.href = contextPath + '/developer?u=' + id;
        }, 0);
    });

    $cardList.on('click', 'button[target="edit"]', function(event) {
        setTimeout(function() {
            var id = event.target.dataset['id'];
            if (id == undefined) {
                id = event.target.parentNode.dataset['id'];
            }
            window.location.href = contextPath + '/developer/edit?u=' + id;
        }, 0);
    });

});