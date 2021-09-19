package com.rony.exceptions;

public class ResourceAccessDeniedException extends RuntimeException{
    public ResourceAccessDeniedException() {
    }

    public ResourceAccessDeniedException(String message) {
        super(message);
    }

    public ResourceAccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
