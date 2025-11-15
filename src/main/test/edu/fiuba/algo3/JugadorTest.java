package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Excepciones.NoTieneRecursos;
import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JugadorTest {

    @Test
    void Test01SeAsignanCorrectamenteLosRecursosAlJugador() {
        Jugador jugador = new Jugador();
        int cantidad_esperada = 4;

        jugador.agregarRecursos("madera",1);
        jugador.agregarRecursos("ladrillo",1);
        jugador.agregarRecursos("lana",1);
        jugador.agregarRecursos("grano",1);
        int cantidad_recursos = jugador.getCantidadRecursosTotales();

        assertEquals(cantidad_esperada, cantidad_recursos);
    }

    @Test
    void Test02AlConstruirUnPobladoSecConsumenCorrectamenteLosRecursos(){
        Jugador jugador = new Jugador();
        int cantidad_esperada = 0;

        jugador.agregarRecursos("madera",1);
        jugador.agregarRecursos("ladrillo",1);
        jugador.agregarRecursos("lana",1);
        jugador.agregarRecursos("grano",1);

        jugador.consumirRecursosParaPoblado();
        int cantidad_recursos = jugador.getCantidadRecursosTotales();

        assertEquals(cantidad_esperada,cantidad_recursos);
    }

    @Test
    void Test02SiElJugadorNoTieneRecursosSuficientesParaPobladoLanzaExcepcion() {
         Jugador jugador = new Jugador();
        jugador.agregarRecursos("madera", 1);
        jugador.agregarRecursos("ladrillo", 1);
        jugador.agregarRecursos("lana", 1);
        //le falta grano

        assertThrows(NoTieneRecursos.class, jugador::consumirRecursosParaPoblado);
    }
}
