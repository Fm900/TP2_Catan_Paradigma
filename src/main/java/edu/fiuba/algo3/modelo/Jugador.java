package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Recurso.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;
import java.util.Collection;

public class Jugador {
    private MazoDeRecursos recursos;
    private Collection<Carta> cartas;

    public Jugador() {
        cartas =  new ArrayList<>();
        recursos = new MazoDeRecursos();
    }

    public void agregarRecurso(Recurso recurso, int cantidad) {
        recursos.agregar(recurso, cantidad);
    }
    public void remover(Recurso recurso, int cantidad) {
        recursos.remover(recurso, cantidad);
    }
    public int cantidadRecurso(Recurso recurso) {
        return recursos.cantidadRecurso(recurso);
    }
}
