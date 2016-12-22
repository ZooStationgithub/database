package nl.zoostation.database.exception;

/**
 * @author valentinnastasi
 */
public class MailServiceException extends BusinessException {

    public MailServiceException(Throwable cause) {
        super(ErrorMessage.MAIL, cause);
    }

}
