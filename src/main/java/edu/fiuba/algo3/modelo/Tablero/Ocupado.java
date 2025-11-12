package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Jugador;

public class Ocupado implements EstadoVertice {
    private final Construccion construccion;

    public Ocupado(Construccion construccion) {
        this.construccion = construccion;
    }

    @Override
    public void construirPoblado(Vertice self, Jugador jugador) {
    }

    @Override
    public void entregarRecursosPorConstruccion(String recurso) {
        // delega en la construcción
        construccion.producirRecurso(recurso);
    }

    @Override
    public boolean validarConstruccionEnVecino() {
        return false;
    }
}
