package com.ifortex.bookservice.exception;

/**
 * An exception that occurs when Genre is invalid.
 */
public class InvalidGenreException extends RuntimeException {
    /**
     * Constructor with an error message.
     *
     * @param message Error message.
     */
    public InvalidGenreException(String message) {
        super(message);
    }

    /**
     * Constructor with error message and reason.
     *
     * @param message Error message.
     * @param cause   The reason for the exclusion.
     */
    public InvalidGenreException(String message, Throwable cause) {
        super(message, cause);
    }
}
