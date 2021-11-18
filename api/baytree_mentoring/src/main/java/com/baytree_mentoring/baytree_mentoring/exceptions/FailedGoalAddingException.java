package com.baytree_mentoring.baytree_mentoring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailedGoalAddingException extends RuntimeException{
    public FailedGoalAddingException() {
    }

    public FailedGoalAddingException(String message) {
        super(message);
    }

    public FailedGoalAddingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedGoalAddingException(Throwable cause) {
        super(cause);
    }

    public FailedGoalAddingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

