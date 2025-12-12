package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

public class Construccion implements FasePrincipal {
    private Jugador jugadorActual;
    private Vertice vertice;
    private Arista arista;


    public void iniciarFase(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public void construirEnArista(Arista arista, Jugador jugador){

    };

    public void construirEnVertice(Vertice vertice, Jugador jugador){

    };

}
