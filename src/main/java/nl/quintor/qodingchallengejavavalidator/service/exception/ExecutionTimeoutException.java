package nl.quintor.qodingchallengejavavalidator.service.exception;

import nl.quintor.qodingchallengejavavalidator.rest.customexception.CustomException;

public class ExecutionTimeoutException extends CustomException {

    private static final String MESSAGE = "Execution of script timed out";

    public ExecutionTimeoutException() {
        this(MESSAGE);
    }

    public ExecutionTimeoutException(String details) {
        this(MESSAGE, details);
    }

    public ExecutionTimeoutException(String details, String nextActions) {
        super(MESSAGE, details, nextActions);
    }
}
