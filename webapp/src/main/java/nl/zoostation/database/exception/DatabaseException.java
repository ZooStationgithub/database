package nl.zoostation.database.exception;

/**
 * @author valentinnastasi
 */
public class DatabaseException extends ZooStationException {

    public DatabaseException() {
        super(ErrorMessage.DATABASE);
    }

    public DatabaseException(Throwable cause) {
        super(ErrorMessage.DATABASE, cause);
    }

    public DatabaseException(ErrorMessage errorMessage, Object... messageParams) {
        super(errorMessage, messageParams);
    }

    public DatabaseException(ErrorMessage errorMessage, Object[] messageParams, Throwable cause) {
        super(errorMessage, messageParams, cause);
    }
}
