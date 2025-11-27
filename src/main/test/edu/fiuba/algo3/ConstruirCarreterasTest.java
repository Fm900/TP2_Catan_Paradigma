package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ConstruirCarreterasTest {

    Vertice vertice1;
    Vertice vertice2;
    Jugador jugador1;
    Jugador jugador2;
    Arista arista;
    MazoDeRecursos recursos;
    Mano mano;


    @BeforeEach
    public void setUp() {
        List<Recurso> listaRecursos = (List.of(new Madera(), new Ladrillo(), new Grano(), new Lana(), new Madera(), new Ladrillo()));
        vertice1 = new Vertice();
        vertice2 = new Vertice();
        recursos = new MazoDeRecursos(listaRecursos);
        jugador1 = new Jugador(recursos, mano, "Hola");
        jugador2 = new Jugador(recursos, mano, "Hola");
        vertice1.conectarConVertice(vertice2);
    }

    @Test
    public void noSePuedeConstruirSinUnaConstruccionPropiaEnUnVerticeAdyacente() {

    }

}
