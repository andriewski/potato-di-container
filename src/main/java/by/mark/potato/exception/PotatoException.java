package by.mark.potato.exception;

public class PotatoException extends RuntimeException {

    public PotatoException(String message, Throwable cause) {
        super(message, cause);
    }

    public PotatoException(Throwable cause) {
        super(cause);
    }
}
