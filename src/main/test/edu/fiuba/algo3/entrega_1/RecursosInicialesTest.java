package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Fase.Dados;
import edu.fiuba.algo3.modelo.Fase.PrimerTurno;
import edu.fiuba.algo3.modelo.Fase.SegundoTurno;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class RecursosInicialesTest {
    Jugador jugador1;
    Jugador jugador2;
    Jugador jugador3;
    Jugador jugador4;
    Tablero tablero;
    PrimerTurno primerTurno;
    SegundoTurno segundoTurno;
    Vertice vertice;

    @BeforeEach
    public void setUp() {
        jugador1 = mock(Jugador.class);
        jugador2 = mock(Jugador.class);
        jugador3 = mock(Jugador.class);
        jugador4 = mock(Jugador.class);
        tablero = mock(Tablero.class);
        primerTurno = spy(new PrimerTurno());
        segundoTurno = spy(new SegundoTurno());
        vertice = mock(Vertice.class);

    }
    @Test
    public void test01RecursosInicialesCorrespondientes() {
        Vertice verticeMock = mock(Vertice.class);

        doReturn(verticeMock).when(primerTurno).elegirVertice();
        primerTurno.construirPoblado(jugador1, tablero);

        verify(tablero).colocarPoblado(jugador1, verticeMock);
    }
    @Test
    public void test02SegundoPobladoRecibeRecursosPorTerrenosAdyacentes() {
        Vertice verticeMock = mock(Vertice.class);
        Terreno terreno1 = mock(Terreno.class);
        Terreno terreno2 = mock(Terreno.class);
        Terreno terreno3 = mock(Terreno.class);

        when(terreno1.obtenerRecurso()).thenReturn("Madera");
        when(terreno2.obtenerRecurso()).thenReturn("Ladrillo");
        when(terreno3.obtenerRecurso()).thenReturn("Grano");
        List<Terreno> terrenosAdyacentes = Arrays.asList(terreno1, terreno2, terreno3);
        when(tablero.obtenerTerrenosAdy(verticeMock)).thenReturn(terrenosAdyacentes);
        doReturn(verticeMock).when(segundoTurno).elegirVertice();
        segundoTurno.construirPoblado(jugador1, tablero);
        verify(tablero).colocarPoblado(jugador1, verticeMock);

        // Verificar que se reciben recursos por los terrenos adyacentes
        verify(jugador1).agregarRecursos("Madera", 1);
        verify(jugador1).agregarRecursos("Ladrillo", 1);
        verify(jugador1).agregarRecursos("Grano", 1);
    }
}
