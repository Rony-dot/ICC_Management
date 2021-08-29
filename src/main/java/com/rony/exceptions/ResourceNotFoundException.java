package com.rony.exceptions;

import org.springframework.transaction.support.ResourceTransactionDefinition;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

}
