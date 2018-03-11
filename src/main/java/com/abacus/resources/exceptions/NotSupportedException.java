package com.abacus.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class NotSupportedException extends RuntimeException {
    public NotSupportedException(String exception) {
        super(exception);
    }
}
