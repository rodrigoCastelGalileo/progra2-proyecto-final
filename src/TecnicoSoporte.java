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

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Límite de tickets: " + limiteTickets);
    }
}