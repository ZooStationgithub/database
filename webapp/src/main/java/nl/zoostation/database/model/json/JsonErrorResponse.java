package nl.zoostation.database.model.json;

import java.io.Serializable;

/**
 * @author valentinnastasi
 */
public class JsonErrorResponse implements Serializable {

    private int httpCode;
    private String exceptionClass;
    private String message;

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public void setExceptionClass(String exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
