package edu.fiuba.algo3.modelo.Recurso;

import edu.fiuba.algo3.modelo.Jugador.GestorDeRecursos;

public class Grano extends Recurso {
    @Override
    public void agregar(int cantidad, GestorDeRecursos mazo) {
        mazo.agregarGrano(this ,cantidad);
    }

    @Override
    public void eliminar(GestorDeRecursos mazo) {
        mazo.removerGrano(this);
    }
}
