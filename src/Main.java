public class Main {

    public static void main(String[] args) {

        SistemaTickets sistema = new SistemaTickets(
                "SISTEMA DE TICKETS DE SOPORTE"
        );

        sistema.mostrarBienvenida();

        Usuario usuario = new Usuario(
                "Carlos Mendoza",
                1001,
                "carlos@empresa.com",
                "cmendoza",
                "Contabilidad"
        );

        TecnicoSoporte tecnico = new TecnicoSoporte(
                "Ana López",
                2001,
                "ana@empresa.com",
                5
        );

        Ticket ticket = sistema.crearTicket(
                1,
                "Problema",
                "La computadora no tiene conexión a Internet",
                "21/09/2026",
                "PENDIENTE",
                "ALTA",
                usuario
        );

        sistema.asignarTicket(ticket, tecnico);

        sistema.mostrarTicket(ticket);
    }
}