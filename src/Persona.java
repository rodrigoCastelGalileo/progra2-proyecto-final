public class Persona {

    private String nombre;
    private int numeroEmpleado;
    private String email;

    public Persona(String nombre, int numeroEmpleado, String email) {
        setNombre(nombre);
        setNumeroEmpleado(numeroEmpleado);
        setEmail(email);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            System.out.println("Error: el nombre es obligatorio.");
            return;
        }

        this.nombre = nombre;
    }

    public int getNumeroEmpleado() {
        return numeroEmpleado;
    }

    private void setNumeroEmpleado(int numeroEmpleado) {
        if (numeroEmpleado <= 0) {
            System.out.println(
                    "Error: el número de empleado debe ser mayor que 0."
            );
            return;
        }

        this.numeroEmpleado = numeroEmpleado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank() || !email.contains("@")) {
            System.out.println("Error: el email no es válido.");
            return;
        }

        this.email = email;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("No. empleado: " + numeroEmpleado);
        System.out.println("Email: " + email);
    }
}