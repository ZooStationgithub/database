/**
 * Created by valentinnastasi on 25/10/2016.
 */
$(document).ready(function() {

    var template = $.templates($('#customFieldTemplate').html());

    $('select#grade').select2();
    $('select#mpl').select2();
    $('select#spl').select2();
    $('select#frameworks').select2();
    $('select#country-origin').select2();
    $('select#country-preferred').select2();

    var collectCustomFields = function() {
        var customFields = {};
        $('#containerMore').find('div[data-type="fieldGroup"]').each(function(i, e) {
            var $this = $(this);
            var key = $this.find('input[data-type="fieldName"]').val().trim();
            var value = $this.find('input[data-type="fieldValue"]').val().trim();
            if (key && value) {
                customFields[key] = value;
            }
        });
        return customFields;
    };

    var serializeForm = function () {
        var formObject = {};

        formObject.id = $('input[name="id"]').val();

        formObject.rankTypeId = $('#grade').val();

        formObject.mainProgrammingLanguageId = $('#mpl').val();

        formObject.secondProgrammingLanguageId = $('#spl').val();

        formObject.knownFrameworkIds = $('#frameworks').val() || [];

        formObject.testRating = $('#rating-cd').val();

        formObject.originCountryId = $('#country-origin').val();

        formObject.preferredCountryIds = $('#country-preferred').val() || [];

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

        formObject.customFields = collectCustomFields();

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
        }).fail(function(xhr) {
            alert(xhr.responseJSON.message);
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
        }).fail(function(xhr) {
            alert(xhr.responseJSON.message);
        });
    });

    $('#btnMoreFields').on('click', function() {
        $('#containerMore').append(template.render());
    });

    $('#containerMore').on('click', 'button[data-type="deleteField"]', function(e) {
         $(this).closest('div[data-type="fieldGroup"]').remove();
    });

});