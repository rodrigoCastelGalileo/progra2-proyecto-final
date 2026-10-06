import java.util.ArrayList;

public class SistemaTickets {

    private String nombreSistema;

    private ArrayList<Usuario> usuarios;
    private ArrayList<TecnicoSoporte> tecnicos;
    private ArrayList<Ticket> tickets;

    public SistemaTickets(String nombreSistema) {
        this.nombreSistema = nombreSistema;

        usuarios = new ArrayList<>();
        tecnicos = new ArrayList<>();
        tickets = new ArrayList<>();
    }

    public void mostrarBienvenida() {
        System.out.println("====================================");
        System.out.println(" " + nombreSistema);
        System.out.println("====================================");
    }

    public void registrarUsuario(Usuario usuario) {
        if (usuario == null) {
            System.out.println("Error: el usuario no es válido.");
            return;
        }

        if (buscarUsuario(usuario.getNumeroEmpleado()) != null) {
            System.out.println(
                    "Error: ya existe un usuario con ese número de empleado."
            );
            return;
        }

        usuarios.add(usuario);
        System.out.println("Usuario registrado correctamente.");
    }

    public void registrarTecnico(TecnicoSoporte tecnico) {
        if (tecnico == null) {
            System.out.println("Error: el técnico no es válido.");
            return;
        }

        if (buscarTecnico(tecnico.getNumeroEmpleado()) != null) {
            System.out.println(
                    "Error: ya existe un técnico con ese número de empleado."
            );
            return;
        }

        tecnicos.add(tecnico);
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
        System.out.println("*************************");
        System.out.println(
                "Ticket #" + id + " creado correctamente. (Guarde este numero, le puede servir)"
        );
        System.out.println("*************************");
        return nuevoTicket;
    }

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

        ticket.asignarTecnico(tecnico);
    }

    public void mostrarTicket(Ticket ticket) {
        if (ticket == null) {
            System.out.println("Error: el ticket no es válido.");
            return;
        }

        ticket.mostrarInformacion();
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

    public Usuario buscarUsuario(int numeroEmpleado) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNumeroEmpleado() == numeroEmpleado) {
                return usuario;
            }
        }

        return null;
    }

    public TecnicoSoporte buscarTecnico(int numeroEmpleado) {
        for (TecnicoSoporte tecnico : tecnicos) {
            if (tecnico.getNumeroEmpleado() == numeroEmpleado) {
                return tecnico;
            }
        }

        return null;
    }

    public Ticket buscarTicket(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }

        return null;
    }
}