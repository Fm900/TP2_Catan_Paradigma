package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Jugador.GestorDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.Madera;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Ladron;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Alterado;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Normal;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class VerificarQueUnJugadorActivoPuedaMoverElLadronYRobarUnaCartaAleatoreaDeUnPropietario {

    Terreno terrenoActual;
    Terreno terrenoDestino;
    Vertice vertice1;
    Vertice vertice2;
    Ladron ladron;
    Jugador jugador1;
    Jugador jugador2;
    Recurso madera;
    List<Recurso> recursosJugador1;
    List<Recurso> recursosJugador2;

    @BeforeEach
    public void setUp() {
        madera = new Madera();
        vertice1 = new Vertice();
        vertice2 = new Vertice();
        recursosJugador1 = List.of();
        recursosJugador2 = List.of(madera);
        terrenoActual = new Terreno (madera, 5, (new Alterado()));
        terrenoDestino = new Terreno( madera, 10, (new Normal()));
        jugador1 = new Jugador((new GestorDeRecursos(recursosJugador1)), new Mano());
        jugador2 = new Jugador((new GestorDeRecursos(recursosJugador2)), new Mano());
        ladron = new Ladron(terrenoActual);
    }

    @Test
    public void test01SeVerificaQueSeRobaCartaAleatoriaDeUnPropietario() {

        vertice2.construirPoblado(jugador2);

        terrenoDestino.asignarVerticesAdyacentes(List.of(vertice1, vertice1, vertice1, vertice1, vertice1, vertice2));

        ladron.moverADestino(jugador1, terrenoDestino, jugador2);


        assertDoesNotThrow(() -> jugador1.consumirRecursos(recursosJugador2), "No tienes suficiente");
        assertThrows(RuntimeException.class, () -> jugador2.consumirRecursos(recursosJugador2), "No tienes suficiente");
    }
}