package componentes;

import componentes.abstractos.Componente;
import logica.*;

public class Receiver extends Componente {
    private String mensajeRecibido;

    public void receive_signal(Mensaje mensaje) {
        System.out.println("logica.Mensaje recibido");
        String[] morse = mensaje.getMensaje().split(",");
        StringBuilder texto = new StringBuilder();
        for (String letra : morse) {
            texto.append(Morse.traducir(letra));
        }

        mensajeRecibido = texto.toString();

    }

    public void display_message() {
        System.out.println(mensajeRecibido);
    }

    @Override
    public String toString() {
        return "componentes.Receiver";
    }

    @Override
    public String getNombre() {
        return "Receptor";
    }
}
