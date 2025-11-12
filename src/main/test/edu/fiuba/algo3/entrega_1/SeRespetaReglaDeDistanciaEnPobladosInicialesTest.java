package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class JugadorStub extends Jugador {
    int pobladosConstruidos = 0;

    @Override
    public void consumirRecursosParaPoblado() {
        pobladosConstruidos++;
    }
}


public class SeRespetaReglaDeDistanciaEnPobladosInicialesTest {

    private Vertice v0, v1, v2, v3;
    private JugadorStub jugador;

    @BeforeEach
    void SetUp() {
        v0 = new Vertice();
        v1 = new Vertice();
        v2 = new Vertice();

        // creo un mini grafo con v0 conectado todos los otros
        v0.conectarConVertice(v1);
        v0.conectarConVertice(v2);

        jugador = new JugadorStub();
    }

    @Test
    void NoSePuedeConstruirPobladoEnVerticeOcupado() throws Exception {
        int pobladosEsperados = 1;

        v0.construirPoblado(jugador);
        v0.construirPoblado(jugador);

        int pobladosConstruidos = jugador.pobladosConstruidos;

        assertEquals(pobladosEsperados,pobladosConstruidos);

    }

    @Test
    void NoSePuedeConstruirPobladoEnVerticeAdyacenteAOtroPoblado() throws Exception {
        int pobladosEsperados = 1;

        // v0--v1
        v0.construirPoblado(jugador); // construyo en v0
        v1.construirPoblado(jugador); // v1 vecino ocupado -> NO deberia construir

        int pobladosConstridos = jugador.pobladosConstruidos;

        assertEquals(pobladosEsperados,pobladosConstridos);
    }

    @Test
    void SePuedeConstruirSiElVerticeEstaADosDeDistancia() throws Exception {
        int pobladosEsperados = 2;

        // v1--v0--v2
        v1.construirPoblado(jugador); // construyo en v1
        v2.construirPoblado(jugador); // construyo en v2 (distancia 2 de v1)
        int pobladosConstruir = jugador.pobladosConstruidos;

        assertEquals(pobladosEsperados,pobladosConstruir);
    }

}
