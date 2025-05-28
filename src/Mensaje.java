public class Mensaje {
    private final String mensaje;
    private int signal;

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
        this.signal = 100;
    }

    public void amplified(){
        this.signal=100;
    }

    public void debilitar(int kmRecorridos){
        for (int i = 0; i < kmRecorridos; i++) {
            signal=signal-(signal/10);
        }
        System.out.println("Señal actual: "+signal);
    }

    public String getMensaje() {

        if (signal>10) {
            return mensaje;
        }else throw new NoSignalExcemption();

    }
}
