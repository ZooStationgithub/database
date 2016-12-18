/**
 * Created by valentinnastasi on 25/10/2016.
 */
$(document).ready(function () {

    $('#srch-grade').select2();
    $('#srch-mpl').select2();
    $('#srch-spl').select2();
    $('#srch-frameworks').select2();
    $('#srch-country-origin').select2();
    $('#srch-country-preferred').select2();

    var $cardList = $('#cardList');

    var serializeForm = function () {
        var formObject = {};

        var $srchZsNumber = $('#srch-zsNumber');
        formObject.zsNumber = $srchZsNumber.val() != undefined ? $srchZsNumber.val().trim() : null;

        formObject.rankTypeId = $('#srch-grade').val();

        formObject.mainProgrammingLanguageId = $('#srch-mpl').val();

        formObject.secondProgrammingLanguageId = $('#srch-spl').val();

        formObject.knownFrameworkIds = $('#srch-frameworks').val() || [];

        formObject.testRating = $('#srch-rating-cd').val();

        formObject.originCountryId = $('#srch-country-origin').val();

        formObject.preferredCountryIds = $('#srch-country-preferred').val();

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