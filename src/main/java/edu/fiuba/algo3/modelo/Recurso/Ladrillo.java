package edu.fiuba.algo3.modelo.Recurso;


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
