import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        SistemaTickets sistema =
                new SistemaTickets("SISTEMA DE TICKETS DE SOPORTE");

        sistema.mostrarBienvenida();

        System.out.println("\n--- REGISTRO DE USUARIO ---");

        String nombre = pedirTexto("Nombre: ");
        int numeroEmpleado = pedirNumeroPositivo("No. de empleado: ");
        String email = pedirEmail("Email: ");
        String nombreUsuario = pedirTexto("Nombre de usuario: ");
        String departamento = pedirTexto("Departamento: ");

        Usuario usuario = new Usuario(
                nombre,
                numeroEmpleado,
                email,
                nombreUsuario,
                departamento
        );

        System.out.println("\n--- REGISTRO DE TÉCNICO ---");

        String nombreTecnico = pedirTexto("Nombre: ");
        int numeroTecnico = pedirNumeroPositivo("No. de empleado: ");
        String emailTecnico = pedirEmail("Email: ");
        int limiteTickets = pedirNumeroPositivo("Límite de tickets: ");

        TecnicoSoporte tecnico = new TecnicoSoporte(
                nombreTecnico,
                numeroTecnico,
                emailTecnico,
                limiteTickets
        );

        System.out.println("\n--- CREAR TICKET ---");

        String tipo = pedirTipo();
        String descripcion = pedirTexto("Descripción: ");
        String fechaCreacion = pedirTexto("Fecha de creación: ");
        String prioridad = pedirPrioridad();

        Ticket ticket = sistema.crearTicket(
                1,
                tipo,
                descripcion,
                fechaCreacion,
                "PENDIENTE",
                prioridad,
                usuario
        );

        sistema.asignarTicket(ticket, tecnico);

        System.out.println("\n--- TICKET REGISTRADO ---");

        sistema.mostrarTicket(ticket);

        scanner.close();
    }

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
}