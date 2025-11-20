package edu.fiuba.algo3.modelo.Tablero.Terreno;

import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

import java.util.List;

public interface EstadoProductivo {
    void producir(Recurso recurso, List<Vertice> vertices_adyacentes);
    EstadoProductivo alterarEstado();
}
