package ru.hh.school.checkupextension.utils.exception.core;

public class VerificationNotFoundException extends RuntimeException {
    public static final String VERIFICATION_ID_NOT_FOUND_MESSAGE = "Verification not found with id: %s";

    public VerificationNotFoundException(long id) {
        super(String.format(VERIFICATION_ID_NOT_FOUND_MESSAGE, id));
    }

    public VerificationNotFoundException(String message) {
        super(message);
    }

    public VerificationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
