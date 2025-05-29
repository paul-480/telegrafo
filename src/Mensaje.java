import java.util.Random;

public class Mensaje {
    private final String mensaje;
    private int signal;

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
        this.signal = 100;
    }

    public void amplified() {
        this.signal = 100;
    }
//############################################################################

    /**
     * Agrega una probabilidad aleatoria de que un Cable pierda completamente la señal (por
     * ejemplo, 5% de fallo por cada 10 km).
     */
    public void debilitar(int kmRecorridos) {
        Random random = new Random(System.nanoTime());

        for (int i = 0; i < kmRecorridos / 10; i++) {
            if (random.nextInt(100) < 5) signal = 0;
        }


        for (int i = 0; i < kmRecorridos; i++) {
            if (signal > 5) signal = signal - (signal / 10);
        }
        System.out.println("Señal actual: " + signal);
    }

    //############################################################################
    public String getMensaje() {

        if (signal > 10) {
            return mensaje;
        } else throw new NoSignalExcemption();

    }
}
