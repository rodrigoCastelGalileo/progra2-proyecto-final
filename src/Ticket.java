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
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.usuario = usuario;

        this.fechaResolucion = null;
        this.resolucion = null;
        this.tecnicoAsignado = null;
    }

    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public TecnicoSoporte getTecnicoAsignado() {
        return tecnicoAsignado;
    }

    public void asignarTecnico(TecnicoSoporte tecnico) {
        this.tecnicoAsignado = tecnico;
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void mostrarInformacion() {
        System.out.println("------------------------------");
        System.out.println("Ticket #" + id);
        System.out.println("Tipo: " + tipo);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Fecha de creación: " + fechaCreacion);
        System.out.println("Prioridad: " + prioridad);
        System.out.println("Estado: " + estado);

        System.out.println("Reportado por: " + usuario.getNombre());

        if (tecnicoAsignado != null) {
            System.out.println(
                    "Técnico asignado: " + tecnicoAsignado.getNombre()
            );
        } else {
            System.out.println("Técnico asignado: Sin asignar");
        }

        if (resolucion != null) {
            System.out.println("Resolución: " + resolucion);
            System.out.println("Fecha de resolución: " + fechaResolucion);
        }

        System.out.println("------------------------------");
    }
}