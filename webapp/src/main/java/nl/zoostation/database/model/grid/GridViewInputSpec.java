package nl.zoostation.database.model.grid;

import java.util.Collection;

/**
 * @author val
 */
public final class GridViewInputSpec {

    public static final String ORDER_ASC = "asc";
    public static final String ORDER_DESC = "desc";

    private Integer pageStart;
    private Integer pageLength;
    private String globalFilter;
    private String orderColumn;
    private String orderDirection;
    private Collection<String> filterableColumns;

    public GridViewInputSpec() {
    }

    public GridViewInputSpec(Integer pageStart, Integer pageLength, String globalFilter, String orderColumn,
                             String orderDirection, Collection<String> filterableColumns) {
        this.pageStart = pageStart;
        this.pageLength = pageLength;
        this.globalFilter = globalFilter;
        this.orderColumn = orderColumn;
        this.orderDirection = orderDirection;
        this.filterableColumns = filterableColumns;
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

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public Collection<String> getFilterableColumns() {
        return filterableColumns;
    }

    public void setFilterableColumns(Collection<String> filterableColumns) {
        this.filterableColumns = filterableColumns;
    }
}
