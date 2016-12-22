package nl.zoostation.database.exception;

/**
 * @author valentinnastasi
 */
public class InvalidParameterException extends BusinessException {

    public InvalidParameterException(ErrorMessage errorMessage, String className, String parameterName) {
        super(errorMessage, className, parameterName);
    }

}
