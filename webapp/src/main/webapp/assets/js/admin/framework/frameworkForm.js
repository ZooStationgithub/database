/**
 * Created by valentinnastasi on 10/12/2016.
 */
FrameworkForm = function(tabContext) {
    this.tabContext = tabContext;
};

FrameworkForm.prototype = function() {

    var panelSelector = '#panelFormFramework';

    var showPanel = function(thisObj) {
        $(panelSelector).find('.panel-body').collapse('show');
    };

    var hidePanel = function(thisObj) {
        $(panelSelector).find('.panel-body').collapse('hide');
    };

    var init = function(thisObj) {
        $(panelSelector).find('form #pl-id').select2();

        $(panelSelector).find('form').submit(function(event) {

            $.ajax({
                url: '/admin/data/framework',
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

    var open = function(id, thisObj) {
        thisObj = thisObj || this;

        var data = {};
        if (id) {
            data.id = id;
        }

        $.ajax({
            url: '/admin/data/framework/form',
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
