package ru.hh.school.checkupextension.utils.exception;

public class ResourceNotFoundException extends RuntimeException {
  public static final String PROBLEM_ID_NOT_FOUND_MESSAGE = "Problem not found with id: %s";
  public static final String SUBMISSION_ID_NOT_FOUND_MESSAGE = "Submission not found with id: %s";
  public static final String VERIFICATION_ID_NOT_FOUND_MESSAGE = "Verification not found with id: %s";

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
