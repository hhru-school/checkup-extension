package ru.hh.school.checkupextension.utils.exception;

import ru.hh.school.checkupextension.core.data.Problem;

public class ProblemNotFoundException extends RuntimeException {

    public static final String PROBLEM_ID_NOT_FOUND_MESSAGE = "Problem not found with id: %s";
    public ProblemNotFoundException(Problem problem) {

    }

    public ProblemNotFoundException(Long problemId) {
        super(String.format(PROBLEM_ID_NOT_FOUND_MESSAGE, problemId));
    }

    public ProblemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

