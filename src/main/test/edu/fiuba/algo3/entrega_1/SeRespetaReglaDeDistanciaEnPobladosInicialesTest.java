package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.Exception.ReglaDeDistanciaNoValida;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SeRespetaReglaDeDistanciaEnPobladosInicialesTest {

    private Jugador jugadorConRecursosParaUnPoblado() {
        MazoDeRecursos gestor = new MazoDeRecursos(new ArrayList<>());
        Mano mano = new Mano();
        Jugador jugador = new Jugador(gestor, mano, "Alex");

        jugador.agregarRecurso(new Madera(), 1);
        jugador.agregarRecurso(new Ladrillo(), 1);
        jugador.agregarRecurso(new Grano(), 1);
        jugador.agregarRecurso(new Lana(), 1);

        return jugador;
    }

    @Test
    void Testo01NoSePuedeConstruirPobladosInicialesEnVerticesAdyacentes() {
        Jugador jugador1 = jugadorConRecursosParaUnPoblado();
        Jugador jugador2 = jugadorConRecursosParaUnPoblado();

        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();

        // v1---v2
        v1.conectarConVertice(v2);

        // Primer poblado: OK
        assertDoesNotThrow(() -> v1.construirPoblado(jugador1));

        // Segundo poblado en vértice adyacente rompe por regla de distancia
        assertThrows(ReglaDeDistanciaNoValida.class, () -> v2.construirPoblado(jugador2));
    }

    @Test
    void Testo02SePuedeConstruirPobladosInicialesEnVerticesNoAdyacentes() {
        Jugador jugador1 = jugadorConRecursosParaUnPoblado();
        Jugador jugador2 = jugadorConRecursosParaUnPoblado();

        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Vertice v3 = new Vertice();

        // Conecto solo v1 con v2, v3 queda aislado
        v1.conectarConVertice(v2);

        // Construyo en v1 y v3 sin violar distancia
        assertDoesNotThrow(() -> v1.construirPoblado(jugador1));
        assertDoesNotThrow(() -> v3.construirPoblado(jugador2));
    }
}
