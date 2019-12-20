package nl.quintor.qodingchallengejavavalidator.rest.customexception;

public class CustomException extends RuntimeException {

    private static final String SUPPORT = "https://quintor.nl/";
    private final String message;
    private final String details;
    private final String nextActions;
    private final String support;

    protected CustomException(String message, String details, String nextActions) {
        this.message = message;
        this.details = details;
        this.nextActions = nextActions;
        this.support = SUPPORT;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public String getNextActions() {
        return nextActions;
    }

    public String getSupport() {
        return support;
    }
}
