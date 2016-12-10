/**
 * Created by valentinnastasi on 08/12/2016.
 */
ContractTypeTab = function(pageContext) {

    this.pageContext = pageContext;
    this.grid = new ContractTypeGrid(this);
    this.form = new ContractTypeForm(this);

};

ContractTypeTab.prototype = function() {

    var show = function(thisObj) {
        thisObj = thisObj || this;

        $.ajax({
            url : '/admin/data/contract/tab'
        }).done(function(responseHtml) {
            $('#contract-types').empty().append(responseHtml);
            thisObj.grid.init();
        });
    };

    return  {
        show : show
    }
}();