package edu.fiuba.algo3.modelo.Recurso;

import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;

public interface Recurso {
    void agregar(int cantidad, MazoDeRecursos mazo);
    void eliminar(MazoDeRecursos mazo);
    int getCantidad(MazoDeRecursos mazo);
    boolean mismaClaseQue(Recurso otro);
}
