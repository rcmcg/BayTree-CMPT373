package com.baytree_mentoring.baytree_mentoring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailedMonthlyQuestionnaireAddingException extends RuntimeException{
    public FailedMonthlyQuestionnaireAddingException() {
    }

    public FailedMonthlyQuestionnaireAddingException(String message) {
        super(message);
    }

    public FailedMonthlyQuestionnaireAddingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedMonthlyQuestionnaireAddingException(Throwable cause) {
        super(cause);
    }

    public FailedMonthlyQuestionnaireAddingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

