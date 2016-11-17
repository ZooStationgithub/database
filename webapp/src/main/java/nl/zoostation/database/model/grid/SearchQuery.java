package nl.zoostation.database.model.grid;

/**
 * @author valentinnastasi
 */
public class SearchQuery {

    private Page page;
    private Order order;
    private SearchFilter filter;

    public SearchQuery() {
        page = new Page();
        order = new Order();
        filter = new SearchFilter();
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public SearchFilter getFilter() {
        return filter;
    }

    public void setFilter(SearchFilter filter) {
        this.filter = filter;
    }

    public static class Page {

        private Integer start;
        private Integer length;

        public Integer getStart() {
            return start;
        }

        public void setStart(Integer start) {
            this.start = start;
        }

        public Integer getLength() {
            return length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }
    }

    public static class Order {

        private String column;
        private String order;

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }

}
