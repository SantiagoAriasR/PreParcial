import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedList;

public class Equipo {
    // Atributos
    private LinkedList<Jugador> listaJugadores;
    private float salarioTotal;

    // Constructor
    public Equipo() {
        this.listaJugadores = new LinkedList<>();
        this.salarioTotal = 200;
    }

    // Getter y Setters
    public LinkedList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(LinkedList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public float getSalarioTotal() {
        return salarioTotal;
    }

    public void setSalarioTotal(float salarioTotal) {
        this.salarioTotal = salarioTotal;
    }

    // Funciones

    // Funcion requerimineto 1
    public LinkedList<Jugador> obtenerListaJugadoresDespedidos() {
        LinkedList<Jugador> jugadoresEliminados = new LinkedList<>();
        for(Jugador jugador : listaJugadores){
            if(jugador.getCalificacion()<=3){
                jugadoresEliminados.add(jugador);
            }
        }

        listaJugadores.removeAll(jugadoresEliminados);

        return jugadoresEliminados;
    }

    // ----------------------------------------------------- Funcion 2 --------------------------------------------------------------
    // Funcion para contar jugadores posicion
    public int contarJugadoresPosicion(Posicion posicion){
        int contadorposicion = 0;
        for(Jugador jugador : listaJugadores){
            if(jugador.getPosJuego().equals(posicion)){
                contadorposicion++;
            }
        }
        return contadorposicion;
    }

    // Funcion retornar jugador menor calificacion
    public Jugador jugadorMenorCalificacion(Posicion posicion){
        float calificacionMenor = Float.MAX_VALUE;
        Jugador jugadorMenorCalificacion = null;
        for(Jugador jugador : listaJugadores){
            if(jugador.getPosJuego().equals(posicion) && jugador.getCalificacion()<calificacionMenor){
                calificacionMenor = jugador.getCalificacion();
                jugadorMenorCalificacion = jugador;
            }
        }
        System.out.println(calificacionMenor);
        return jugadorMenorCalificacion;
    }

    // Validar salario jugador con salario plantilla
    public boolean salarioDisponible(Jugador jugador){
        boolean esDisponible = false;
        if (jugador.getSalario()<salarioTotal){
            esDisponible = true;
        }
        return esDisponible;
    }

    // Asignar numero camiseta
    public int numeroCamisetaAleatorio(){
        boolean bandera = false;
        int camiseta = 0;
        while (!bandera){
            camiseta = (int) (Math.random() * 40) + 1;
            if(listaJugadores.isEmpty()) bandera = true;
            for (Jugador jugador : listaJugadores){
                if (jugador.getNumCamiseta()==camiseta){
                    break;
                } else{
                    bandera = true;
                }
            }

        }
        return camiseta;
    }


    // Contratar jugador
    public Jugador contratarJugador(String nombre, float salario, float calificacion, Posicion posJuego, LocalDate fechaContratacion) throws Exception{

        if(nombre.isEmpty()) throw new Exception("El nombre es obligatorio");

        Jugador jugador  = new Jugador(nombre,salario,numeroCamisetaAleatorio(),calificacion,posJuego,fechaContratacion);
        if(salarioDisponible(jugador)) {
            if (contarJugadoresPosicion(jugador.getPosJuego()) < 3) {
                listaJugadores.add(jugador);
            } else {
                Jugador peor = jugadorMenorCalificacion(jugador.getPosJuego());
                if (peor.getCalificacion() < jugador.getCalificacion() ) {
                    listaJugadores.add(jugador);
                    listaJugadores.remove(peor);
                }
            }
        }else {
            throw new Exception("No hay dinerp sufiente para contratar jugadores");
        }
        return jugador;
    }

    // ----------------------------------------Función 3-----------------------------------------
    // Funcion calcular porcentaje salarios
    public PorcentajeSalario calcularPorcentajeSalarios(){
        // Inicializamos el objeto
        PorcentajeSalario porcentajeSalario = new PorcentajeSalario();

        // calculamos salarios totales
        float salarioTotal = calcularTotalSalarios();
        float salarioArq = calcularSalarioTotalPosicion(Posicion.ARQ);
        float salarioDef = calcularSalarioTotalPosicion(Posicion.DEF);
        float salarioMedioCam = calcularSalarioTotalPosicion(Posicion.MED);
        float salarioDel = calcularSalarioTotalPosicion(Posicion.DEL);

        // Calculamos porcentajes
        porcentajeSalario.setPorcentajeSalarioDel(salarioDel/salarioTotal*100);
        porcentajeSalario.setPorcentajeSalarioArq(salarioArq/salarioTotal*100);
        porcentajeSalario.setPorcentajeSalarioMedioCam(salarioMedioCam/salarioTotal*100);
        porcentajeSalario.setPorcentajeSalarioDef(salarioDef/salarioTotal*100);

        return porcentajeSalario;
    }

    // Total salarios
    public float calcularTotalSalarios(){
        float salarioTotal = 0;
        for (Jugador jugador : listaJugadores){
            salarioTotal += jugador.getSalario();
        }
        return salarioTotal;
    }

    // Funcion para total salario dependiendo posicion
    public float calcularSalarioTotalPosicion(Posicion posicion){
        float salarioTotal = 0;
        for (Jugador jugador : listaJugadores){
            if(jugador.getPosJuego().equals(posicion)){
                salarioTotal += jugador.getSalario();
            }
        }
        return salarioTotal;
    }

    // ------------------------------------------------- Funcion 4 --------------------------------------------------------
    // Calcular tiempo jugador
    public int calcularTiempoContratacion(Jugador jugador){
        Period diferencia = Period.between(jugador.getFechaContratacion(), LocalDate.now());
        return diferencia.getYears();
    }

    // Rebajar Sueldos jugadores mayor 2 años
    public void rebajarSueldoJugadores(){
        float nuevoSueldo = 0;
        for (Jugador jugador : listaJugadores){
            if(calcularTiempoContratacion(jugador) > 2 && jugador.getCalificacion() < 5 && jugador.getCalificacion() > 3){
                salarioTotal += jugador.getSalario();  // salario total 100, 110
                nuevoSueldo = (jugador.getSalario() / 100) * 75; // calcular nuevo sueldo
                jugador.setSalario(nuevoSueldo);
                salarioTotal -= nuevoSueldo;  // 7.5  102.5
            }
        }
    }
}
