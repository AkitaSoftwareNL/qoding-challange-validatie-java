package nl.quintor.qodingchallengejavavalidator.service.exception;

public class CanNotCompileException extends Exception {
    public CanNotCompileException() {
        super("Cant compile code");
    }

    public CanNotCompileException(String message) {
        super(message);
    }

    public CanNotCompileException(Throwable cause) {
        super("Cant compile code", cause);
    }

    public CanNotCompileException(String message, Throwable cause) {
        super(message, cause);
    }
}
