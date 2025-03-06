import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class testMetodo {

    @Test
    public void contratarJuagadorTest(){
        Equipo equipo = new Equipo();
        assertThrows(Exception.class, () -> {
            equipo.contratarJugador("Juliana", 3000, 2, Posicion.ARQ, LocalDate.now());
        } );
    }

    @Test
    public void contratarJuagador2Test(){
        Equipo equipo = new Equipo();
        assertThrows(Exception.class, () -> {
            equipo.contratarJugador("Juliana", 3000, 2, Posicion.ARQ, LocalDate.now());
        } );
    }

    @Test
    public void despedirJugadoresTest() throws Exception{
        Equipo equipo = new Equipo();

        equipo.contratarJugador("Juliana", 2, 2, Posicion.ARQ, LocalDate.now());
        equipo.contratarJugador("Juliana", 30, 5, Posicion.DEF, LocalDate.now());
        equipo.contratarJugador("Juliana", 30, 2, Posicion.ARQ, LocalDate.now());

        List<Jugador> jugadorList = equipo.obtenerListaJugadoresDespedidos();
        assertEquals(2, jugadorList.size());
    }

    @Test
    public void calcularPorcentajeJugadoresTest() throws Exception{
        Equipo equipo = new Equipo();

        equipo.contratarJugador("Juliana", 2, 2, Posicion.ARQ, LocalDate.now());
        equipo.contratarJugador("Juliana", 30, 5, Posicion.DEF, LocalDate.now());
        equipo.contratarJugador("Juliana", 30, 2, Posicion.ARQ, LocalDate.now());

        PorcentajeSalario porcentajeSalario = equipo.calcularPorcentajeSalarios();
        assertEquals(0, porcentajeSalario.getPorcentajeSalarioMedioCam());
        assertEquals(0, porcentajeSalario.getPorcentajeSalarioDel());
        assertEquals(30.0f/62.0f * 100, porcentajeSalario.getPorcentajeSalarioDef());
        assertEquals(32.0f/62.0f * 100, porcentajeSalario.getPorcentajeSalarioArq());
    }

}
