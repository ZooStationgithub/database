/**
 * Created by valentinnastasi on 08/12/2016.
 */
ProgrammingLanguageTab = function(pageContext) {

    this.pageContext = pageContext;
    this.grid = new ProgrammingLanguageGrid(this);
    this.form = new ProgrammingLanguageForm(this);

};

ProgrammingLanguageTab.prototype = function() {

    var show = function(thisObj) {
        thisObj = thisObj || this;

        $.ajax({
            url : '/admin/data/pl/tab'
        }).done(function(responseHtml) {
            var $pl = $('#programming-languages');
            $pl.empty().append(responseHtml);
            thisObj.grid.init();
            setTimeout(function () {
                $pl.trigger('resize');
            }, 1000);
        });
    };

    return  {
        show : show
    }
}();