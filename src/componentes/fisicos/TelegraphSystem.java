package componentes.fisicos;

import componentes.Componente;
import excemptions.NotFoundExcemption;

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
        boolean encendido = isEncendido();
        in.nextLine();
        Transmitter transmisor = new Transmitter(encendido);
        System.out.println("Transmisor creado");
        componentes.add(transmisor);
        System.out.println("Transmisor añadido al sistema");
        System.out.println("Creando cable para el transmisor");
        Cable cable = addCable();
        transmisor.conectarCable(cable);
        System.out.println("Cable conectado al transmisor");
    }

    private static boolean isEncendido() {
        System.out.println("Añadiendo transmisor");
        System.out.println("El dispositivo está encendido?");
        System.out.println("(1)->Sí");
        System.out.println("(2)->No");
        return in.nextInt() == 1;
    }

    private Cable addCable() {

        System.out.println("Introduzca la longitud del cable(km)");
        int longitud = in.nextInt();
        in.nextLine();
        Cable cable = new Cable(longitud);
        componentes.add(cable);
        System.out.println("componentes.fisicos.Cable creado");

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

        componentes.add(repetidor);
        System.out.println("Creando cable para el repetidor");
        Cable cable = addCable();
        repetidor.conectarCable(cable);
        return repetidor;
    }

    private static Relay createRelay() {
        System.out.println("Creando repetidor");
        return new Relay(isEncendido());
    }

    private static RelayBL createRelayBL() {
        RelayBL repetidor;
        int bateria;
        System.out.println("Introduzca la cantidad de batería");
        bateria = in.nextInt();
        in.nextLine();
        repetidor = new RelayBL(isEncendido(), bateria);
        return repetidor;
    }

    private Receiver addReciever() {
        Receiver repetidor = new Receiver();
        componentes.add(repetidor);
        return repetidor;
    }

    public void listarComponentes() {
        System.out.println("=======Lista de componentes=======");
        for (Componente componente : componentes) {
            System.out.println(Integer.toString(componentes.indexOf(componente)) + componente);
        }
    }

    public void cargarRepetidores() {
        for (Componente componente : componentes) {
            if (componente instanceof RelayBL) {
                ((RelayBL) componente).cargar();
            }
        }
    }

    public Componente seleccionarComponente() {
        listarComponentes();
        System.out.println("Selecciona un componente de la lista");
        int index = in.nextInt();
        if (index < 0 || index >= componentes.size()) throw new NotFoundExcemption("Objeto no encontrado");
        else return componentes.get(index);
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
        StringBuilder lista = new StringBuilder("Sistema( ");
        for (Componente componente : componentes) {
            lista.append(componente.getNombre());
        }
        lista.append(")");
        return lista.toString();
    }
}
