package nl.zoostation.database.exception;

import java.text.MessageFormat;

/**
 * @author valentinnastasi
 */
public enum ErrorMessage {

    GENERAL ("ZS-000", "A general exception occurred"),

    DATABASE ("ZS-010", "A general database exception occurred"),
    UNIQUE_KEY_VIOLATION("ZS-011", "Cannot execute DML statement, unique key constraint violation"),
    FOREIGN_KEY_VIOLATION("ZS-012", "Cannot execute DML statement, foreign key constraint violation"),

    SERVICE ("ZS-020", "A general business exception occurred"),
    OBJECT_NOT_FOUND ("ZS-021", "{0} was not found"),
    NULL_PARAMETER ("ZS-022", "Class ''{0}'': parameter ''{1}'' must not be null"),
    EMPTY_STRING_PARAMETER ("ZS-0223", "Class ''{0}'': string parameter ''{1}'' must not be null or empty"),
    EMPTY_COLLECTION_PARAMETER ("ZS-024", "Class ''{0}'': collection parameter ''{1}'' must not be null or empty"),
    EMPTY_MAP_PARAMETER ("ZS-024", "Class ''{0}'': map parameter ''{1}'' must not be null or empty"),
    INVALID_OBJECT_STATE ("ZS-025", "{0} is an invalid state: {1}"),

    MAIL("ZS-030", "Could not send mail")
    ;

    private String code;
    private String messageTemplate;

    ErrorMessage(String code, String messageTemplate) {
        this.code = code;
        this.messageTemplate = messageTemplate;
    }

    public String getCode() {
        return code;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    public static String build(ErrorMessage errorMessage, Object... parameters) {
        return errorMessage.getCode() + ": " + MessageFormat.format(errorMessage.getMessageTemplate(), parameters);
    }

}
