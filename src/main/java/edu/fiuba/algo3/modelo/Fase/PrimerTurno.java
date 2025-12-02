package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

public class PrimerTurno implements FaseInicial {
    @Override
    public void iniciarFase(Jugador jugador, Vertice vertice, Arista arista){
        Tablero tablero = Juego.getInstancia().getTablero();
        tablero.colocarPobladoInicial(jugador, vertice);
        tablero.colocarCarretera(jugador, arista);
    }
}