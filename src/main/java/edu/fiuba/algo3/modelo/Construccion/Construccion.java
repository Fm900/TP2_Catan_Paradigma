package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso;


public abstract class Construccion {
    protected Integer puntosDeVictoria;
    protected Producir producir;
    protected Jugador dueño;

    public Construccion(Integer puntosDeVictoria, Producir producir, Jugador dueño){
        this.puntosDeVictoria = puntosDeVictoria;
        this.producir = producir;
        this.dueño =  dueño;
    }

    //public abstract void construir();

    public void producirRecurso(String recurso){
        producir.producir(recurso, dueño);
    }
}
