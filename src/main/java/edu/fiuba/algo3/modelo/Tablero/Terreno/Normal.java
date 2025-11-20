package edu.fiuba.algo3.modelo.Tablero.Terreno;

import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

import java.util.List;

public class Normal implements EstadoProductivo {

    public void producir(String recurso, List<Vertice> verticesAdyacentes) {
        for (Vertice verticeAdyacente : verticesAdyacentes) {
            verticeAdyacente.entregarRecursosPorConstruccion(recurso);
        }
    }

    @Override
    public void producir(Recurso recurso, List<Vertice> vertices_adyacentes) {

    }

    @Override
    public EstadoProductivo alterarEstado() {
        EstadoProductivo nuevoestado = new Alterado();
        return nuevoestado;
    }
}
