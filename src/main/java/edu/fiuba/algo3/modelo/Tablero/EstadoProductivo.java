package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public interface EstadoProductivo {
    void producir(Recurso recurso, List<Vertice> vertices_adyacentes);
    EstadoProductivo alterarEstado();
}
