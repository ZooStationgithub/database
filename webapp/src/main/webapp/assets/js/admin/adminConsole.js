/**
 * Created by valentinnastasi on 07/12/2016.
 */
AdminConsolePage = function() {
    var thisObj = this;
    thisObj.tabMap = {
        0: new AccountTab(thisObj),
        1: new ProgrammingLanguageTab(thisObj),
        2: new FrameworkTab(thisObj),
        3: new ContractTypeTab(thisObj),
        4: new CompanyTypeTab(thisObj)
    };
    thisObj.init();
};

AdminConsolePage.prototype = function() {

    var handlers = function(thisObj) {
      return {
          onTabShow: function(event) {
              var index = $(event.target).data('tabIndex');
              thisObj.tabMap[index].show();
          }
      }
    };

    var tabBarSelector = '#adminTabBar';

    var init = function(thisObj) {
        thisObj = thisObj || this;

        // Bind tab event handlers
        $(tabBarSelector).find('a[data-toggle="tab"]').on('show.bs.tab', handlers(thisObj).onTabShow);
    };

    var openTab = function(index, thisObj) {
        $(tabBarSelector).find('li:eq(' + index + ') a').tab('show');
    };

    return  {
        init : init,
        openTab : openTab
    }
}();

$(document).ready(function () {

    var pageContext = new AdminConsolePage();
    pageContext.openTab(0);

});