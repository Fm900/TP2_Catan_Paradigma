package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Turnos.Fase.Comercio;
import edu.fiuba.algo3.modelo.Turnos.Turno;
import edu.fiuba.algo3.vistas.Tablero.VistaComercio;

import java.util.List;

public class ControladorComercioConBanca {

    private VistaComercio vista;
    private final Jugador jugadorActual;
    private final Comercio comercioActual;
    private final ManejoTurnos manejoTurnos;

    public ControladorComercioConBanca(Jugador jugadorActual, ManejoTurnos manejadorDeTurnos) {
        this.jugadorActual = jugadorActual;
        comercioActual = new Comercio();
        this.manejoTurnos = manejadorDeTurnos;
    }

    public void setVista(VistaComercio vista) {
        this.vista = vista;
    }

    public void abrirComercioConBanca() {
        vista.mostrarComercioConBanca();
    }

    // recursos del jugador actual
    public List<Recurso> obtenerRecursosJugadorActual() {
        return jugadorActual.obtenerRecursos();
    }

    public void abrirComercioConPuerto() {
    }

    public void abrirComercioDirectoConBanca() {
    }

    public void abrirCompraDeCartas() {
        comercioActual.ejecutar(jugadorActual, manejoTurnos);
        vista.mostrarCompraDeCartas();
    }


    public void comprarCartas(List<Carta> cartasSeleccionadas) {
        for (Carta carta : cartasSeleccionadas) {
            comercioActual.comprarCarta(carta);
        }
    }
}