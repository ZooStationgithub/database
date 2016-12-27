/**
 * Created by valentinnastasi on 08/12/2016.
 */
AccountForm = function(tabContext) {
    this.tabContext = tabContext;
};

AccountForm.prototype = function() {

    var panelSelector = '#panelFormAccount';

    var showPanel = function(thisObj) {
        $(panelSelector).find('.panel-body').collapse('show');
    };

    var hidePanel = function(thisObj) {
        $(panelSelector).find('.panel-body').collapse('hide');
    };

    var init = function(thisObj) {
        $(panelSelector).find('form').submit(function(event) {

            $.ajax({
                url: '/admin/account',
                method: 'post',
                data: $(this).serialize()
            }).done(function(response) {
                if (!response) {
                    thisObj.tabContext.grid.refresh();
                    hidePanel();
                } else {
                    $(panelSelector).find('div.panel-body').empty().append(response);
                    init(thisObj);
                }
            }).fail(function(xhr) {
                alert(xhr.responseJSON.message);
            });

            event.preventDefault();
        });
    };

    var open = function(thisObj) {
        thisObj = thisObj || this;

        $.ajax({
            url: '/admin/account/form',
            method: 'get'
        }).done(function(response) {
           $(panelSelector).find('div.panel-body').empty().append(response);
           init(thisObj);
           showPanel(thisObj);
        });

    };

    return {
        open : open
    }
}();
