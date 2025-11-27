package edu.fiuba.algo3.modelo.Recurso;


import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;

public class Mineral extends Recurso {
    @Override
    public void agregar(int cantidad, MazoDeRecursos mazo) {
        mazo.agregarMineral(cantidad, this);
    }

    @Override
    public void eliminar(MazoDeRecursos mazo) {
        mazo.removerMineral(this);
    }

    @Override
    public int getCantidad(MazoDeRecursos mazo) {
        return mazo.getCantidadDe(this);
    }
}
