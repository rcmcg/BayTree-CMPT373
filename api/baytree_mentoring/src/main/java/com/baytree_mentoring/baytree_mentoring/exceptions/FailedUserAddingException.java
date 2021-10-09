package com.baytree_mentoring.baytree_mentoring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailedUserAddingException extends RuntimeException{
    public FailedUserAddingException() {
    }

    public FailedUserAddingException(String message) {
        super(message);
    }

    public FailedUserAddingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedUserAddingException(Throwable cause) {
        super(cause);
    }

    public FailedUserAddingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

