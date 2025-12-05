package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class EntreJugadoresAleatoreo implements Intercambio {

    private final Jugador victimario;
    private final Jugador victima;

    public EntreJugadoresAleatoreo(Jugador victimario, Jugador victima) {
        this.victimario = victimario;
        this.victima = victima;
    }

    @Override
    public void intercambio() {
        Recurso recursoADescartar = victima.obtenerRecursoAleatorio();
        victima.consumirRecursos(List.of(recursoADescartar));
        victimario.agregarRecurso(recursoADescartar, 1);
    }
}