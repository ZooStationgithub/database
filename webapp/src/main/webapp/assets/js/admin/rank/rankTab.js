/**
 * Created by valentinnastasi on 08/12/2016.
 */
RankTypeTab = function(pageContext) {

    this.pageContext = pageContext;
    this.grid = new RankTypeGrid(this);
    this.form = new RankTypeForm(this);

};

RankTypeTab.prototype = function() {

    var show = function(thisObj) {
        thisObj = thisObj || this;

        $.ajax({
            url : '/admin/data/rank/tab'
        }).done(function(responseHtml) {
            $('#rank-types').empty().append(responseHtml);
            thisObj.grid.init();
        });
    };

    return  {
        show : show
    }
}();