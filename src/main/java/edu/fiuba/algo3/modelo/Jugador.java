package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Recurso.GestorDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;
import java.util.Collection;

public class Jugador {
    private GestorDeRecursos gestor;
    private Collection<Carta> cartas;

    public Jugador() {
        cartas =  new ArrayList<>();
        gestor = new GestorDeRecursos();
    }

    public void agregarRecurso(Recurso recurso, int cantidad) {
        gestor.agregar(recurso, cantidad);
    }
    public void remover(Recurso recurso, int cantidad) {
        gestor.remover(recurso, cantidad);
    }
    public int cantidadRecurso(Recurso recurso) {
        return gestor.cantidadRecurso(recurso);
    }
}
