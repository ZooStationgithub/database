/**
 * Created by valentinnastasi on 08/12/2016.
 */
RoleTypeForm = function(tabContext) {
    this.tabContext = tabContext;
};

RoleTypeForm.prototype = function() {

    var panelSelector = '#panelFormRole';

    var showPanel = function(thisObj) {
        $(panelSelector).find('.panel-body').collapse('show');
    };

    var hidePanel = function(thisObj) {
        $(panelSelector).find('.panel-body').collapse('hide');
    };

    var init = function(thisObj) {
        $(panelSelector).find('form').submit(function(event) {

            $.ajax({
                url: '/admin/data/role',
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
        }).fail(function(xhr) {
            alert(xhr.responseJSON.message);
        });
    };

    var open = function(id, thisObj) {
        thisObj = thisObj || this;

        var data = {};
        if (id) {
            data.id = id;
        }

        $.ajax({
            url: '/admin/data/role/form',
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
