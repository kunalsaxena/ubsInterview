package com.ubs.opsit.interviews.exceptions;

/**
 * Invalid time related Custom Exception
 */
public class InvalidTimeException extends RuntimeException {

    public InvalidTimeException(String message) {
        super(message);
    }
}
