package componentes.fisicos;

import componentes.Componente;
import logica.Mensaje;

public class Cable extends Componente {
    private final int longitud;
    private Componente receptor;

    public Cable(int longitud) {
        this.longitud = longitud;
    }


    public void conectarCable(Componente componente) {
        this.receptor = componente;
        System.out.println("componentes.fisicos.Cable conectado al receptor");
    }

    public void transmit(Mensaje mensaje) {
        mensaje.debilitar(longitud);
        enviar(mensaje);
    }

    private void enviar(Mensaje mensaje) {
        if (receptor instanceof Relay) {
            enviarARelay(mensaje);
        } else if (receptor instanceof Receiver) {
            enviarAReceptor(mensaje);
        } else throw new RuntimeException("Dispositivo no reconocido");
    }

    private void enviarAReceptor(Mensaje mensaje) {
        ((Receiver) receptor).receive_signal(mensaje);
    }

    private void enviarARelay(Mensaje mensaje) {
        ((Relay) receptor).amplify_signal(mensaje);
    }

    @Override
    public String toString() {
        return "componentes.fisicos.Cable{ longitud=" + longitud + '}';
    }

    @Override
    public String getNombre() {
        return "componentes.fisicos.Cable, ";
    }
}
