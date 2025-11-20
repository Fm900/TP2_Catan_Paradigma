package edu.fiuba.algo3;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Exception.NoTieneRecursos;
import edu.fiuba.algo3.modelo.Tablero.Ladron;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LadronTest {

    Terreno terrenoActual;
    Terreno terrenoDestino;
    Ladron ladron;
    Vertice vertice1;
    Vertice vertice2;
    Jugador jugador1;
    Jugador jugador2;

    @BeforeEach
    public void setUp() {
        terrenoActual = mock(Terreno.class);
        terrenoDestino = mock(Terreno.class);
        ladron = new Ladron(terrenoActual);
        vertice1 = mock(Vertice.class);
        vertice2 = mock(Vertice.class);
        jugador1 = spy(new Jugador());
        jugador2 = spy(new Jugador());
    }

    @Test
    public void test01LadronSeMueveYLosTerrenosCambianEstado() {

        ladron.moverADestino(jugador1, terrenoDestino);

        // Verificar que el terreno actual cambia su estado productivo
        verify(terrenoActual, times(1)).cambiarEstado();

        // Verificar que el terreno destino también cambia su estado productivo
        verify(terrenoDestino, times(1)).cambiarEstado();
    }

    @Test
    public void test02LadronAlMoverseATerrnoVacioNoRoba() {
        ladron = spy(ladron);
        terrenoDestino.asignarVerticesAdyacentes(List.of(vertice1, vertice1, vertice1, vertice1, vertice1, vertice2));

        ladron.moverADestino(jugador1, terrenoDestino);

        verify(ladron, times(0)).robar(jugador1, jugador2);

    }

    @Test
    public void test02LadronAlMoverseATerrnoDondeSoloEstaElQueLoMueveNoRoba() {

        ladron = spy(ladron);

        vertice2.construirPoblado(jugador1);

        terrenoDestino.asignarVerticesAdyacentes(List.of(vertice1, vertice1, vertice1, vertice1, vertice1, vertice2));

        ladron.moverADestino(jugador1, terrenoDestino);

        verify(ladron, times(0)).robar(jugador1, jugador2);
    }

    @Test
    public void test03LadronAlMoverseRobaCartaAleatoriaAUnHabitanteDelTerreno() {

        ladron = spy(ladron);
        jugador2.agregarRecursos("madera",1);

        doReturn(new ArrayList<>(List.of(jugador1, jugador2))).when(terrenoDestino).obtenerHabitantes();
        doReturn(jugador2).when(jugador1).elegirVictima(anyList());
        ladron.moverADestino(jugador1, terrenoDestino);

        verify(ladron, times(1)).robar(jugador1, jugador2);
        assertDoesNotThrow(() -> jugador1.descartarRecursos("madera", 1));
        assertThrows(NoTieneRecursos.class, () -> jugador2.descartarRecursos("madera", 1));
    }
}
