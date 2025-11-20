package edu.fiuba.algo3.modelo.Recurso;


import edu.fiuba.algo3.modelo.Jugador.GestorDeRecursos;

public class Madera extends Recurso{
    @Override
    public void agregar(int cantidad, GestorDeRecursos mazo) {
        mazo.agregarMadera(cantidad, this);
    }

    @Override
    public void eliminar(GestorDeRecursos mazo) {
        mazo.removerMadera(this);
    }
}
