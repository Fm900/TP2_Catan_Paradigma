package edu.fiuba.algo3.modelo.Turnos;

import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Turnos.Fase.*;

import java.util.ArrayList;
import java.util.List;

public class Normal implements Turno {

    private final List<Fase> fases = new ArrayList<>();
    private int faseActual = 0;
    private int indiceJugador = 0;
    private ManejoTurnos manejador;

    public Normal() {
        inicializarFases();
    }

    private void inicializarFases() {
        fases.add(new Dados());
        fases.add(new Comercio());
        fases.add(new Construccion());
        fases.add(new JugarCartas());
    }

    @Override
    public Jugador jugadorActual(ManejoTurnos manejador) {
        this.manejador = manejador;
        return manejador.getJugadores().get(indiceJugador);
    }

    @Override
    public void siguiente(ManejoTurnos manejador) {
        this.manejador = manejador;
        ejecutarFaseActual();
    }

    private void ejecutarFaseActual() {
        Jugador jugador = manejador.getJugadores().get(indiceJugador);
        fases.get(faseActual).ejecutar(jugador, manejador);
    }

    public void pasarSiguienteFase() {
        faseActual++;

        if (faseActual >= fases.size()) {
            avanzarSiguienteJugador();
        }
    }

    private void avanzarSiguienteJugador() {
        faseActual = 0;
        if (manejador != null) {
            indiceJugador = (indiceJugador + 1) % manejador.getJugadores().size();
        }
    }

    public Fase faseActual() {
        return fases.get(faseActual);
    }

    public String nombreFaseActual() {
        return fases.get(faseActual).getClass().getSimpleName();
    }

    public int numeroRonda() {
        if (manejador == null) return 1;
        int totalJugadores = manejador.getJugadores().size();
        return (indiceJugador * fases.size() + faseActual) / (totalJugadores * fases.size()) + 1;
    }

    public Jugador getJugadorActual() {
        return manejador != null ? manejador.getJugadores().get(indiceJugador) : null;
    }
}