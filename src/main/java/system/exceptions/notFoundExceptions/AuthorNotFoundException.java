package system.exceptions.notFoundExceptions;

public class AuthorNotFoundException extends RuntimeException {
    private static final long SERIAL_VERSION_UID = 2;

    public AuthorNotFoundException(String message) {
        super(message);
    }
}
