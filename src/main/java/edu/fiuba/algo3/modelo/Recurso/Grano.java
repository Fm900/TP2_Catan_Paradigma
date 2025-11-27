package edu.fiuba.algo3.modelo.Recurso;

import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;

public class Grano extends Recurso {
    @Override
    public void agregar(int cantidad, MazoDeRecursos mazo) {
        mazo.agregarGrano(this ,cantidad);
    }

    @Override
    public void eliminar(MazoDeRecursos mazo) {
        mazo.removerGrano(this);
    }

    @Override
    public int getCantidad(MazoDeRecursos mazo) {
        return mazo.getCantidadDe(this);
    }
}
