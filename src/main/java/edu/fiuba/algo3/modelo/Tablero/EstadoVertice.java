package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Jugador;

public interface EstadoVertice  {
    void construirPoblado (Vertice self, Jugador jugador);
    void entregarRecursosPorConstruccion(String recurso);
    boolean validarConstruccionEnVecino();
}

