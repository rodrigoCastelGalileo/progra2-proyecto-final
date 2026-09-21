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
        setNombreUsuario(nombreUsuario);
        setDepartamento(departamento);
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        if (nombreUsuario == null || nombreUsuario.isBlank()) {
            System.out.println(
                    "Error: el nombre de usuario es obligatorio."
            );
            return;
        }

        this.nombreUsuario = nombreUsuario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        if (departamento == null || departamento.isBlank()) {
            System.out.println(
                    "Error: el departamento es obligatorio."
            );
            return;
        }

        this.departamento = departamento;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Usuario: " + nombreUsuario);
        System.out.println("Departamento: " + departamento);
    }
}