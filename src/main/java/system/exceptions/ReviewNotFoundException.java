package system.exceptions;


public class ReviewNotFoundException extends RuntimeException {
    private static final long SERIAL_VERSION_UID = 3;

    public ReviewNotFoundException(String message) {
        super(message);
    }
}
