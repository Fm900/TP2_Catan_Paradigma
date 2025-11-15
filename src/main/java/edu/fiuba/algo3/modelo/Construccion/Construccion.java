package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Jugador;
import java.util.List;

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

    public List<Jugador> agregarPropietario(List<Jugador> propietarios) {
        if (propietarios.contains(this.dueño)) {
            return propietarios;
        }
        propietarios.add(this.dueño);
        return propietarios;
    }

    public Jugador getDueño() {
        return dueño;
    }
}
