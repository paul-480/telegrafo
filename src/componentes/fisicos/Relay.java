package componentes.fisicos;

import componentes.Encendible;
import excemptions.DispositivoApagadoExcemption;
import logica.Mensaje;

public class Relay extends Encendible {
    protected boolean estado;
    protected Cable cableSalida;


    public Relay(boolean estado) {
        this.estado = estado;
    }


    public void conectarCable(Cable cable) {
        System.out.println("Cable conectado");
        this.cableSalida = cable;
    }

    public void encender() {
        System.out.println("Repetidor encendido");
        estado = true;
    }

    public void apagar() {
        System.out.println("Repetidor apagado");
        estado = false;

    }

    public void amplify_signal(Mensaje mensaje) {
        if (estaActivo()) {
            mensaje.amplified();
            System.out.println("Mensaje reintensificado");
            cableSalida.transmit(mensaje);
        } else throw new DispositivoApagadoExcemption("Repetidor apagado");
    }

    public boolean estaActivo() {
        return estado;
    }

    @Override
    public String toString() {
        return "Relay{" + "estado=" + estado + '}';
    }

    @Override
    public String getNombre() {
        return "Repetidor, ";
    }
}
