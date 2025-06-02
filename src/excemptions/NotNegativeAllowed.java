package excemptions;

public class NotNegativeAllowed extends RuntimeException {
    public NotNegativeAllowed(String message) {
        super(message);
    }
}
