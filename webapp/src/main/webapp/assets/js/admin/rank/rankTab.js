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
            var $rank = $('#rank-types');
            $rank.empty().append(responseHtml);
            thisObj.grid.init();
            setTimeout(function () {
                $rank.trigger('resize');
            }, 1000);
        });
    };

    return  {
        show : show
    }
}();