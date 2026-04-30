public class Tarea {

    private String nombre, descripcion;
    private int duracion;

    public Tarea(String descripcion, int duracion, String nombre) {
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
