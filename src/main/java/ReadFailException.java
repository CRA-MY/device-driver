public class ReadFailException extends Exception {
    public ReadFailException() {
        super();
    }

    public ReadFailException(String message) {
        super(message);
    }

    public ReadFailException(Throwable cause) {
        super(cause);
    }

    public ReadFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
