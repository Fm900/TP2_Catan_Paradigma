package edu.fiuba.algo3.modelo;

import java.util.List;

public interface EstadoProductivo {
    void producir(String recurso, List<Vertice> vertices_adyacentes);
}
