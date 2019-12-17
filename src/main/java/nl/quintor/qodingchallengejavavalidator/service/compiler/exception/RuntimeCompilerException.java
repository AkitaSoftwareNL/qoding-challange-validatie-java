package nl.quintor.qodingchallengejavavalidator.service.compiler.exception;

public class RuntimeCompilerException extends RuntimeException {
    public RuntimeCompilerException() {
        super();
    }

    public RuntimeCompilerException(String message) {
        super(message);
    }

    public RuntimeCompilerException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeCompilerException(Throwable cause) {
        super(cause);
    }

    protected RuntimeCompilerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
