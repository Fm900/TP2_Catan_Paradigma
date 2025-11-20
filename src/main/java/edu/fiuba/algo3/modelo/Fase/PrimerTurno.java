package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice;

import java.util.List;

public class PrimerTurno implements FaseInicial {
    List<Jugador> jugadores;
    Tablero tablero;

    public void iniciarFase(List<Jugador> jugadores, Tablero tablero) {
        this.jugadores = jugadores;
        this.tablero = tablero;
    }

//    public void construirPoblado(vertice, arista, jugadorActual){
//        /// construir el problado / carretera principales debe ser con recursos precargados o iniciales por el super constructor X2
//        this.tablero.colocarPoblado(jugadorActual, vertice);
//    }
//    private void construirCarretera(Jugador jugador, Tablero tablero) {
//        /// se colocal una carretera conectada al poblado puesto anteriormente
//    }
}
