package edu.fiuba.algo3.modelo.Tablero.Terreno;

import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

import java.util.List;

public class Alterado implements EstadoProductivo {

    @Override
    public void producir(Recurso recurso, List<Vertice> vertices_adyacentes) {

    }

    @Override
    public EstadoProductivo alterarEstado() {
        EstadoProductivo nuevoestado = new Normal();
        return nuevoestado;
    }
}
