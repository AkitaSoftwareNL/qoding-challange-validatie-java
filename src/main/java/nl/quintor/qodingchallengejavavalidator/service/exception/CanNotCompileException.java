package nl.quintor.qodingchallengejavavalidator.service.exception;

import nl.quintor.qodingchallengejavavalidator.rest.customexception.CustomException;

public class CanNotCompileException extends CustomException {

    public static final String MESSAGE = "Can not compile code";

    public CanNotCompileException() {
        super(MESSAGE);
    }

    public CanNotCompileException(String details) {
        super(MESSAGE, details);
    }

    public CanNotCompileException(String details, String nextActions) {
        super(MESSAGE, details, nextActions);
    }
}
