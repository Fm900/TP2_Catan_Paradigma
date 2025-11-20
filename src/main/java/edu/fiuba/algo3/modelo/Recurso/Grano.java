package edu.fiuba.algo3.modelo.Recurso;

public class Grano extends Recurso {
    @Override
    public void agregar(int cantidad, MazoDeRecursos mazo) {
        mazo.agregarGrano(this ,cantidad);
    }

    @Override
    public void eliminar(MazoDeRecursos mazo) {
        mazo.removerGrano(this);
    }
}
