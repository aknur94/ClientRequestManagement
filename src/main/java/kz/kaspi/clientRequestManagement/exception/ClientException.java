package kz.kaspi.clientRequestManagement.exception;

public class ClientException extends Exception {

    public final static String INVALID_PHONE = "Phone number is invalid";
    public final static String ALREADY_EXISTS = "This client already has been inserted";

    public ClientException() {
        super();
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }

    protected ClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
