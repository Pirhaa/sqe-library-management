/**
 * Custom exception for Library file I/O failures.
 * Wraps the underlying IOException so callers don't need to know
 * about low-level file/stream details.
 */
public class LibraryIOException extends Exception {

    public LibraryIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public LibraryIOException(String message) {
        super(message);
    }
}