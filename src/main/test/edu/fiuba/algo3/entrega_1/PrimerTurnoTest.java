package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Fase.PrimerTurno;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;


public class PrimerTurnoTest {

    Jugador jugador1;
    Jugador jugador2;
    Jugador jugador3;
    Jugador jugador4;
    Tablero tablero;
    PrimerTurno primerTurno;
    Vertice vertice;

    @BeforeEach
    public void setUp(){
        jugador1 = mock(Jugador.class);
        jugador2 = mock(Jugador.class);
        jugador3 = mock(Jugador.class);
        jugador4 = mock(Jugador.class);
        tablero = mock(Tablero.class);
        primerTurno = spy(new PrimerTurno());
        vertice = mock(Vertice.class);
    }

    @Test
    public void test01EnElPrimerTurnoTodosLosJugadoresConstruyenUnPoblado(){
        primerTurno.iniciarFase(List.of(jugador1,jugador2,jugador3,jugador4),tablero);
        verify(primerTurno, times(4)).construirPoblado(any(Jugador.class), any(Tablero.class));
    }

    @Test
    public void test02ConstruirPobladoAgregaRecursosAlJugador() {
        primerTurno.construirPoblado(jugador1, tablero);

        verify(jugador1).agregarRecursos("madera", 1);
        verify(jugador1).agregarRecursos("ladrillo", 1);
        verify(jugador1).agregarRecursos("lana", 1);
        verify(jugador1).agregarRecursos("grano", 1);
    }

    @Test
    public void test03ConstruirPobladoColocaUnPobladoEnElTablero() {
        Vertice verticeMock = mock(Vertice.class);

        doReturn(verticeMock).when(primerTurno).elegirVertice();

        primerTurno.construirPoblado(jugador1, tablero);

        verify(tablero).colocarPoblado(jugador1, verticeMock);
    }



}
