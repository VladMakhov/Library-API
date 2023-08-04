package system.exceptions;

public class BookNotFoundException extends RuntimeException {
    private static final long SERIAL_VERSION_UID = 1;

    public BookNotFoundException(String message) {
        super(message);
    }
}