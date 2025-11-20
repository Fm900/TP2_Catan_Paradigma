package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Jugador.Jugador;

import java.util.List;

public class Obligado implements Intercambiar {

    private Jugador victimario;
    private Jugador victima;

    public Obligado(Jugador victimario, Jugador victima) {
        this.victimario = victimario;
        this.victima = victima;
    }

    @Override
    public void intercambio() {
        String recursoAleatorio = ((this.victima).obtenerRecursoAleatorio());
        victimario.agregarRecursos(recursoAleatorio, 1);
        victima.descartarRecursos(recursoAleatorio, 1);

    }
}