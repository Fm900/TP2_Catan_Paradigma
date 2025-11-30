package edu.fiuba.algo3.modelo.Recurso;

import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;

public class Desierto extends Recurso{
    @Override
    public void agregar(int cantidad, MazoDeRecursos mazo) {
        //No hace nada
    }

    @Override
    public void eliminar(MazoDeRecursos mazo) {
        //No hace nada
    }

    @Override
    public int getCantidad(MazoDeRecursos mazo) {
        return 0;
    }

    @Override
    public boolean mismaClaseQue(Recurso otro) {
        return false;
    }
}
