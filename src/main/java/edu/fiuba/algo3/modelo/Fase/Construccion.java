package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

public class Construccion implements FasePrincipal {
    private Jugador jugadorActual;
    private Tablero tablero;
        public void iniciarFase(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
        tablero = Juego.getInstancia().getTablero();
    }

    public void construirCarretera(Arista arista) {
        tablero.colocarCarretera(jugadorActual, arista);
    }

    public void construirPoblado(Vertice vertice){
        tablero.colocarPoblado(jugadorActual, vertice);
    }

    public void construirCiudad(Vertice vertice){
        tablero.mejorarPobladoACiudad(jugadorActual, vertice);
    }
}
