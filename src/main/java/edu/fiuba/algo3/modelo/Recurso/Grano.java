package edu.fiuba.algo3.modelo.Recurso;

import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;

public class Grano implements Recurso {
    @Override
    public void agregar(int cantidad, MazoDeRecursos mazo) {
        mazo.agregarRecurso(this ,cantidad);
    }

    @Override
    public void eliminar(MazoDeRecursos mazo) {
        mazo.removerRecurso(this);
    }

    @Override
    public int getCantidad(MazoDeRecursos mazo) {
        return mazo.getCantidadDe(this);
    }

    @Override
    public boolean mismaClaseQue(Recurso otro) {
        return this.getClass().equals(otro.getClass());
    }
}
