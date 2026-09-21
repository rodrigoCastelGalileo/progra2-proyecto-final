public class Persona {

    private String nombre;
    private int numeroEmpleado;
    private String email;

    public Persona(String nombre, int numeroEmpleado, String email) {
        this.nombre = nombre;
        this.numeroEmpleado = numeroEmpleado;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("No. empleado: " + numeroEmpleado);
        System.out.println("Email: " + email);
    }
}