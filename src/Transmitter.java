import java.util.ArrayList;
import java.util.List;

public class Transmitter extends Componente {
    private boolean encendido;
    private Cable cable;

    public Transmitter(boolean encendido){
        this.encendido = encendido;
    }

    public void conectarCable(Cable cable){
        this.cable = cable;
    }

    public boolean estaEncendido() {
        return encendido;
    }

    public void encender(){
        System.out.println("Encendiendo el transmisor");
        encendido = true;
    }

    public void apagar(){
        System.out.println("Apagando el transmisor");
        encendido=false;
    }

    public void send_signal(String texto){
        if(encendido){

            String mensajeCodificado="";
            for (int i = 0; i < texto.length(); i++) {
                mensajeCodificado += codificar(texto.charAt(i));
            }
        Mensaje mensaje = new Mensaje(mensajeCodificado);

        System.out.println("Mensaje enviado");

        cable.transmit(mensaje);

        }else {throw new DispositivoApagadoExcemption("Transmisor Apagado");}
    }

    private static String codificar(char letra){
        switch (letra) {
            case 'a' -> { return ".-"; }
            case 'b' -> { return "-..."; }
            case 'c' -> { return "-.-."; }
            case 'd' -> { return "-.."; }
            case 'e' -> { return "."; }
            case 'f' -> { return "..-."; }
            case 'g' -> { return "--."; }
            case 'h' -> { return "...."; }
            case 'i' -> { return ".."; }
            case 'j' -> { return ".---"; }
            case 'k' -> { return "-.-"; }
            case 'l' -> { return ".-.."; }
            case 'm' -> { return "--"; }
            case 'n' -> { return "-."; }
            case 'o' -> { return "---"; }
            case 'p' -> { return ".--."; }
            case 'q' -> { return "--.-"; }
            case 'r' -> { return ".-."; }
            case 's' -> { return "..."; }
            case 't' -> { return "-"; }
            case 'u' -> { return "..-"; }
            case 'v' -> { return "...-"; }
            case 'w' -> { return ".--"; }
            case 'x' -> { return "-..-"; }
            case 'y' -> { return "-.--"; }
            case 'z' -> { return "--.."; }
            default -> { return "?"; }
        }
    }
}
