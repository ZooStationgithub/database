/**
 * Created by valentinnastasi on 10/12/2016.
 */
FrameworkTab = function(pageContext) {

    this.pageContext = pageContext;
    this.grid = new FrameworkGrid(this);
    this.form = new FrameworkForm(this);

};

FrameworkTab.prototype = function() {

    var show = function(thisObj) {
        thisObj = thisObj || this;

        $.ajax({
            url : '/admin/data/framework/tab'
        }).done(function(responseHtml) {
            var $frameworks = $('#frameworks');
            $frameworks.empty().append(responseHtml);
            thisObj.grid.init();
            setTimeout(function () {
                $frameworks.trigger('resize');
            }, 1000);
        });
    };

    return  {
        show : show
    }

}();