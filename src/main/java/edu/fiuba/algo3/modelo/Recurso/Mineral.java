package edu.fiuba.algo3.modelo.Recurso;


public class Mineral extends Recurso {
    @Override
    public void agregar(int cantidad, GestorDeRecursos mazo) {
        mazo.agregarMineral(cantidad, this);
    }

    @Override
    public void eliminar(GestorDeRecursos mazo) {
        mazo.removerMineral(this);
    }
}
