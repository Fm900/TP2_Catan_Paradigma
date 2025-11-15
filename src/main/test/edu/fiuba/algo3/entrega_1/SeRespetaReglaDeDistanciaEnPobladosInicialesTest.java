package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.Excepciones.ReglaDeDistanciaNoValida;
import edu.fiuba.algo3.modelo.Excepciones.VerticeOcupadoNoPuedeConstruir;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class SeRespetaReglaDeDistanciaEnPobladosInicialesTest {

    private Vertice v0, v1, v2;

    @BeforeEach
    void SetUp() {
        v0 = new Vertice();
        v1 = new Vertice();
        v2 = new Vertice();

        // creo un mini grafo con v0 conectado todos los otros
        v0.conectarConVertice(v1);
        v0.conectarConVertice(v2);
    }

    @Test
    void Test01NoSePuedeConstruirPobladoEnVerticeOcupado() {
        Jugador jugador = mock(Jugador.class);

        v0.construirPoblado(jugador);

        assertThrows(VerticeOcupadoNoPuedeConstruir.class, () -> v0.construirPoblado(jugador));
    }

    @Test
    void Test02NoSePuedeConstruirPobladoEnVerticeAdyacenteAOtroPoblado() {
        Jugador jugador = mock(Jugador.class);

        v0.construirPoblado(jugador); // construyo en v0

        assertThrows(ReglaDeDistanciaNoValida.class, () -> v1.construirPoblado(jugador));
    }

    @Test
    void Test03SePuedeConstruirSiElVerticeEstaADosDeDistancia() {
        Jugador jugador = mock(Jugador.class);

        // v1--v0--v2
        v1.construirPoblado(jugador); // construyo en v1
        v2.construirPoblado(jugador); // construyo en v2 (distancia 2 de v1)

        verify(jugador, times(2)).consumirRecursosParaPoblado(); //deberia haber podido construir ambos
    }

}
