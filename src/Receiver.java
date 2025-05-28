public class Receiver extends Componente {
    private String  mensajeRecibido;

    public void receive_signal(Mensaje mensaje){
        String[] morse =  mensaje.getMensaje().split(",");
        String texto="";
        for (String letra : morse) {
            texto+=desmorse(letra);
        }

        mensajeRecibido = texto;

    }

    public void display_message(){
        System.out.println(mensajeRecibido);
    }

    private static char desmorse(String letra) {
        switch (letra) {
            case ".-"    -> { return 'a'; }
            case "-..."  -> { return 'b'; }
            case "-.-."  -> { return 'c'; }
            case "-.."   -> { return 'd'; }
            case "."     -> { return 'e'; }
            case "..-."  -> { return 'f'; }
            case "--."   -> { return 'g'; }
            case "...."  -> { return 'h'; }
            case ".."    -> { return 'i'; }
            case ".---"  -> { return 'j'; }
            case "-.-"   -> { return 'k'; }
            case ".-.."  -> { return 'l'; }
            case "--"    -> { return 'm'; }
            case "-."    -> { return 'n'; }
            case "---"   -> { return 'o'; }
            case ".--."  -> { return 'p'; }
            case "--.-"  -> { return 'q'; }
            case ".-."   -> { return 'r'; }
            case "..."   -> { return 's'; }
            case "-"     -> { return 't'; }
            case "..-"   -> { return 'u'; }
            case "...-"  -> { return 'v'; }
            case ".--"   -> { return 'w'; }
            case "-..-"  -> { return 'x'; }
            case "-.--"  -> { return 'y'; }
            case "--.."  -> { return 'z'; }
            default      -> { return '?'; }
        }
    }

    @Override
    public String toString() {
        return "Receiver";
    }
}
