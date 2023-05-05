package ru.hh.school.checkupextension.utils.exception;

public class SubmissionNotFoundException extends RuntimeException {
  static final String SUBMISSION_ID_NOT_FOUND_MESSAGE = "Submission not found with id: %s";
  public SubmissionNotFoundException(String message) {
    super(message);
  }

  public SubmissionNotFoundException(long submissionId) {
    super(String.format(SUBMISSION_ID_NOT_FOUND_MESSAGE, submissionId));
  }

  public SubmissionNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
