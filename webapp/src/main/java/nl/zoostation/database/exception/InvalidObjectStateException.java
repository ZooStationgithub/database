package nl.zoostation.database.exception;

/**
 * @author valentinnastasi
 */
public class InvalidObjectStateException extends BusinessException {

    public InvalidObjectStateException(ObjectDescriptor objectDescriptor, String detailedMessage) {
        super(ErrorMessage.INVALID_OBJECT_STATE, objectDescriptor.toString(), detailedMessage);
    }

}
