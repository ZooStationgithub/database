/**
 * Created by valentinnastasi on 25/10/2016.
 */
$(document).ready(function () {

    var $tokenOriginCountry = $('input#srch-country-origin').tokenInput('/profile/country/tokens', {
        searchDelay: 500,
        minChars: 3,
        tokenLimit: 1,
        prePopulate: $('input#srch-country-origin').data('selected')
    });

    var $tokenPreferredCountries = $('input#srch-country-preferred').tokenInput('/profile/country/tokens', {
        searchDelay: 500,
        minChars: 3,
        tokenLimit: null,
        prePopulate: $('input#srch-country-preferred').data('selected')
    });

    var $cardList = $('#cardList');

    var serializeForm = function () {
        var formObject = {};

        formObject.zsNumber = $('#srch-zsNumber').val().trim();

        formObject.rankTypeId = $('#srch-grade').val();

        formObject.mainProgrammingLanguageId = $('#srch-mpl').val();

        formObject.secondProgrammingLanguageId = $('#srch-spl').val();

        formObject.knownFrameworkIds = $.merge([], $('#srch-frameworks').val());

        formObject.testRating = $('#srch-rating-cd').val();

        var tokens = $tokenOriginCountry.tokenInput('get');
        formObject.originCountryId = $.isEmptyObject(tokens) ? null : tokens[0].id;

        formObject.preferredCountryIds = $.merge([], $tokenPreferredCountries.tokenInput('get').map(function(item) { return item.id }));

        var selectors = $('input[name="contractTypeId"]:checked');
        formObject.contractTypeId = selectors.length > 0 ? selectors.val() : null;

        selectors = $('input[name="visaNeeded"]:checked');
        formObject.visaNeeded = selectors.length > 0 ? selectors.val() : null;

        formObject.preferredCompanyTypeIds = [];
        $('input[name="preferredCompanyTypeIds"]:checked').each(function (i, e) {
            formObject.preferredCompanyTypeIds.push($(this).val());
        });

        formObject.experience = $('#srch-experience').val();

        selectors = $('input[name="roleTypeId"]:checked');
        formObject.roleTypeId = selectors.length > 0 ? selectors.val() : null;

        formObject.englishLevel = $('#srch-english-level').val();

        formObject.travelTime = $('#srch-travel').val();

        formObject.preferredCity = $('#srch-place-live').val().trim();

        formObject.availability = $('#srch-availability').val();

        formObject.hoursPerWeek = $('#srch-hours-week').val();

        return formObject;
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