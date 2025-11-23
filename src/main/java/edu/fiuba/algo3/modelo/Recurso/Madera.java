package edu.fiuba.algo3.modelo.Recurso;


import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;

public class Madera extends Recurso{
    @Override
    public void agregar(int cantidad, MazoDeRecursos mazo) {
        mazo.agregarMadera(cantidad, this);
    }

    @Override
    public void eliminar(MazoDeRecursos mazo) {
        mazo.removerMadera(this);
    }
}
