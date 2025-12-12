package edu.fiuba.algo3;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Constructores.GeneradorDeTerrenos;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;



public class TableroTest {

    @Test
    void Test01ColocarPobladoDelegaEnElVertice() {
        Vertice verticeMock = mock(Vertice.class);
        Jugador jugadorMock = mock(Jugador.class);
        GeneradorDeTerrenos generadorMock = mock(GeneradorDeTerrenos.class);
        when(generadorMock.generar()).thenReturn(List.of());

        Tablero tablero = new Tablero(generadorMock);

        tablero.colocarPoblado(jugadorMock, verticeMock);

        verify(verticeMock).construirPoblado(jugadorMock);
    }


    @Test
    void Test02ProducirParaLlamaProducirSiCorrespondeEnCadaTerreno() {
        Terreno t1 = mock(Terreno.class);
        Terreno t2 = mock(Terreno.class);

        GeneradorDeTerrenos generadorMock = mock(GeneradorDeTerrenos.class);
        when(generadorMock.generar()).thenReturn(List.of(t1, t2));

        Tablero tablero = new Tablero(generadorMock);

        tablero.producirPara(8);

        verify(t1).producirSiCorresponde(8);
        verify(t2).producirSiCorresponde(8);
    }


    @Test
    void Test03ObtenerTerrenosAdyFiltraTerrenosQueContienenAlVertice() {
        Vertice v = mock(Vertice.class);
        Terreno t1 = mock(Terreno.class);
        Terreno t2 = mock(Terreno.class);
        GeneradorDeTerrenos generadorMock = mock(GeneradorDeTerrenos.class);

        when(t1.tieneVertice(v)).thenReturn(true);
        when(t2.tieneVertice(v)).thenReturn(false);
        when(generadorMock.generar()).thenReturn(List.of(t1, t2));

        Tablero tablero = new Tablero(generadorMock);
        List<Terreno> res = tablero.obtenerTerrenosAdy(v);

        assertEquals(1, res.size());
    }

}
