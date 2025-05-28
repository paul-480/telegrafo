import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TelegraphSystem {
    private List<Componente> componentes =new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    public TelegraphSystem(){

    }

    private void addTransmitter(){
        System.out.println("Deseas encender el transmisor?");
        System.out.println("(1)->Sí");
        System.out.println("(2)->No");
        boolean encendido = in.nextInt() ==1;
        Transmitter transmisor = new Transmitter(encendido);

        componentes.add(transmisor);
    }
    private Cable addCable(){
        System.out.println("Introduzca la longitud del cable");
        int longitud = in.nextInt();
        Cable cable= new Cable(longitud);
        componentes.add(cable);
        return cable;
    }
}
