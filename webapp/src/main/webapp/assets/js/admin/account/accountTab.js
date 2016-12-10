/**
 * Created by valentinnastasi on 07/12/2016.
 */
AccountTab = function(pageContext) {

    this.pageContext = pageContext;
    this.grid = new AccountGrid(this);
    this.form = new AccountForm(this);

};

AccountTab.prototype = function() {

    var show = function(thisObj) {
        thisObj = thisObj || this;

        $.ajax({
            url : '/admin/account/tab'
        }).done(function(responseHtml) {
            var $accounts = $('#accounts');
            $accounts.empty().append(responseHtml);
            thisObj.grid.init();
            setTimeout(function () {
                $accounts.trigger('resize');
            }, 1000);
        });
    };

    return  {
        show : show
    }

}();