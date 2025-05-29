import java.rmi.NoSuchObjectException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    private static HashMap<String, TelegraphSystem> sistemas = new HashMap<>();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir;
        do {

            textoMenuPrincipal();
            salir = menuPrincipal();

        } while (!salir);


    }

    private static void textoMenuPrincipal() {
        System.out.println("<############>Menú Principal<##########>");
        System.out.println("(1)-> Introducir un nuevo sistema");
        System.out.println("(2)-> Utilizar un sistema existente");
        System.out.println("(3)-> Mostrar los componentes de un sistema");
        System.out.println("(4)-> Cargar los repetidores de un sistema");
        System.out.println("(5)-> Mostrar lista de sistemas");
        System.out.println("(6)-> Enviar un mensaje por un sistema");
        System.out.print("Su elección: ");
    }

    private static boolean menuPrincipal() {
        int opcion = in.nextInt();
        in.nextLine();
        switch (opcion) {
            case 1 -> {
                System.out.println("Ha seleccionado Crear nuevo sistema");
                addSystem();
                return false;
            }
            case 2 -> {
                TelegraphSystem sistema = chooseSistema();

                boolean salir;
                do {
                    textoMenuSistema();
                    salir = menuSistema(sistema);
                } while (!salir);
                return false;
            }
            case 3 -> {
                TelegraphSystem sistema = chooseSistema();
                sistema.listarComponentes();
                return false;
            }
            case 4 -> {
                TelegraphSystem sistema = chooseSistema();
                sistema.cargarRepetidores();
                return false;
            }
            case 5 -> {
                showAllSistemas();
                return false;
            }
            case 6 -> {
                TelegraphSystem sistema = chooseSistema();
                sistema.enviarMensaje();
                sistema.showMensaje();
            }
            case 7 -> {
                return true;
            }
            default -> {
                System.err.println("Opción no valida");
                return false;
            }

        }
        return false;
    }

    private static void showAllSistemas() {
        for (Map.Entry<String, TelegraphSystem> entry : sistemas.entrySet()) {
            System.out.println(entry.toString());
        }
    }

    private static boolean menuSistema(TelegraphSystem sistema) {
        int opcion = in.nextInt();
        in.nextLine();
        switch (opcion) {
            case 1 -> {
                sistema.listarComponentes();
                return false;
            }
            case 2 -> {
                sistema.cargarRepetidores();
                return false;
            }
            case 3 -> {
                sistema.enviarMensaje();
                return false;
            }
            case 4 -> {
                return true;
            }
            default -> {
                System.out.println("Opción invalida");
                return false;
            }
        }
    }

    private static TelegraphSystem chooseSistema() {
        System.out.println("Cual es el nombre del Sistema?");
        String clave = in.next();
        TelegraphSystem sistema = sistemas.get(clave);
        if (sistema == null) throw new NotFoundExcemption("Sistema no encontrado");
        else return sistema;

    }

    private static void textoMenuSistema() {
        System.out.println("<############>Menú Principal<##########>");
        System.out.println("(1)-> Mostrar los componentes ");
        System.out.println("(2)-> Cargar los repetidores");
        System.out.println("(3)-> Enviar un mensaje");
        System.out.println("(4)-> Volver");
        System.out.print("Su elección: ");


    }

    private static void addSystem() {
        String clave;
        System.out.println("Introduzca un nombre para su sistema");
        clave = in.next();
        TelegraphSystem sistema = new TelegraphSystem();
        sistemas.put(clave, sistema);
    }

}