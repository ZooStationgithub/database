/**
 * Created by valentinnastasi on 08/12/2016.
 */
CompanyTypeTab = function(pageContext) {

    this.pageContext = pageContext;
    this.grid = new CompanyTypeGrid(this);
    this.form = new CompanyTypeForm(this);

};

CompanyTypeTab.prototype = function() {

    var show = function(thisObj) {
        thisObj = thisObj || this;

        $.ajax({
            url : '/admin/data/company/tab'
        }).done(function(responseHtml) {
            $('#company-types').empty().append(responseHtml);
            thisObj.grid.init();
        });
    };

    return  {
        show : show
    }
}();