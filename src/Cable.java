public class Cable extends Componente {
    private final int longitud;
    private Componente receptor;

    public Cable(int longitud) {
        this.longitud = longitud;
    }

    public void transmit(Mensaje mensaje){
        mensaje.debilitar(longitud);
        if (receptor instanceof Relay){
            ((Relay) receptor).amplify_signal(mensaje);
        } else if (receptor instanceof Receiver) {
            ((Receiver) receptor).receive_signal(mensaje);
        }else throw new RuntimeException("Dispositivo no reconocido");
    }


}
