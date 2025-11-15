package edu.fiuba.algo3;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;


public class VerticeTest {

    @Test
    void Test01conectarConVerticeCreaAristaEnAmbosVertices() {
        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();

        v1.conectarConVertice(v2);

        // la arista debe ser la misma
        assertSame(v1.aristas().get(0), v2.aristas().get(0));
    }

}
