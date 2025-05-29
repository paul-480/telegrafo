public class RelayBL extends Relay {


    private int bateria;

    public RelayBL(boolean estado, int bateria) {
        super(estado);
        this.bateria = bateria;
    }

    public RelayBL(int bateria) {
        super();
        setBateria(bateria);
    }

    public RelayBL(boolean estado) {
        super(estado);
        bateria = 5;
    }

    public RelayBL() {
        super();
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

        if (bateria <= 0) {
            System.out.print("Bateria insuficiente, ");
            apagar();
        }

        if (estado) {
            mensaje.amplified();
            System.out.println("Mensaje reintensificado");
            cableSalida.transmit(mensaje);
            bateria--;
        } else throw new DispositivoApagadoExcemption("Repetidor apagado");

    }
//############################################################################
    @Override
    public String getNombre(){
        return "Repetidor con bateria, bateria restante: "+ bateria+", ";
    }

    public int getBateria() {
        return bateria;
    }

    public void cargar() {
        this.bateria = 5;
    }

    /**
     * Valores controlados de bateria
     */
    public void setBateria(int bateria) {
        if (bateria <= 5 && bateria >= 0) this.bateria = bateria;
        else if (bateria > 5) bateria = 5;
        else bateria = 0;
    }

    @Override
    public String toString() {
        return "Relay{" + "estado=" + estado + " Bateria=" + bateria + '}';
    }
}
