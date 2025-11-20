package edu.fiuba.algo3.modelo.Recurso;


import edu.fiuba.algo3.modelo.Jugador.GestorDeRecursos;

public class Ladrillo extends Recurso {
    @Override
    public void agregar(int cantidad, GestorDeRecursos mazo) {
        mazo.agregarLadrillo(cantidad, this);
    }

    @Override
    public void eliminar(GestorDeRecursos mazo) {
        mazo.removerLadrillo(this);
    }
}
