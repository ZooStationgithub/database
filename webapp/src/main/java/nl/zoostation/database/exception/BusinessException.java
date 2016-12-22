package nl.zoostation.database.exception;

/**
 * @author valentinnastasi
 */
public class BusinessException extends ZooStationException {

    public BusinessException() {
        super(ErrorMessage.SERVICE);
    }

    public BusinessException(Throwable cause) {
        super(ErrorMessage.SERVICE, cause);
    }

    public BusinessException(ErrorMessage errorMessage, Object... messageParams) {
        super(errorMessage, messageParams);
    }

    public BusinessException(ErrorMessage errorMessage, Object[] messageParams, Throwable cause) {
        super(errorMessage, messageParams, cause);
    }

}
