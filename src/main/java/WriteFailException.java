public class WriteFailException extends Exception {
    public WriteFailException() {
        super();
    }

    public WriteFailException(String message) {
        super(message);
    }

    public WriteFailException(Throwable cause) {
        super(cause);
    }

    public WriteFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
