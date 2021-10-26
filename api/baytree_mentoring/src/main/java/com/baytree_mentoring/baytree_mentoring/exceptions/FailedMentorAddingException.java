package com.baytree_mentoring.baytree_mentoring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailedMentorAddingException extends RuntimeException{
    public FailedMentorAddingException() {
    }

    public FailedMentorAddingException(String message) {
        super(message);
    }

    public FailedMentorAddingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedMentorAddingException(Throwable cause) {
        super(cause);
    }

    public FailedMentorAddingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

