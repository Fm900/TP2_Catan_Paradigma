package edu.fiuba.algo3.modelo.Recurso;


public class Ladrillo extends Recurso {
    @Override
    public void agregar(int cantidad, MazoDeRecursos mazo) {
        mazo.agregarLadrillo(cantidad, this);
    }

    @Override
    public void eliminar(MazoDeRecursos mazo) {
        mazo.removerLadrillo(this);
    }
}
