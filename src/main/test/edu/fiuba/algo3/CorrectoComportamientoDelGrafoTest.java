package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Exception.ReglaDeDistanciaNoValida;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CorrectoComportamientoDelGrafoTest {
    private Jugador nuevoJugador() {
        MazoDeRecursos gestor = new MazoDeRecursos(new ArrayList<>());
        Mano mano = new Mano();
        return new Jugador(gestor, mano,"Alex");
    }

    @Test
    void noSePuedeConstruirPobladoEnVerticesVecinos(){
        Tablero tablero = new Tablero();
        Jugador jugador = nuevoJugador();

        jugador.agregarRecurso(new Madera(), 10);
        jugador.agregarRecurso(new Ladrillo(), 10);
        jugador.agregarRecurso(new Grano(), 10);
        jugador.agregarRecurso(new Lana(), 10);
        jugador.agregarRecurso(new Mineral(),10);

        Vertice v1 = tablero.vertice(4);
        Vertice v2 = tablero.vertice(1); //en el grafo final, los vertices 4 y 1

        tablero.colocarPobladoInicial(jugador, v1); //podria construir en el primero

        assertThrows(ReglaDeDistanciaNoValida.class, ()-> {tablero.colocarPobladoInicial(jugador,v2);}); // no me deberia dejar construir en uno vecino
    }
    @Test
    void MeDejaConstruirEnVerticeCorrecto(){
        Tablero tablero = new Tablero();
        Jugador jugador = nuevoJugador();
        Vertice v1 = tablero.vertice(4);
        Vertice v2 = tablero.vertice(1);
        Vertice v3 = tablero.vertice(5);
        Arista arista1 = tablero.aristaEntre(v1,v2);
        Arista arista2 = tablero.aristaEntre(v2,v3);

        jugador.agregarRecurso(new Madera(), 10);
        jugador.agregarRecurso(new Ladrillo(), 10);
        jugador.agregarRecurso(new Grano(), 10);
        jugador.agregarRecurso(new Lana(), 10);
        jugador.agregarRecurso(new Mineral(),10);

        tablero.colocarPobladoInicial(jugador, v1); //podria construir en el primero
        tablero.colocarCarretera(jugador,arista1);  //coloco carretera entre 4 y 1
        tablero.colocarCarretera(jugador,arista2); //coloco carretera entre 1 y 5
        assertDoesNotThrow(()-> tablero.colocarPoblado(jugador,v3)); //coloco poblado en 5
    }
}
