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
            var $company = $('#company-types');
            $company.empty().append(responseHtml);
            thisObj.grid.init();
            setTimeout(function () {
                $company.trigger('resize');
            }, 1000);
        });
    };

    return  {
        show : show
    }
}();