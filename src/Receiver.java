public class Receiver extends Componente {
    private String mensajeRecibido;

    public void receive_signal(Mensaje mensaje) {
        System.out.println("Mensaje recibido");
        String[] morse = mensaje.getMensaje().split(",");
        String texto = "";
        for (String letra : morse) {
            texto += Morse.desmorse(letra);
        }

        mensajeRecibido = texto;

    }

    public void display_message() {
        System.out.println(mensajeRecibido);
    }

    @Override
    public String toString() {
        return "Receiver";
    }

    @Override
    protected String getNombre() {
        return "Receptor";
    }
}
