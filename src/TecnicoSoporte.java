// TecnicoSoporte hereda de Persona y agrega el limite de trabajo que puede recibir.
public class TecnicoSoporte extends Persona {

    private int limiteTickets;

    public TecnicoSoporte(
            String nombre,
            int numeroEmpleado,
            String email,
            int limiteTickets
    ) {
        super(nombre, numeroEmpleado, email);
        setLimiteTickets(limiteTickets);
    }

    public int getLimiteTickets() {
        return limiteTickets;
    }

    public void setLimiteTickets(int limiteTickets) {
        if (limiteTickets <= 0) {
            System.out.println(
                    "Error: el límite de tickets debe ser mayor que 0."
            );
            return;
        }

        this.limiteTickets = limiteTickets;
    }

    // Compara los tickets activos del tecnico contra el limite que tiene configurado.
    public boolean puedeRecibirTicket(int ticketsActuales) {

        if (ticketsActuales < 0) {
            System.out.println(
                    "Error: la cantidad de tickets no puede ser negativa."
            );
            return false;
        }

        if (ticketsActuales >= limiteTickets) {
            System.out.println(
                    "El técnico ha alcanzado el límite de tickets."
            );
            return false;
        }

        return true;
    }

    // Reutilizamos los datos de Persona y agregamos el limite del tecnico.
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Límite de tickets: " + limiteTickets);
    }
}