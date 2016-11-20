/**
 * Created by valentinnastasi on 25/10/2016.
 */
$(document).ready(function () {

    var $tokenOriginCountry = $('input#country-origin').tokenInput('/profile/country/tokens', {
        searchDelay: 500,
        minChars: 3,
        tokenLimit: 1,
        prePopulate: $('input#country-origin').data('selected')
    });

    var $tokenPreferredCountries = $('input#country-preferred').tokenInput('/profile/country/tokens', {
        searchDelay: 500,
        minChars: 3,
        tokenLimit: null,
        prePopulate: $('input#country-preferred').data('selected')
    });

    var $cardList = $('#cardList');

    var serializeForm = function () {
        var o = {};
        $('#formSearch').find('input, select, textarea').each(function (i, e) {
            var $e = $(e);

            if ($.isEmptyObject($e.attr('name'))) {
                return;
            }

            if ($e.attr('id') == 'country-origin') {
                var tokens = $tokenOriginCountry.tokenInput('get');
                o['originCountryId'] = $.isEmptyObject(tokens) ? '' : tokens[0].id;
                return;
            }

            if ($e.attr('id') == 'country-preferred') {
                var tokens = $tokenPreferredCountries.tokenInput('get');
                o['preferredCountryIds'] = $.isEmptyObject(tokens) ? [] : tokens.map(function(a) {return a.id});
                return;
            }

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

    $cardList.on('click', 'button[target="view-details"]', function (event) {
        setTimeout(function () {
            var id = event.target.dataset['id'];
            if (id == undefined) {
                id = event.target.parentNode.dataset['id'];
            }
            window.location.href = contextPath + '/developer?u=' + id;
        }, 0);
    });

    $cardList.on('click', 'button[target="edit"]', function (event) {
        setTimeout(function () {
            var id = event.target.dataset['id'];
            if (id == undefined) {
                id = event.target.parentNode.dataset['id'];
            }
            window.location.href = contextPath + '/developer/edit?u=' + id;
        }, 0);
    });

    $('#btnSearch').off('click').on('click', function(event) {
        grid.draw();
    });

});