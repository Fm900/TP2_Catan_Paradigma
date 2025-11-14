package edu.fiuba.algo3.modelo.Tablero;

import java.util.List;

public class Normal implements EstadoProductivo {
    @Override
    public void producir(String recurso, List<Vertice> verticesAdyacentes) {
        for (Vertice verticeAdyacente : verticesAdyacentes) {
            verticeAdyacente.entregarRecursosPorConstruccion(recurso);
        }
    }

    @Override
    public EstadoProductivo alterarEstado() {
        EstadoProductivo nuevoestado = new Alterado();
        return nuevoestado;
    }
}
