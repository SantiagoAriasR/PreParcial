import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testMetodo {
    @Test
    public void validarMetodo(){
        Equipo equipo = new Equipo();
        Jugador jugador1 = equipo.contratarJugador("Juliana", 3, 2, Posicion.ARQ, LocalDate.now());
        Jugador jugador2 = equipo.contratarJugador("Laura", 5, 3, Posicion.DEF, LocalDate.now());
        Jugador jugador3 = equipo.contratarJugador("Sara", 1, 6, Posicion.DEL, LocalDate.now());
        Jugador jugador4 = equipo.contratarJugador("Arias", 0, 1, Posicion.ARQ, LocalDate.now());

        LinkedList<Jugador> echados = equipo.jugadoresDespedidos();
        int echadosSize = echados.size();
        LinkedList<Jugador> esperado = new LinkedList<>();
        int esperadoSize = esperado.size();
        esperado.add(jugador1);
        esperado.add(jugador2);
        esperado.add(jugador4);

        assertEquals(esperadoSize, echadosSize);
    }
}
