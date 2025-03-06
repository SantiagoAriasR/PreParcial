import java.time.LocalDate;

public class Jugador {
    // Atributos
    private String nombre;
    private float salario;
    private int numCamiseta;
    private float calificacion;
    private Posicion posJuego;
    private LocalDate fechaContratacion;

    // Constructor
    public Jugador(String nombre, float salario, int numCamiseta, float calificacion, Posicion posJuego, LocalDate fechaContratacion) {
        this.nombre = nombre;
        this.salario = salario;
        this.numCamiseta = numCamiseta;
        this.calificacion = calificacion;
        this.posJuego = posJuego;
        this.fechaContratacion = fechaContratacion;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public int getNumCamiseta() {
        return numCamiseta;
    }

    public void setNumCamiseta(int numCamiseta) {
        this.numCamiseta = numCamiseta;
    }

    public Posicion getPosJuego() {
        return posJuego;
    }

    public void setPosJuego(Posicion posJuego) {
        this.posJuego = posJuego;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }
}
