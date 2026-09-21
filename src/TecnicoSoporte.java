public class TecnicoSoporte extends Persona {

    private int limiteTickets;

    public TecnicoSoporte(
            String nombre,
            int numeroEmpleado,
            String email,
            int limiteTickets
    ) {
        super(nombre, numeroEmpleado, email);
        this.limiteTickets = limiteTickets;
    }

    public int getLimiteTickets() {
        return limiteTickets;
    }

    public void setLimiteTickets(int limiteTickets) {
        this.limiteTickets = limiteTickets;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Límite de tickets: " + limiteTickets);
    }
}