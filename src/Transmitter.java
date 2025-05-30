public class Transmitter extends Componente {
    private boolean encendido;
    private Cable cable;

    public Transmitter(boolean encendido) {
        this.encendido = encendido;
    }

    public void conectarCable(Cable cable) {

        this.cable = cable;
        System.out.println("Cable conectado al transmisor");
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
        if (encendido) {

            String mensajeCodificado = "";
            for (int i = 0; i < texto.length(); i++) {
                mensajeCodificado += Morse.codificar(texto.charAt(i));
                mensajeCodificado +=",";
            }
            Mensaje mensaje = new Mensaje(mensajeCodificado);

            System.out.println("Mensaje enviado desde el transmisor");

            cable.transmit(mensaje);

        } else {
            throw new DispositivoApagadoExcemption("Transmisor Apagado");
        }
    }

    @Override
    public String toString() {
        return "Transmitter{" +
                "encendido=" + encendido +
                '}';
    }

    @Override
    protected String getNombre() {
        return "Transmisor, ";
    }
}
