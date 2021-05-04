package com.ytterbrink.phonecase.exceptions;

import com.ytterbrink.phonecase.exceptions.NothingToSeeYetException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class NoPhoneCasesYetAdvice {
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NothingToSeeYetException.class)
    public void handleConflict(){

    }

}
