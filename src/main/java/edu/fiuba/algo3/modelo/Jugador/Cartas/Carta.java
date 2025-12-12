package edu.fiuba.algo3.modelo.Jugador.Cartas;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public abstract class Carta {

    protected List<Recurso> precio;
    protected Efecto activacion;


    public Carta(List<Recurso> precio) {
        this.precio = precio;
        this.activacion = new Deshabilitado();
    }

    public void intentarActivarEfecto(Jugador jugador) {
        this.activacion.usar(this, jugador);
    }

    public boolean equals(Object o) {
        return o != null && this.getClass() == o.getClass();
    }

    public void cambiarEstado() {
        this.activacion = new Habilitado();
    }

    public void agregarse(Jugador jugador) {
        jugador.consumirRecursos(precio);
        jugador.agregarCarta(this);
    }



    public abstract void activarEfecto(Jugador jugador);

}
