package com.ifortex.bookservice.exception;

/**
 * An exception that occurs when book not found.
 */
public class BookNotFoundException extends RuntimeException {
    /**
     * Constructor with an error message.
     *
     * @param message Error message.
     */
    public BookNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor with error message and reason.
     *
     * @param message Error message.
     * @param cause   The reason for the exclusion.
     */
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
