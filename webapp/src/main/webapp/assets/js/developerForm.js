/**
 * Created by valentinnastasi on 25/10/2016.
 */
$(document).ready(function() {

    var FormObject = function() {
        return {
            'zsNumber' : null,
            'rankTypeId' : null,
            'mainProgrammingLanguageId' : null,
            'secondProgrammingLanguageId' : null,
            'knownFrameworkIds' : [],
            'testRating' : null,
            'originCountryId' : null,
            'preferredCountryIds' : [],
            'contractTypeId' : null,
            'visaNeeded' : null,
            'preferredCompanyTypeIds' : [],
            'experience' : null,
            'roleTypeId' : null,
            'workHistory' : null,
            'englishLevel' : null,
            'travelTime' : null,
            'preferredCity' : null,
            'availability' : null,
            'hoursPerWeek' : null,
            'relocationReason' : null,
            'customFields' : {}
        }
    };

    var $tokenOriginCountry = $('input#country-origin').tokenInput('/profile/country/tokens', {
        searchDelay: 500,
        minChars: 3,
        tokenLimit: 1,
        prePopulate: $('input#country-origin').data('selected'),
    });

    var $tokenPreferredCountries = $('input#country-preferred').tokenInput('/profile/country/tokens', {
        searchDelay: 500,
        minChars: 3,
        tokenLimit: null,
        prePopulate: $('input#country-preferred').data('selected')
    });

    var serializeForm = function () {
        var formObject = {};

        formObject.id = $('input[name="id"]').val();

        formObject.rankTypeId = $('#grade').val();

        formObject.mainProgrammingLanguageId = $('#mpl').val();

        formObject.secondProgrammingLanguageId = $('#spl').val();

        formObject.knownFrameworkIds = $.merge([], $('#frameworks').val());

        formObject.testRating = $('#rating-cd').val();

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

        formObject.experience = $('#experience').val();

        selectors = $('input[name="roleTypeId"]:checked');
        formObject.roleTypeId = selectors.length > 0 ? selectors.val() : null;

        formObject.workHistory = $('#worked-before').val().trim();

        formObject.englishLevel = $('#english-level').val();

        formObject.travelTime = $('#travel').val();

        formObject.preferredCity = $('#place-live').val().trim();

        formObject.availability = $('#availability').val();

        formObject.hoursPerWeek = $('#hours-week').val();

        formObject.relocationReason = $('#reasons').val().trim();

        return formObject;
    };

    $('#btnSave').one('click', function(event) {
        $.ajax({
            url: '/developer/edit',
            method: 'post',
            contentType : 'application/json',
            data : JSON.stringify(serializeForm())
        }).done(function(data) {
            location.replace(contextPath + '/index'); // TODO add popup
        }).fail(function(data) {
            alert("Failure :(");
        });
    });

    $('#btnDelete').one('click', function(event) {
        $.ajax({
            url: '/developer/delete',
            method: 'post',
            data: {
                'u': $(event.target).data('id')
            }
        }).done(function(data) {
            location.replace(contextPath + '/index'); // TODO add popup
        }).fail(function(data) {
            alert("Failure :(");
        });
    });

});