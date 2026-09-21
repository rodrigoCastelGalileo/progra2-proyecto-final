public class SistemaTickets {

    private String nombreSistema;

    public SistemaTickets(String nombreSistema) {
        this.nombreSistema = nombreSistema;
    }

    public void mostrarBienvenida() {
        System.out.println("====================================");
        System.out.println(" " + nombreSistema);
        System.out.println("====================================");
    }

    public Ticket crearTicket(
            int id,
            String tipo,
            String descripcion,
            String fechaCreacion,
            String estado,
            String prioridad,
            Usuario usuario
    ) {
        return new Ticket(
                id,
                tipo,
                descripcion,
                fechaCreacion,
                estado,
                prioridad,
                usuario
        );
    }

    public void asignarTicket(
            Ticket ticket,
            TecnicoSoporte tecnico
    ) {
        ticket.asignarTecnico(tecnico);
    }

    public void mostrarTicket(Ticket ticket) {
        ticket.mostrarInformacion();
    }
}