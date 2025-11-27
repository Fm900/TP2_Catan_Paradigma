package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Obligado implements Intercambio {

    private final Jugador victimario;
    private final Jugador victima;

    public Obligado(Jugador victimario, Jugador victima) {
        this.victimario = victimario;
        this.victima = victima;
    }

    @Override
    public void intercambio() {
        Recurso recursoADescartar = victima.obtenerRecursoAleatorio();
        List<Recurso> recursosADescartar = new ArrayList<Recurso>();
        recursosADescartar.add(recursoADescartar);
        victima.consumirRecursos(recursosADescartar);
        victimario.agregarRecurso(recursoADescartar, 1);
    }
}