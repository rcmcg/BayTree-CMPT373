package com.baytree_mentoring.baytree_mentoring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailedAddingGoalException extends RuntimeException{
    public FailedAddingGoalException() {
    }

    public FailedAddingGoalException(String message) {
        super(message);
    }

    public FailedAddingGoalException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedAddingGoalException(Throwable cause) {
        super(cause);
    }

    public FailedAddingGoalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

