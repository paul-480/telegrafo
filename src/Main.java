import componentes.Componente;
import componentes.Encendible;
import excemptions.NotFoundExcemption;
import componentes.fisicos.TelegraphSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    private static final HashMap<String, TelegraphSystem> sistemas = new HashMap<>();
    private static final Scanner in = new Scanner(System.in);

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
        System.out.println("(3)-> Mostrar lista de sistemas");
        System.out.println("(4)-> Salir");
        System.out.print("Su elección: ");
    }

    private static boolean menuPrincipal() {
        int opcion = in.nextInt();
        in.nextLine();
        switch (opcion) {
            case 1 -> {

                addSystem();
                return false;
            }
            case 2 -> {
                return mostrarMenuSistema();
            }
            case 3 -> {

                showAllSistemas();
                return false;
            }
            case 4 -> {
                System.out.println("Saliendo...");
                return true;
            }
            default -> {
                System.err.println("Opción no valida");
                return false;
            }

        }
    }

    private static boolean mostrarMenuSistema() {
        TelegraphSystem sistema = chooseSistema();
        boolean salir;
        do {
            textoMenuSistema();
            salir = menuSistema(sistema);
        } while (!salir);
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
                menuComponente(sistema);

                return false;
            }
            case 3 -> {
                sistema.enviarMensaje();
                return false;
            }
            case 4 -> {
                sistema.showMensaje();
                return false;
            }
            case 5 -> {
                return true;
            }
            default -> {
                System.out.println("Opción invalida");
                return false;
            }
        }
    }

    private static void menuComponente(TelegraphSystem sistema) {


        int opcion;

        do {
            System.out.println("<##########>Administrar componente<#########>");
            System.out.println("(1)-> Encender un componente");
            System.out.println("(2)-> Apagar un componente");
            System.out.println("(3)-> Cargar repetidores");
            System.out.println("(4)-> Volver.");
            opcion = in.nextInt();

            switch (opcion) {
                case 1 -> {

                    Componente componente = sistema.seleccionarComponente();
                    if (componente instanceof Encendible encendible) {
                        encendible.encender();
                    } else {
                        throw new NotFoundExcemption("No encontrado o no encendible");
                    }


                }
                case 2 -> {
                    Componente componente = sistema.seleccionarComponente();
                    if (componente instanceof Encendible encendible) {
                        encendible.apagar();
                    } else {
                        throw new NotFoundExcemption("No encontrado o no apagable");
                    }

                }
                case 3 -> {
                    sistema.cargarRepetidores();
                }
                case 4 -> {
                    System.out.println("Saliendo...");
                }
                default -> {
                    System.out.println("Opción invalida");
                }
            }
        }
        while (opcion != 4);


    }

    private static TelegraphSystem chooseSistema() {
        System.out.println("Cual es el nombre del Sistema?");
        String clave = in.next();
        TelegraphSystem sistema = sistemas.get(clave);
        if (sistema == null) throw new NotFoundExcemption("Sistema no encontrado");
        else return sistema;

    }

    private static void textoMenuSistema() {
        System.out.println("<############>Menú del sistema <##########>");
        System.out.println("(1)-> Mostrar los componentes ");
        System.out.println("(2)-> Administrar componentes");
        System.out.println("(3)-> Enviar un mensaje");
        System.out.println("(4)-> leer el mensaje");
        System.out.println("(5)-> Volver");
        System.out.print("Su elección: ");


    }

    private static void addSystem() {
        System.out.println("Ha seleccionado Crear nuevo sistema");
        String clave;
        System.out.println("Introduzca un nombre para su sistema");
        clave = in.next();
        TelegraphSystem sistema = new TelegraphSystem();
        sistemas.put(clave, sistema);
    }

}