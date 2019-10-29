package ru.krilovs.andrejs.insuranceapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public final class PolicyNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Policy not found!";
    }
}
