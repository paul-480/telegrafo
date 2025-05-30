package excemptions;

public class NoSignalExcemption extends RuntimeException {
    public NoSignalExcemption() {
        super("logica.Mensaje perdido,  NO SIGNAL");
    }
}
