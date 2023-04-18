package ru.hh.school.checkupextension.utils.exception;

public class VerificationNotFoundException extends RuntimeException {
  public VerificationNotFoundException(String message) {
    super(message);
  }

  public VerificationNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}