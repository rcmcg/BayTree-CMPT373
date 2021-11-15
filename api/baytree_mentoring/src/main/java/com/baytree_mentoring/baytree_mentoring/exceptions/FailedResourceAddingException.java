package com.baytree_mentoring.baytree_mentoring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailedResourceAddingException extends RuntimeException{
    public FailedResourceAddingException() {
    }

    public FailedResourceAddingException(String message) {
        super(message);
    }

    public FailedResourceAddingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedResourceAddingException(Throwable cause) {
        super(cause);
    }

    public FailedResourceAddingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
