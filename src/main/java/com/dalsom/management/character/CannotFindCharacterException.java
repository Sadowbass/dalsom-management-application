package com.dalsom.management.character;

public class CannotFindCharacterException extends RuntimeException {

    public CannotFindCharacterException() {
        super();
    }

    public CannotFindCharacterException(String message) {
        super(message);
    }

    public CannotFindCharacterException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotFindCharacterException(Throwable cause) {
        super(cause);
    }

    protected CannotFindCharacterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
