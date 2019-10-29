package ru.krilovs.andrejs.insuranceapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
public final class InvalidPoliceStructureException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Incorrect policy structure!";
    }
}
