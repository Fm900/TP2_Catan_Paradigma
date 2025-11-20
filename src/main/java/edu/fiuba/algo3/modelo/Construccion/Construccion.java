package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Exception.NoSePuedeMejorarACiudad;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public abstract class Construccion {
    protected Integer puntosDeVictoria;
    protected Jugador dueño;
    protected List<Recurso> precio;
    protected final int coeficienteDeProduccion;

    public Construccion(Integer puntosDeVictoria, int coeficienteDeProduccion, Jugador dueño,  List<Recurso> precio) {
        this.puntosDeVictoria = puntosDeVictoria;
        this.coeficienteDeProduccion = coeficienteDeProduccion;
        this.dueño =  dueño;
        this.precio = precio;
    }

    public void producirRecurso(Recurso recurso){
        dueño.agregarRecurso(recurso, this.coeficienteDeProduccion);
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

    public abstract void construir();
}
