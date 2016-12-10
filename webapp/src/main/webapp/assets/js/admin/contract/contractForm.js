/**
 * Created by valentinnastasi on 08/12/2016.
 */
ContractTypeForm = function(tabContext) {
    this.tabContext = tabContext;
};

ContractTypeForm.prototype = function() {

    var panelSelector = '#panelFormContractType';

    var showPanel = function(thisObj) {
        $(panelSelector).find('.panel-body').collapse('show');
    };

    var hidePanel = function(thisObj) {
        $(panelSelector).find('.panel-body').collapse('hide');
    };

    var init = function(thisObj) {
        $(panelSelector).find('form').submit(function(event) {

            $.ajax({
                url: '/admin/data/contract',
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
            });

            event.preventDefault();
        });
    };

    var open = function(id, thisObj) {
        thisObj = thisObj || this;

        var data = {};
        if (id) {
            data.id = id;
        }

        $.ajax({
            url: '/admin/data/contract/form',
            method: 'get',
            data: data
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
