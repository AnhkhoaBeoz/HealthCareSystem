package com.khoabeo.quanlyphongkham.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class ExceptionNotFound extends RuntimeException {
    public ExceptionNotFound(String mess) {
        super(mess);
    }
}
