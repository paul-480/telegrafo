package logica;

import componentes.fisicos.TelegraphSystem;
import excemptions.NotNegativeAllowed;

import java.util.Scanner;

public class Utilities {
    private static final Scanner in = new Scanner(System.in);

    public static boolean isEncendido() {
        System.out.println("El dispositivo está encendido?");
        System.out.println("(1)->Sí");
        System.out.println("(2)->No");
        return in.nextInt() == 1;
    }

    public static int validarPositivo(int num){
        if (num > 0 ) return num;
        else throw new NotNegativeAllowed("No puede ser negativo");
    }

    public static Scanner in(){
        return in;
    }
}
