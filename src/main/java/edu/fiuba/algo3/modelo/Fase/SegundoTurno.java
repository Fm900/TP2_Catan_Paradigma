package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import java.util.List;

public class SegundoTurno implements FaseInicial{

    @Override
    public void iniciarFase(List<Jugador> jugadores, Tablero tablero) {
        for (int i = jugadores.size() - 1; i >= 0; i--) {
            Jugador jugador = jugadores.get(i);
        }
    }
//    public void construirCarretera(Jugador jugador, Tablero tablero) {
//
//    }
//    public void construirPoblado(Jugador jugador, Tablero tablero, Vertice vertice){
//        tablero.colocarPoblado(jugador, vertice);
//        agregarRecursosPorTerrenosAdyacentes(jugador, tablero, vertice);
//    }
//    public void agregarRecursosPorTerrenosAdyacentes(Jugador jugador, Tablero tablero, Vertice vertice) {
//        vertice.encontrarTerrenos(new Poblado());
//    }
}
