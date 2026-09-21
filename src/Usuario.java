public class Usuario extends Persona {

    private String nombreUsuario;
    private String departamento;

    public Usuario(
            String nombre,
            int numeroEmpleado,
            String email,
            String nombreUsuario,
            String departamento
    ) {
        super(nombre, numeroEmpleado, email);
        this.nombreUsuario = nombreUsuario;
        this.departamento = departamento;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Usuario: " + nombreUsuario);
        System.out.println("Departamento: " + departamento);
    }
}