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

        if (estaSolucionado()) {
            System.out.println(
                    "Error: un ticket solucionado no puede modificarse."
            );
            return;
        }

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

        if (estaSolucionado()) {
            System.out.println(
                    "Error: un ticket solucionado no puede modificarse."
            );
            return;
        }

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

    private void setEstado(String estado) {
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

        if (estaSolucionado()) {
            System.out.println(
                    "Error: un ticket solucionado no puede modificarse."
            );
            return;
        }

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

    public boolean cambiarEstado(String nuevoEstado) {

        if (estaSolucionado()) {
            System.out.println(
                    "Error: un ticket solucionado no puede cambiar de estado."
            );
            return false;
        }

        if (nuevoEstado == null
                || (!nuevoEstado.equalsIgnoreCase("PENDIENTE")
                && !nuevoEstado.equalsIgnoreCase("EN_PROCESO"))) {

            System.out.println(
                    "Error: el estado no es válido."
            );
            return false;
        }

        if (estado.equalsIgnoreCase(nuevoEstado)) {
            System.out.println(
                    "Error: el ticket ya se encuentra en ese estado."
            );
            return false;
        }

        if (nuevoEstado.equalsIgnoreCase("EN_PROCESO")
                && tecnicoAsignado == null) {

            System.out.println(
                    "Error: debe asignar un técnico antes de cambiar el ticket a EN_PROCESO."
            );
            return false;
        }

        setEstado(nuevoEstado);
        return true;
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

    public boolean estaSolucionado() {
        return estado != null && estado.equalsIgnoreCase("SOLUCIONADO");
    }

    public void registrarSolucion(
            String resolucion,
            String fechaResolucion
    ) {

        if (estaSolucionado()) {
            System.out.println(
                    "Error: el ticket ya está solucionado."
            );
            return;
        }

        if (tecnicoAsignado == null) {
            System.out.println(
                    "Error: el ticket debe tener un técnico asignado."
            );
            return;
        }

        if (resolucion == null || resolucion.isBlank()) {
            System.out.println(
                    "Error: la resolución es obligatoria."
            );
            return;
        }

        if (fechaResolucion == null || fechaResolucion.isBlank()) {
            System.out.println(
                    "Error: la fecha de resolución es obligatoria."
            );
            return;
        }

        this.resolucion = resolucion;
        this.fechaResolucion = fechaResolucion;

        setEstado("SOLUCIONADO");
    }
}