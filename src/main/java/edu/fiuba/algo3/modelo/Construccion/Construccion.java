package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Exception.Excepciones.NoSePuedeMejorarACiudad;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

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

    public void producirRecurso(Recurso recurso){
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

    public Construccion mejorarACiudad(){
        throw new NoSePuedeMejorarACiudad("Esta construccion no puede mejorarse a ciudad");
    }
}
