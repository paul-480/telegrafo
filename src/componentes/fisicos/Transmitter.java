package componentes.fisicos;

import componentes.Encendible;
import excemptions.DispositivoApagadoExcemption;
import logica.*;

public class Transmitter extends Encendible {
    private boolean encendido;
    private Cable cable;

    public Transmitter(boolean encendido) {
        this.encendido = encendido;
    }

    public void conectarCable(Cable cable) {

        this.cable = cable;
        System.out.println("componentes.fisicos.Cable conectado al transmisor");
    }

    public boolean estaEncendido() {
        return encendido;
    }

    public void encender() {
        System.out.println("Encendiendo el transmisor");
        encendido = true;
    }

    public void apagar() {
        System.out.println("Apagando el transmisor");
        encendido = false;
    }

    public void send_signal(String texto) {
        if (estaEncendido()) {

            StringBuilder mensajeCodificado = new StringBuilder();
            for (int i = 0; i < texto.length(); i++) {
                mensajeCodificado.append(Morse.traducir(texto.charAt(i)));
                mensajeCodificado.append(",");
            }
            Mensaje mensaje = new Mensaje(mensajeCodificado.toString());

            System.out.println("logica.Mensaje enviado desde el transmisor");

            cable.transmit(mensaje);

        } else {
            throw new DispositivoApagadoExcemption("Transmisor Apagado");
        }
    }

    @Override
    public String toString() {
        return "componentes.fisicos.Transmitter{" +
                "encendido=" + estaEncendido() +
                '}';
    }

    @Override
    public String getNombre() {
        return "Transmisor, ";
    }
}
