package nl.zoostation.database.model.grid;

import java.util.Collection;

/**
 * @author val
 */
public final class GridViewOutputSpec<T> {

    private Long totalRecords;

    private Long filteredRecords;

    private Collection<T> records;

    public GridViewOutputSpec() {
    }

    public GridViewOutputSpec(Long totalRecords, Long filteredRecords, Collection<T> records) {
        this.totalRecords = totalRecords;
        this.filteredRecords = filteredRecords;
        this.records = records;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Long getFilteredRecords() {
        return filteredRecords;
    }

    public void setFilteredRecords(Long filteredRecords) {
        this.filteredRecords = filteredRecords;
    }

    public Collection<T> getRecords() {
        return records;
    }

    public void setRecords(Collection<T> records) {
        this.records = records;
    }
}
