package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Turnos.Fase.JugarCartas;

import java.util.List;

public class ControladorJugarCartas {

    private final Jugador jugador;
    private final JugarCartas fase;
    private final ManejoTurnos manejo;

    public ControladorJugarCartas(ManejoTurnos manejo) {
        this.jugador = manejo.jugadorActual();
        this.fase = new JugarCartas();
        this.manejo = manejo;
    }

    public List<Carta> obtenerCartasJugador() {
        return jugador.obtenerCartas();
    }

    public void jugarCarta(Carta carta) {
        fase.ejecutar(jugador, manejo);
        //fase.jugarCarta(Carta);
    }
}
