/**
 * Created by valentinnastasi on 08/12/2016.
 */
RoleTypeTab = function(pageContext) {

    this.pageContext = pageContext;
    this.grid = new RoleTypeGrid(this);
    this.form = new RoleTypeForm(this);

};

RoleTypeTab.prototype = function() {

    var show = function(thisObj) {
        thisObj = thisObj || this;

        $.ajax({
            url : '/admin/data/role/tab'
        }).done(function(responseHtml) {
            var $role = $('#role-types');
            $role.empty().append(responseHtml);
            thisObj.grid.init();
            setTimeout(function () {
                $role.trigger('resize');
            }, 1000);
        });
    };

    return  {
        show : show
    }
}();