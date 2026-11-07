import java.util.ArrayList;
import java.util.HashMap;

// Esta clase se encarga de guardar los registros y hacer las operaciones principales.
public class SistemaTickets {

    private String nombreSistema;

    // Usuario y tecnico usan el numero de empleado como llave para buscarlos rapido.
    private HashMap<Integer, Usuario> usuarios;
    private HashMap<Integer, TecnicoSoporte> tecnicos;

    // Los tickets se guardan en lista porque normalmente los recorremos para consultarlos.
    private ArrayList<Ticket> tickets;

    public SistemaTickets(String nombreSistema) {
        this.nombreSistema = nombreSistema;

        usuarios = new HashMap<>();
        tecnicos = new HashMap<>();
        tickets = new ArrayList<>();
    }

    public void mostrarBienvenida() {
        System.out.println("====================================");
        System.out.println(" " + nombreSistema);
        System.out.println("====================================");
    }

    // Antes de guardar un usuario revisamos que no exista otro con el mismo numero.
    public void registrarUsuario(Usuario usuario) {
        if (usuario == null) {
            System.out.println("Error: el usuario no es válido.");
            return;
        }

        if (usuarios.containsKey(usuario.getNumeroEmpleado())) {
            System.out.println(
                    "Error: ya existe un usuario con ese número de empleado."
            );
            return;
        }

        usuarios.put(
                usuario.getNumeroEmpleado(),
                usuario
        );
        System.out.println("Usuario registrado correctamente.");
    }

    // Los tecnicos tambien se identifican por su numero de empleado.
    public void registrarTecnico(TecnicoSoporte tecnico) {
        if (tecnico == null) {
            System.out.println("Error: el técnico no es válido.");
            return;
        }

        if (tecnicos.containsKey(tecnico.getNumeroEmpleado())) {
            System.out.println(
                    "Error: ya existe un técnico con ese número de empleado."
            );
            return;
        }

        tecnicos.put(
                tecnico.getNumeroEmpleado(),
                tecnico
        );
        System.out.println("Técnico registrado correctamente.");
    }

    public Ticket crearTicket(
            String tipo,
            String descripcion,
            String fechaCreacion,
            String prioridad,
            Usuario usuario
    ) {
        if (usuario == null) {
            System.out.println(
                    "Error: se necesita un usuario para crear el ticket."
            );
            return null;
        }

        // El ID se genera segun la cantidad de tickets que ya existen.
        int id = tickets.size() + 1;

        Ticket nuevoTicket = new Ticket(
                id,
                tipo,
                descripcion,
                fechaCreacion,
                "PENDIENTE",
                prioridad,
                usuario
        );

        tickets.add(nuevoTicket);
        System.out.println();
        System.out.println("====================================");
        System.out.println(" TICKET CREADO CORRECTAMENTE");
        System.out.println(" ID DEL TICKET: " + id);
        System.out.println(" Guarde este ID para futuras operaciones.");
        System.out.println("====================================");
        return nuevoTicket;
    }

    // La asignacion revisa que el ticket este libre y que el tecnico tenga capacidad.
    public void asignarTicket(
            Ticket ticket,
            TecnicoSoporte tecnico
    ) {
        if (ticket == null || tecnico == null) {
            System.out.println(
                    "Error: el ticket o técnico no es válido."
            );
            return;
        }

        if (ticket.getTecnicoAsignado() != null) {
            System.out.println(
                    "Error: el ticket ya tiene un técnico asignado."
            );
            return;
        }

        int ticketsActuales =
                contarTicketsAsignados(tecnico);

        if (tecnico.puedeRecibirTicket(ticketsActuales)) {
            ticket.asignarTecnico(tecnico);

            System.out.println(
                    "Ticket asignado correctamente a "
                            + tecnico.getNombre() + "."
            );
        }
    }

    public void mostrarTicket(Ticket ticket) {
        if (ticket == null) {
            System.out.println("Error: el ticket no es válido.");
            return;
        }

        ticket.mostrarInformacion();
    }

    // Solo contamos tickets que siguen activos, los solucionados ya no ocupan espacio.
    public int contarTicketsAsignados(TecnicoSoporte tecnico) {

        int cantidad = 0;

        for (Ticket ticket : tickets) {
            if (ticket.getTecnicoAsignado() == tecnico
                    && !ticket.estaSolucionado()) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public boolean hayUsuarios() {
        return !usuarios.isEmpty();
    }

    public boolean hayTecnicos() {
        return !tecnicos.isEmpty();
    }

    public boolean hayTickets() {
        return !tickets.isEmpty();
    }

    // Con HashMap podemos buscar directamente usando el numero de empleado.
    public Usuario buscarUsuario(int numeroEmpleado) {
        return usuarios.get(numeroEmpleado);
    }

    public TecnicoSoporte buscarTecnico(int numeroEmpleado) {
        return tecnicos.get(numeroEmpleado);
    }

    // Los tickets estan en una lista, por eso recorremos hasta encontrar el ID.
    public Ticket buscarTicket(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }

        return null;
    }

    // Recorre los tickets y muestra solamente los asignados al tecnico buscado.
    public void mostrarTicketsPorTecnico(int numeroEmpleado) {

        TecnicoSoporte tecnico = buscarTecnico(numeroEmpleado);

        if (tecnico == null) {
            System.out.println(
                    "Error: no existe un técnico con ese número de empleado."
            );
            return;
        }

        boolean encontroTickets = false;

        System.out.println(
                "\n--- TICKETS ASIGNADOS A "
                        + tecnico.getNombre().toUpperCase()
                        + " ---"
        );

        for (Ticket ticket : tickets) {
            if (ticket.getTecnicoAsignado() == tecnico) {
                ticket.mostrarInformacion();
                encontroTickets = true;
            }
        }

        if (!encontroTickets) {
            System.out.println(
                    "Este técnico no tiene tickets asignados."
            );
        }
    }

    // Recorre los tickets y muestra solamente los reportados por ese usuario.
    public void mostrarTicketsPorUsuario(int numeroEmpleado) {

        Usuario usuario = buscarUsuario(numeroEmpleado);

        if (usuario == null) {
            System.out.println(
                    "Error: no existe un usuario con ese número de empleado."
            );
            return;
        }

        boolean encontroTickets = false;

        System.out.println(
                "\n--- TICKETS REPORTADOS POR "
                        + usuario.getNombre().toUpperCase()
                        + " ---"
        );

        for (Ticket ticket : tickets) {
            if (ticket.getUsuario() == usuario) {
                ticket.mostrarInformacion();
                encontroTickets = true;
            }
        }

        if (!encontroTickets) {
            System.out.println(
                    "Este usuario no tiene tickets registrados."
            );
        }
    }

    public void mostrarTodosLosTickets() {

        for (Ticket ticket : tickets) {
            ticket.mostrarInformacion();
        }
    }

    // Este mismo metodo sirve para PENDIENTE, EN_PROCESO y SOLUCIONADO.
    public void mostrarTicketsPorEstado(String estado) {

        boolean encontroTickets = false;

        for (Ticket ticket : tickets) {

            if (ticket.getEstado().equalsIgnoreCase(estado)) {
                ticket.mostrarInformacion();
                encontroTickets = true;
            }
        }

        if (!encontroTickets) {
            System.out.println(
                    "No hay tickets con estado " + estado + "."
            );
        }
    }
}