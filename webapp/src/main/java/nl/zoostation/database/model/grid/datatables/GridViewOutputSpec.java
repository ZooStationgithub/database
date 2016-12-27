package nl.zoostation.database.model.grid.datatables;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GridViewOutputSpec{");
        sb.append("totalRecords=").append(totalRecords);
        sb.append(", filteredRecords=").append(filteredRecords);
        sb.append(", records=").append(records);
        sb.append('}');
        return sb.toString();
    }
}
