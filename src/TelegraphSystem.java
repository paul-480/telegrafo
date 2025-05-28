import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TelegraphSystem {
    private final List<Componente> componentes =new ArrayList<>();
    private static final Scanner in = new Scanner(System.in);

    public TelegraphSystem(){

    }

    private void addTransmitter(){
        System.out.println("Deseas encender el transmisor?");
        System.out.println("(1)->Sí");
        System.out.println("(2)->No");
        boolean encendido = in.nextInt() ==1;
        Transmitter transmisor = new Transmitter(encendido);
        System.out.println("Transmisor creado");
        componentes.add(transmisor);
        Cable cable= addCable();
        transmisor.conectarCable(cable);
    }
    private Cable addCable(){
        System.out.println("Introduzca la longitud del cable");
        int longitud = in.nextInt();
        Cable cable= new Cable(longitud);
        componentes.add(cable);
        System.out.println("Cable creado");

        System.out.println("Deseas añadir un repetidor o un receptor?");
        System.out.println("(1)->Repetidor");
        System.out.println("(2)->Receptor");

        if (in.nextInt()==1){
            cable.conectarCable(addrelay());
        }else {
            cable.conectarCable(addReciever());
        }

        return cable;
    }
    private Relay addrelay(){
        System.out.println("Deseas encender el repetidor?");
        System.out.println("(1)->Sí");
        System.out.println("(2)->No");
        boolean encendido = in.nextInt() ==1;
        Relay repetidor = new Relay(encendido);
        System.out.println("Transmisor creado");
        componentes.add(repetidor);
        Cable cable= addCable();
        repetidor.conectarCable(cable);
        return repetidor;
    }

    private Receiver addReciever(){
        Receiver repetidor = new Receiver();
        System.out.println("Repetidor Creado");
        componentes.add(repetidor);
        return repetidor;
    }
}
