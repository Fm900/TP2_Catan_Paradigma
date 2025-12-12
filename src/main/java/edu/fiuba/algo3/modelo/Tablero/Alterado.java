package edu.fiuba.algo3.modelo.Tablero;

import java.util.List;

public class Alterado implements EstadoProductivo {
    @Override
    public void producir(String recurso, List<Vertice> vertices_adyacentes) {
    }
    @Override
    public EstadoProductivo alterarEstado() {
        EstadoProductivo nuevoestado = new Normal();
        return nuevoestado;
    }
}
