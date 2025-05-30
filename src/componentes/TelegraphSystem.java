package componentes;

import componentes.abstractos.Componente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TelegraphSystem {
    private final List<Componente> componentes = new ArrayList<>();
    private static final Scanner in = new Scanner(System.in);

    public TelegraphSystem() {
        addTransmitter();
    }

    private void addTransmitter() {
        System.out.println("Añadiendo transmisor");
        System.out.println("Deseas encender el transmisor?");
        System.out.println("(1)->Sí");
        System.out.println("(2)->No");
        boolean encendido = in.nextInt() == 1;
        in.nextLine();
        Transmitter transmisor = new Transmitter(encendido);
        System.out.println("Transmisor creado");
        componentes.add(transmisor);
        System.out.println("Transmisor añadido al sistema");
        System.out.println("Creando cable para el transmisor");
        Cable cable = addCable();
        transmisor.conectarCable(cable);
        System.out.println("componentes.Cable conectado al transmisor");
    }

    private Cable addCable() {

        System.out.println("Introduzca la longitud del cable(km)");
        int longitud = in.nextInt();
        in.nextLine();
        Cable cable = new Cable(longitud);
        componentes.add(cable);
        System.out.println("componentes.Cable creado");

        System.out.println("Deseas añadir un repetidor o un receptor?");
        System.out.println("(1)->Repetidor");
        System.out.println("(2)->Receptor");

        if (in.nextInt() == 1) {
            cable.conectarCable(addrelay());
        } else {
            cable.conectarCable(addReciever());
        }

        return cable;
    }

    private Relay addrelay() {
        Relay repetidor;

        System.out.println("Deseas un repetidor con bateria?");
        System.out.println("(1)->Sí");
        System.out.println("(2)->No");
        boolean cargable = in.nextInt() == 1;
        in.nextLine();

        if (cargable) repetidor = createRelayBL();

        else repetidor = createRelay();


        System.out.println("Transmisor creado");
        componentes.add(repetidor);
        System.out.println("Creando cable para el repetidor");
        Cable cable = addCable();
        repetidor.conectarCable(cable);
        return repetidor;
    }

    private static Relay createRelay() {
        Relay repetidor;
        System.out.println("Deseas encender el repetidor?");
        System.out.println("(1)->Sí");
        System.out.println("(2)->No");
        boolean encendido = in.nextInt() == 1;
        in.nextLine();
        repetidor = new Relay(encendido);
        return repetidor;
    }

    private static RelayBL createRelayBL() {
        RelayBL repetidor;
        boolean encendido;
        int bateria;
        System.out.println("Deseas encender el repetidor?");
        System.out.println("(1)->Sí");
        System.out.println("(2)->No");
        encendido = in.nextInt() == 1;
        in.nextLine();
        System.out.println("Introduzca la cantidad de batería");
        bateria = in.nextInt();
        in.nextLine();
        repetidor = new RelayBL(encendido, bateria);
        System.out.println("Repetidor Creado");

        return repetidor;
    }

    private Receiver addReciever() {
        Receiver repetidor = new Receiver();
        System.out.println("Receptor Creado");
        componentes.add(repetidor);
        return repetidor;
    }

    public void listarComponentes() {
        for (Componente componente : componentes) {
            System.out.println(componente);
        }
    }

    public void cargarRepetidores() {
        for (Componente componente : componentes) {
            if (componente instanceof RelayBL) {
                ((RelayBL) componente).cargar();
            }
        }
    }

    public void enviarMensaje() {
        Transmitter transmitter = componentes.getFirst() instanceof Transmitter ? ((Transmitter) componentes.getFirst()) : null;
        assert transmitter != null;
        System.out.print("Introduce el mensaje: ");
        in.nextLine();
        String texto = in.nextLine().toLowerCase();
        transmitter.send_signal(texto);
    }

    public void showMensaje() {
        Receiver receiver = null;
        for (Componente componente : componentes) {
            if (componente instanceof Receiver) receiver = (Receiver) componente;
        }
        assert receiver != null;
        receiver.display_message();
    }

    @Override
    public String toString() {
        StringBuilder lista= new StringBuilder("Sistema( ");
        for (Componente componente : componentes) {
            lista.append(componente.getNombre());
        }
        lista.append(")");
        return lista.toString();
    }
}
