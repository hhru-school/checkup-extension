package ru.hh.school.checkupextension.utils.exception;

public class SubmissionNotFoundException extends RuntimeException {
  public SubmissionNotFoundException(String message) {
    super(message);
  }

  public SubmissionNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
