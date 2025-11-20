package edu.fiuba.algo3.modelo.Recurso;

import edu.fiuba.algo3.modelo.Jugador.GestorDeRecursos;

public abstract class Recurso {

    public abstract void agregar(int cantidad, GestorDeRecursos mazo);

    public abstract void eliminar(GestorDeRecursos mazo);

    @Override
    public boolean equals(Object o) {
        return o != null && this.getClass() == o.getClass();
    }
}
