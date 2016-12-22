package nl.zoostation.database.exception;

/**
 * @author valentinnastasi
 */
public class ZooStationException extends RuntimeException {

    public ZooStationException() {
        super(ErrorMessage.build(ErrorMessage.GENERAL));
    }

    public ZooStationException(Throwable cause) {
        super(ErrorMessage.build(ErrorMessage.GENERAL, new Object[]{}, cause));
    }

    public ZooStationException(ErrorMessage errorMessage, Object... messageParams) {
        super(ErrorMessage.build(errorMessage, messageParams));
    }

    public ZooStationException(ErrorMessage errorMessage, Object[] messageParams, Throwable cause) {
        super(ErrorMessage.build(errorMessage, messageParams), cause);
    }

}
