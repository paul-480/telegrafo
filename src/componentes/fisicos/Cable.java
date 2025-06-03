package componentes.fisicos;

import componentes.Componente;
import jdk.jshell.execution.Util;
import logica.Mensaje;
import logica.Utilities;

import static logica.Utilities.in;

public class Cable extends Componente {
    private final int longitud;
    private Componente receptor;

    public Cable(int longitud) {
        this.longitud = longitud;
    }

    public Cable(){
        System.out.println("Introduzca la longitud del cable(km)");
        this.longitud = Utilities.validarPositivo(in().nextInt());
        System.out.println("Deseas añadir un repetidor o un receptor?");
        System.out.println("(1)->Repetidor");
        System.out.println("(2)->Receptor");

        if (in().nextInt() == 1) {
            Relay repetidor;
            System.out.println("Deseas un repetidor con bateria?");
            System.out.println("(1)->Sí");
            System.out.println("(2)->No");
            boolean cargable = in().nextInt() == 1;
            in().nextLine();

            if (cargable) repetidor = new RelayBL();

            else repetidor = new Relay();
            conectarCable(repetidor);
        } else {
            conectarCable(new Receiver());
        }
    }

    public void conectarCable(Componente componente) {
        this.receptor = componente;
        System.out.println("Cable conectado al receptor");
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
