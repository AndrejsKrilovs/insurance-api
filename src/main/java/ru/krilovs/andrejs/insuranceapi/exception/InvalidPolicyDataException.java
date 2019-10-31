package ru.krilovs.andrejs.insuranceapi.exception;

public final class InvalidPolicyDataException extends InvalidPolicyStructureException {
    @Override
    public String getMessage() {
        return "Incorrect policy data!";
    }
}
