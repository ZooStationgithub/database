package nl.zoostation.database.web.datatables;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

/**
 * <p>
 * Container for encapsulating a DataTables response object.
 * </p>
 *
 * @param <T>
 * @author val
 */
public class DataTablesResponse<T> {

    /**
     * A request-response synchronization marker. Needs to be the same as
     * {@link DataTablesRequest#drawCounter}
     */
    @JsonProperty(DataTablesConstants.Response.DRAW_COUNTER)
    private Integer drawCounter;

    /**
     * Total number of rows found in the database.
     */
    @JsonProperty(DataTablesConstants.Response.TOTAL_RECORDS)
    private Long totalRecords;

    /**
     * Total number of rows in the database after applying filtering.
     */
    @JsonProperty(DataTablesConstants.Response.FILTERED_RECORDS)
    private Long filteredRecords;

    /**
     * List of data objects for rendering the rows.
     */
    @JsonProperty(DataTablesConstants.Response.DATA)
    private Collection<T> records;

    /**
     * Optional message in case request threw an error.
     */
    @JsonProperty(DataTablesConstants.Response.ERROR)
    private String error;

    public DataTablesResponse() {
    }

    public DataTablesResponse(Integer drawCounter, Long totalRecords, Long filteredRecords, Collection<T> records) {
        super();
        this.drawCounter = drawCounter;
        this.totalRecords = totalRecords;
        this.filteredRecords = filteredRecords;
        this.records = records;
    }

    public Integer getDrawCounter() {
        return drawCounter;
    }

    public void setDrawCounter(Integer drawCounter) {
        this.drawCounter = drawCounter;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DataTablesResponse{");
        sb.append("drawCounter=").append(drawCounter);
        sb.append(", totalRecords=").append(totalRecords);
        sb.append(", filteredRecords=").append(filteredRecords);
        sb.append(", records=").append(records);
        sb.append(", error='").append(error).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
