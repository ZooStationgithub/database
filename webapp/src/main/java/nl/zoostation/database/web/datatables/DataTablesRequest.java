package nl.zoostation.database.web.datatables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * Container class for encapsulating a response from DataTables.
 * </p>
 *
 * @author val
 */
public class DataTablesRequest {

    /**
     * A request-response synchronization marker. Needs to be copied to the
     * response object.
     */
    private Integer drawCounter;

    /**
     * 0-based number of the first row to be drawn in the grid. Used for data
     * paging.
     */
    private Integer pageStart;

    /**
     * Maximum number of rows to be drawn in the grid. Used for data paging.
     */
    private Integer pageLength;

    /**
     * A filter value that should be applied to all filterable columns in the
     * grid.
     */
    private String globalFilter;

    /**
     * A container describing the ordering strategy of the grid.
     */
    private OrderDefinition orderDefinition;

    /**
     * Map that holds some useful description properties for each column in the
     * grid.
     */
    private Map<Integer, ColumnDefinition> columnDefinitions;

    /**
     * Map holding all other request parameters.
     */
    private Map<String, String> extras;

    public DataTablesRequest() {
        orderDefinition = new OrderDefinition();
        columnDefinitions = new HashMap<>();
    }

    public List<String> getFilterableColumns() {
        return columnDefinitions.values().stream()
                .filter(ColumnDefinition::getSearchable)
                .map(ColumnDefinition::getName)
                .collect(toList());
    }

    public String getOrderColumn() {
        return columnDefinitions.entrySet().stream()
                .filter((entry) -> orderDefinition.getColumnIndex().equals(entry.getKey()))
                .limit(1)
                .map((entry) -> entry.getValue().getName())
                .reduce((one, two) -> one)
                .orElse(null);
    }

    public String getOrderDirection() {
        return orderDefinition.getDirection();
    }

    public Integer getDrawCounter() {
        return drawCounter;
    }

    public void setDrawCounter(Integer drawCounter) {
        this.drawCounter = drawCounter;
    }

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getPageLength() {
        return pageLength;
    }

    public void setPageLength(Integer pageLength) {
        this.pageLength = pageLength;
    }

    public String getGlobalFilter() {
        return globalFilter;
    }

    public void setGlobalFilter(String globalFilter) {
        this.globalFilter = globalFilter;
    }

    public OrderDefinition getOrderDefinition() {
        return orderDefinition;
    }

    public void setOrderDefinition(OrderDefinition orderDefinition) {
        this.orderDefinition = orderDefinition;
    }

    public Map<Integer, ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    public void setColumnDefinitions(Map<Integer, ColumnDefinition> columnDefinitions) {
        this.columnDefinitions = columnDefinitions;
    }

    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DataTablesRequest{");
        sb.append("drawCounter=").append(drawCounter);
        sb.append(", pageStart=").append(pageStart);
        sb.append(", pageLength=").append(pageLength);
        sb.append(", globalFilter='").append(globalFilter).append('\'');
        sb.append(", orderDefinition=").append(orderDefinition);
        sb.append(", columnDefinitions=").append(columnDefinitions);
        sb.append(", extras=").append(extras);
        sb.append('}');
        return sb.toString();
    }

    /**
     * <p>
     * Container class for defining an ordering strategy for a DataTables grid.
     * </p>
     *
     * @author val
     */
    static class OrderDefinition {

        /**
         * Index of the column on which ordering is applied.
         */
        private Integer columnIndex;

        /**
         * Order (sorting) direction. Possible values are {@code asc} and
         * {@code desc}.
         */
        private String direction;

        public OrderDefinition() {
        }

        public Integer getColumnIndex() {
            return columnIndex;
        }

        public void setColumnIndex(Integer columnIndex) {
            this.columnIndex = columnIndex;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("OrderDefinition{");
            sb.append("columnIndex=").append(columnIndex);
            sb.append(", direction='").append(direction).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * <p>
     * Container class for describing the properties of a column within a
     * DataTables grid.
     * </p>
     *
     * @author val
     */
    static class ColumnDefinition {

        /**
         * JSON property name of the column.
         */
        private String name;

        /**
         * Name of the column, which will be rendered in the grid table's
         * header.
         */
        private String header;

        /**
         * Whether this column is subject to filtering.
         */
        private Boolean searchable;

        /**
         * Whether this column is subject to sorting.
         */
        private Boolean orderable;

        /**
         * A filter value that should be applied only to this column.
         */
        private String filterValue;

        public ColumnDefinition() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public Boolean getSearchable() {
            return searchable;
        }

        public void setSearchable(Boolean searchable) {
            this.searchable = searchable;
        }

        public Boolean getOrderable() {
            return orderable;
        }

        public void setOrderable(Boolean orderable) {
            this.orderable = orderable;
        }

        public String getFilterValue() {
            return filterValue;
        }

        public void setFilterValue(String filterValue) {
            this.filterValue = filterValue;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ColumnDefinition{");
            sb.append("name='").append(name).append('\'');
            sb.append(", header='").append(header).append('\'');
            sb.append(", searchable=").append(searchable);
            sb.append(", orderable=").append(orderable);
            sb.append(", filterValue='").append(filterValue).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

}
