package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

public class Construccion implements FasePrincipal {
    private Jugador jugadorActual;
    private Vertice vertice;
    private Arista arista;


    public void iniciarFase(Jugador jugadorActual, Banca banca) {
        this.jugadorActual = jugadorActual;
    }

    public void construirEnArista(Arista arista, Jugador jugador){

    };

    public void construirEnVertice(Vertice vertice, Jugador jugador){

    };

}
