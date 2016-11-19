package nl.zoostation.database.web.datatables;

/**
 * @author val
 */
public interface DataTablesConstants {

    interface Request {

        String DRAW_COUNTER_PATTERN = "draw";

        String PAGE_START_PATTERN = "start";

        String PAGE_LENGTH_PATTERN = "length";

        String GLOBAL_FILTER_PATTERN = "search\\[value\\]";

        String ORDER_COLUMN_PATTERN = "order\\[0\\]\\[column\\]";

        String ORDER_DIRECTION_PATTERN = "order\\[0\\]\\[dir\\]";

        String COLUMN_DEFINITION_PATTERN = "columns\\[\\d+\\]\\[.+\\]";

        String COLUMN_NAME_PATTERN = ".+\\[data\\]";

        String COLUMN_HEADER_PATTERN = ".+\\[name\\]";

        String COLUMN_SEARCHABLE_PATTERN = ".+\\[searchable\\]";

        String COLUMN_ORDERBALE_PATTERN = ".+\\[orderable\\]";

        String COLUMN_FILTER_PATTERN = ".+\\[search\\]\\[value\\]";

    }

    interface Response {

        String DRAW_COUNTER = "draw";

        String TOTAL_RECORDS = "recordsTotal";

        String FILTERED_RECORDS = "recordsFiltered";

        String DATA = "data";

        String ERROR = "error";

        String ROW_ID = "DT_RowId";

    }

}
