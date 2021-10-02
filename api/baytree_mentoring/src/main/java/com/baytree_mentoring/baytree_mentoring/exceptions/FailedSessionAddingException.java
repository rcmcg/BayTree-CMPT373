package com.baytree_mentoring.baytree_mentoring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailedSessionAddingException extends RuntimeException{
    public FailedSessionAddingException() {
    }

    public FailedSessionAddingException(String message) {
        super(message);
    }

    public FailedSessionAddingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedSessionAddingException(Throwable cause) {
        super(cause);
    }

    public FailedSessionAddingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

