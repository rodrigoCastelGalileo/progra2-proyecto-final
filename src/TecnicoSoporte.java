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

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Límite de tickets: " + limiteTickets);
    }
}