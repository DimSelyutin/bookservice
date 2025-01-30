package com.ifortex.bookservice.exception;

import java.io.Serial;
import java.io.Serializable;

/**
 * An exception that occurs when an unsuccessful attempt is made to extract a
 * book.
 */
public class FailedFetchBookException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public FailedFetchBookException() {
        super("Failed to fetch book");
    }

    /**
     * Constructor with an error message.
     *
     * @param message Error message.
     */
    public FailedFetchBookException(String message) {
        super(message);
    }

    /**
     * Constructor with error message and reason.
     *
     * @param message Error message.
     * @param cause   The reason for the exclusion.
     */
    public FailedFetchBookException(String message, Throwable cause) {
        super(message, cause);
    }
}
