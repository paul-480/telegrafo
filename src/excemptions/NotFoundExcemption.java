package excemptions;

public class NotFoundExcemption extends RuntimeException {
    public NotFoundExcemption(String message) {
        super(message);
    }
}
