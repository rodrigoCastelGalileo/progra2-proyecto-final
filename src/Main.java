import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        SistemaTickets sistema =
                new SistemaTickets("SISTEMA DE TICKETS DE SOPORTE");

        int opcion;

        sistema.mostrarBienvenida();

        do {
            mostrarMenu(sistema);
            opcion = pedirNumero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    registrarUsuario(sistema);
                    break;

                case 2:
                    registrarTecnico(sistema);
                    break;

                case 3:
                    crearTicket(sistema);
                    break;

                case 4:
                    asignarTecnico(sistema);
                    break;

                case 5:
                    cambiarEstado(sistema);
                    break;

                case 6:
                    registrarSolucion(sistema);
                    break;

                case 7:
                    consultarTicket(sistema);
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Error: opción no válida.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    //Funciones del main
    private static void mostrarMenu(SistemaTickets sistema) {

        System.out.println("\n========== MENÚ PRINCIPAL ==========");
        System.out.println("1. Registrar usuario");
        System.out.println("2. Registrar técnico");

        if (sistema.hayUsuarios()) {
            System.out.println("3. Crear ticket");
        } else {
            System.out.println("3. Crear ticket (BLOQUEADO)");
        }

        if (sistema.hayTickets() && sistema.hayTecnicos()) {
            System.out.println("4. Asignar técnico a ticket");
        } else {
            System.out.println(
                    "4. Asignar técnico a ticket (BLOQUEADO)"
            );
        }

        if (sistema.hayTickets()) {
            System.out.println("5. Cambiar estado de ticket");
            System.out.println("6. Registrar solución");
            System.out.println("7. Consultar ticket");
        } else {
            System.out.println(
                    "5. Cambiar estado de ticket (BLOQUEADO)"
            );
            System.out.println(
                    "6. Registrar solución (BLOQUEADO)"
            );
            System.out.println(
                    "7. Consultar ticket (BLOQUEADO)"
            );
        }

        System.out.println("0. Salir");
        System.out.println("====================================");
    }

    private static void registrarUsuario(SistemaTickets sistema) {

        System.out.println("\n--- REGISTRO DE USUARIO ---");

        String nombre = pedirTexto("Nombre: ");
        int numeroEmpleado =
                pedirNumeroPositivo("No. de empleado: ");
        String email = pedirEmail("Email: ");
        String nombreUsuario =
                pedirTexto("Nombre de usuario: ");
        String departamento =
                pedirTexto("Departamento: ");

        Usuario usuario = new Usuario(
                nombre,
                numeroEmpleado,
                email,
                nombreUsuario,
                departamento
        );

        sistema.registrarUsuario(usuario);
    }

    private static void registrarTecnico(SistemaTickets sistema) {

        System.out.println("\n--- REGISTRO DE TÉCNICO ---");

        String nombre = pedirTexto("Nombre: ");
        int numeroEmpleado =
                pedirNumeroPositivo("No. de empleado: ");
        String email = pedirEmail("Email: ");
        int limiteTickets =
                pedirNumeroPositivo("Límite de tickets: ");

        TecnicoSoporte tecnico = new TecnicoSoporte(
                nombre,
                numeroEmpleado,
                email,
                limiteTickets
        );

        sistema.registrarTecnico(tecnico);
    }

    private static void crearTicket(SistemaTickets sistema) {

        if (!sistema.hayUsuarios()) {
            System.out.println(
                    "No hay usuarios registrados todavía."
            );
            return;
        }

        System.out.println("\n--- CREAR TICKET ---");

        int numeroEmpleado =
                pedirNumeroPositivo(
                        "No. de empleado del usuario: "
                );

        Usuario usuario =
                sistema.buscarUsuario(numeroEmpleado);

        if (usuario == null) {
            System.out.println(
                    "No existe un usuario con ese número de empleado."
            );
            return;
        }

        String tipo = pedirTipo();
        String descripcion =
                pedirTexto("Descripción: ");
        String fechaCreacion =
                pedirTexto("Fecha de creación: ");
        String prioridad = pedirPrioridad();

        sistema.crearTicket(
                tipo,
                descripcion,
                fechaCreacion,
                prioridad,
                usuario
        );
    }

    private static void cambiarEstado(SistemaTickets sistema) {

        if (!sistema.hayTickets()) {
            System.out.println(
                    "No hay tickets registrados todavía."
            );
            return;
        }

        System.out.println("\n--- CAMBIAR ESTADO DE TICKET ---");

        int id = pedirNumeroPositivo("ID del ticket: ");

        Ticket ticket = sistema.buscarTicket(id);

        if (ticket == null) {
            System.out.println(
                    "No existe un ticket con ese ID."
            );
            return;
        }

        String nuevoEstado = pedirEstado();

        if (ticket.cambiarEstado(nuevoEstado)) {
            System.out.println(
                    "Estado del ticket actualizado correctamente."
            );
        }
    }

    private static void asignarTecnico(SistemaTickets sistema) {

        if (!sistema.hayTickets()) {
            System.out.println(
                    "No hay tickets registrados todavía."
            );
            return;
        }

        if (!sistema.hayTecnicos()) {
            System.out.println(
                    "No hay técnicos registrados todavía."
            );
            return;
        }

        System.out.println("\n--- ASIGNAR TÉCNICO ---");

        int id = pedirNumeroPositivo("ID del ticket: ");

        Ticket ticket = sistema.buscarTicket(id);

        if (ticket == null) {
            System.out.println(
                    "No existe un ticket con ese ID."
            );
            return;
        }

        int numeroEmpleado =
                pedirNumeroPositivo(
                        "No. de empleado del técnico: "
                );

        TecnicoSoporte tecnico =
                sistema.buscarTecnico(numeroEmpleado);

        if (tecnico == null) {
            System.out.println(
                    "No existe un técnico con ese número de empleado."
            );
            return;
        }

        sistema.asignarTicket(ticket, tecnico);
    }

    private static void registrarSolucion(SistemaTickets sistema) {

        if (!sistema.hayTickets()) {
            System.out.println(
                    "No hay tickets registrados todavía."
            );
            return;
        }

        System.out.println("\n--- REGISTRAR SOLUCIÓN ---");

        int id = pedirNumeroPositivo("ID del ticket: ");

        Ticket ticket = sistema.buscarTicket(id);

        if (ticket == null) {
            System.out.println(
                    "No existe un ticket con ese ID."
            );
            return;
        }

        if (ticket.estaSolucionado()) {
            System.out.println(
                    "Este ticket ya fue solucionado."
            );
            return;
        }

        if (ticket.getTecnicoAsignado() == null) {
            System.out.println(
                    "El ticket debe tener un técnico asignado antes de solucionarlo."
            );
            return;
        }

        String resolucion =
                pedirTexto("Resolución: ");

        String fechaResolucion =
                pedirTexto("Fecha de resolución: ");

        ticket.registrarSolucion(
                resolucion,
                fechaResolucion
        );

        System.out.println(
                "Ticket #" + ticket.getId()
                        + " solucionado correctamente."
        );
    }

    private static void consultarTicket(SistemaTickets sistema) {

        if (!sistema.hayTickets()) {
            System.out.println(
                    "No hay tickets registrados todavía."
            );
            return;
        }

        int id = pedirNumeroPositivo("ID del ticket: ");

        Ticket ticket = sistema.buscarTicket(id);

        if (ticket == null) {
            System.out.println(
                    "No existe un ticket con ese ID."
            );
            return;
        }

        sistema.mostrarTicket(ticket);
    }

    //Helpers
    private static String pedirTexto(String mensaje) {
        String valor;

        do {
            System.out.print(mensaje);
            valor = scanner.nextLine();

            if (valor.isBlank()) {
                System.out.println("Error: este campo es obligatorio.");
            }

        } while (valor.isBlank());

        return valor;
    }

    private static int pedirNumero(String mensaje) {
        int numero = -1;

        do {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();

            try {
                numero = Integer.parseInt(entrada);

                if (numero < 0) {
                    System.out.println(
                            "Error: ingrese un número válido."
                    );
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Error: debe ingresar un número."
                );
            }

        } while (numero < 0);

        return numero;
    }

    private static int pedirNumeroPositivo(String mensaje) {
        int numero = 0;

        do {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();

            try {
                numero = Integer.parseInt(entrada);

                if (numero <= 0) {
                    System.out.println(
                            "Error: el número debe ser mayor que 0."
                    );
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Error: debe ingresar un número."
                );
            }

        } while (numero <= 0);

        return numero;
    }

    private static String pedirEmail(String mensaje) {
        String email;

        do {
            System.out.print(mensaje);
            email = scanner.nextLine();

            if (email.isBlank() || !email.contains("@")) {
                System.out.println("Error: ingrese un email válido.");
            }

        } while (email.isBlank() || !email.contains("@"));

        return email;
    }

    private static String pedirTipo() {
        String tipo;

        do {
            System.out.print("Tipo (PROBLEMA/SOLICITUD): ");
            tipo = scanner.nextLine();

            if (!tipo.equalsIgnoreCase("PROBLEMA")
                    && !tipo.equalsIgnoreCase("SOLICITUD")) {

                System.out.println(
                        "Error: ingrese PROBLEMA o SOLICITUD."
                );
            }

        } while (!tipo.equalsIgnoreCase("PROBLEMA")
                && !tipo.equalsIgnoreCase("SOLICITUD"));

        return tipo;
    }

    private static String pedirPrioridad() {
        String prioridad;

        do {
            System.out.print("Prioridad (BAJA/MEDIA/ALTA): ");
            prioridad = scanner.nextLine();

            if (!prioridad.equalsIgnoreCase("BAJA")
                    && !prioridad.equalsIgnoreCase("MEDIA")
                    && !prioridad.equalsIgnoreCase("ALTA")) {

                System.out.println(
                        "Error: ingrese BAJA, MEDIA o ALTA."
                );
            }

        } while (!prioridad.equalsIgnoreCase("BAJA")
                && !prioridad.equalsIgnoreCase("MEDIA")
                && !prioridad.equalsIgnoreCase("ALTA"));

        return prioridad;
    }

    private static String pedirEstado() {
        String estado;

        do {
            System.out.print(
                    "Estado (PENDIENTE/EN_PROCESO): "
            );

            estado = scanner.nextLine();

            if (!estado.equalsIgnoreCase("PENDIENTE")
                    && !estado.equalsIgnoreCase("EN_PROCESO")) {

                System.out.println(
                        "Error: ingrese PENDIENTE o EN_PROCESO."
                );
            }

        } while (!estado.equalsIgnoreCase("PENDIENTE")
                && !estado.equalsIgnoreCase("EN_PROCESO"));

        return estado;
    }
}