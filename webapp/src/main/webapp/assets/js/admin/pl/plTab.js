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
            $('#programming-languages').empty().append(responseHtml);
            thisObj.grid.init();
        });
    };

    return  {
        show : show
    }
}();