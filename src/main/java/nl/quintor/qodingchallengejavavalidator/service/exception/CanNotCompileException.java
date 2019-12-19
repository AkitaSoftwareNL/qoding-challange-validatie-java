package nl.quintor.qodingchallengejavavalidator.service.exception;

import nl.quintor.qodingchallengejavavalidator.rest.customexception.CustomException;

public class CanNotCompileException extends CustomException {

    public static final String MESSAGE = "Can not compile code";

    public CanNotCompileException() {
        this(MESSAGE);
    }

    public CanNotCompileException(String details) {
        this(MESSAGE, details);
    }

    public CanNotCompileException(String details, String nextActions) {
        super(MESSAGE, details, nextActions);
    }
}
