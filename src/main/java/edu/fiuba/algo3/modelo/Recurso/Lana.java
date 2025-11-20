package edu.fiuba.algo3.modelo.Recurso;

public class Lana extends Recurso {

    @Override
    public void agregar(int cantidad, GestorDeRecursos mazo){
        mazo.agregarLana(this ,cantidad);
    }
    @Override
    public void eliminar(GestorDeRecursos mazo){
        mazo.removerLana(this);
    }
}
