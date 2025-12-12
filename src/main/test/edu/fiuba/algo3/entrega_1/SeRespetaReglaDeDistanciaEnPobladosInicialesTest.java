package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void NoSePuedeConstruirPobladoEnVerticeOcupado() {
        Jugador jugador = mock(Jugador.class);

        v0.construirPoblado(jugador);
        v0.construirPoblado(jugador);

        verify(jugador,times(1)).consumirRecursosParaPoblado(); //solo se cobra un poblado
    }

    @Test
    void NoSePuedeConstruirPobladoEnVerticeAdyacenteAOtroPoblado() {
        Jugador jugador = mock(Jugador.class);

        // v0--v1
        v0.construirPoblado(jugador); // construyo en v0
        v1.construirPoblado(jugador); // v1 vecino ocupado -> NO deberia construir


        verify(jugador, times(1)).consumirRecursosParaPoblado(); //solo se cobra un poblado
    }

    @Test
    void SePuedeConstruirSiElVerticeEstaADosDeDistancia() {
        Jugador jugador = mock(Jugador.class);

        // v1--v0--v2
        v1.construirPoblado(jugador); // construyo en v1
        v2.construirPoblado(jugador); // construyo en v2 (distancia 2 de v1)

        verify(jugador, times(2)).consumirRecursosParaPoblado(); //deberia haber podido construir ambos
    }

}
