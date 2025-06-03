package componentes.fisicos;

import excemptions.DispositivoApagadoExcemption;
import logica.Mensaje;
import logica.Utilities;

import static logica.Utilities.in;

public class RelayBL extends Relay {


    private int bateria;

    public RelayBL(boolean estado, int bateria) {
        super(estado);
        setBateria(bateria);
        System.out.println("Repetidor Creado");
    }

    public RelayBL() {
        RelayBL repetidor;
        int bateria;
        System.out.println("Introduzca la cantidad de batería");
        bateria = in().nextInt();
        in().nextLine();

        this.bateria=bateria;
        this.estado= Utilities.isEncendido();

    }

    @Override
    public void encender() {
        System.out.println("Repetidor encendido");
        estado = true;
        this.bateria = 5;
    }

    /**
     * Introduce un Repeater con batería limitada: solo puede amplificar un número finito de señales
     * antes de "agotarse" (requiere recarga manual o automática)
     */
//############################################################################
    @Override
    public void amplify_signal(Mensaje mensaje) {

        if (getBateria() <= 0) {
            System.out.print("Bateria insuficiente, ");
            apagar();
        }

        if (estado) {
            mensaje.amplified();
            System.out.println("logica.Mensaje reintensificado");
            cableSalida.transmit(mensaje);
            bateria--;
        } else throw new DispositivoApagadoExcemption("Repetidor apagado");

    }

    //############################################################################
    @Override
    public String getNombre() {
        return "Repetidor con bateria, bateria restante: " + bateria + ", ";
    }

    public int getBateria() {
        return bateria;
    }

    public void cargar() {
        this.bateria = 5;
        encender();
    }

    /**
     * Valores controlados de bateria
     */
    public void setBateria(int bateria) {
        if (bateria <= 5 && bateria >= 0) this.bateria = bateria;
        else if (bateria > 5) this.bateria = 5;
        else this.bateria = 0;
    }

    @Override
    public String toString() {
        return "componentes.fisicos.Relay{" + "estado=" + estaActivo() + " Bateria=" + getBateria() + '}';
    }
}
