public class Ticket {

    private int id;
    private String tipo;
    private String descripcion;
    private String fechaCreacion;
    private String fechaResolucion;
    private String estado;
    private String resolucion;
    private String prioridad;

    private Usuario usuario;
    private TecnicoSoporte tecnicoAsignado;

    public Ticket(
            int id,
            String tipo,
            String descripcion,
            String fechaCreacion,
            String estado,
            String prioridad,
            Usuario usuario
    ) {
        setId(id);
        setTipo(tipo);
        setDescripcion(descripcion);
        setFechaCreacion(fechaCreacion);
        setEstado(estado);
        setPrioridad(prioridad);
        setUsuario(usuario);

        this.fechaResolucion = null;
        this.resolucion = null;
        this.tecnicoAsignado = null;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        if (id <= 0) {
            System.out.println(
                    "Error: el ID debe ser mayor que 0."
            );
            return;
        }

        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo == null ||
                (!tipo.equalsIgnoreCase("PROBLEMA")
                        && !tipo.equalsIgnoreCase("SOLICITUD"))) {

            System.out.println(
                    "Error: el tipo debe ser PROBLEMA o SOLICITUD."
            );
            return;
        }

        this.tipo = tipo.toUpperCase();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            System.out.println(
                    "Error: la descripción es obligatoria."
            );
            return;
        }

        this.descripcion = descripcion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    private void setFechaCreacion(String fechaCreacion) {
        if (fechaCreacion == null || fechaCreacion.isBlank()) {
            System.out.println(
                    "Error: la fecha de creación es obligatoria."
            );
            return;
        }

        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null ||
                (!estado.equalsIgnoreCase("PENDIENTE")
                        && !estado.equalsIgnoreCase("EN_PROCESO")
                        && !estado.equalsIgnoreCase("SOLUCIONADO"))) {

            System.out.println(
                    "Error: el estado no es válido."
            );
            return;
        }

        this.estado = estado.toUpperCase();
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        if (prioridad == null ||
                (!prioridad.equalsIgnoreCase("BAJA")
                        && !prioridad.equalsIgnoreCase("MEDIA")
                        && !prioridad.equalsIgnoreCase("ALTA"))) {

            System.out.println(
                    "Error: la prioridad debe ser BAJA, MEDIA o ALTA."
            );
            return;
        }

        this.prioridad = prioridad.toUpperCase();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    private void setUsuario(Usuario usuario) {
        if (usuario == null) {
            System.out.println(
                    "Error: el ticket debe tener un usuario."
            );
            return;
        }

        this.usuario = usuario;
    }

    public TecnicoSoporte getTecnicoAsignado() {
        return tecnicoAsignado;
    }

    public void asignarTecnico(TecnicoSoporte tecnico) {
        if (tecnico == null) {
            System.out.println(
                    "Error: debe seleccionar un técnico válido."
            );
            return;
        }

        this.tecnicoAsignado = tecnico;
    }

    public void cambiarEstado(String nuevoEstado) {
        setEstado(nuevoEstado);
    }

    public void mostrarInformacion() {
        System.out.println("------------------------------");
        System.out.println("Ticket #" + id);
        System.out.println("Tipo: " + tipo);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Fecha de creación: " + fechaCreacion);
        System.out.println("Prioridad: " + prioridad);
        System.out.println("Estado: " + estado);

        if (usuario != null) {
            System.out.println(
                    "Reportado por: " + usuario.getNombre()
            );
        }

        if (tecnicoAsignado != null) {
            System.out.println(
                    "Técnico asignado: "
                            + tecnicoAsignado.getNombre()
            );
        } else {
            System.out.println("Técnico asignado: Sin asignar");
        }

        if (resolucion != null) {
            System.out.println("Resolución: " + resolucion);
            System.out.println(
                    "Fecha de resolución: " + fechaResolucion
            );
        }

        System.out.println("------------------------------");
    }
}