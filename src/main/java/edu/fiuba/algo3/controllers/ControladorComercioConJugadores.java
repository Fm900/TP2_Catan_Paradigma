package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Intercambio.Oferta.Oferta;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Turnos.Fase.Comercio;

import edu.fiuba.algo3.vistas.Tablero.VistaComercio;
import java.util.ArrayList;
import java.util.List;

public class ControladorComercioConJugadores {

    private VistaComercio vista;

    private final Jugador jugadorActual;

    private final ManejoTurnos manejoTurnos;
    private Oferta ofertaActual;
    private Comercio comercioActual;

    public ControladorComercioConJugadores(Jugador jugadorActual, ManejoTurnos manejoTurnos) {
        this.jugadorActual = jugadorActual;
        this.manejoTurnos = manejoTurnos;
    }

    public void setVista(VistaComercio vista) {
        this.vista = vista;
    }

    // ----------------------------------------------------------------------
    // ABRIR PANTALLAS
    // ----------------------------------------------------------------------
    public void abrirComercioEntreJugadores() {
        vista.mostrarComercioEntreJugadores();
    }

    public void abrirComercioConBanca() {
        System.out.println("Comercio con banca todavía no implementado.");
    }

    // ----------------------------------------------------------------------
    // DATOS PARA LA VISTA
    // ----------------------------------------------------------------------

    public List<Jugador> obtenerJugadoresExceptoActual() {
        List<Jugador> jugadores = new ArrayList<>(Juego.getInstancia().getJugadores());
        jugadores.remove(jugadorActual);
        return jugadores;
    }

    public List<Recurso> obtenerRecursosJugadorActual() {
        return new ArrayList<>(jugadorActual.obtenerRecursos());
    }

    public List<Recurso> obtenerRecursosJugadorElegido(Jugador jugador) {
        return new ArrayList<>(jugador.obtenerRecursos());
    }

    // ----------------------------------------------------------------------
    // MÉTODO SOLICITADO: manejarComercioEntreJugadores
    // ----------------------------------------------------------------------
    public void manejarComercioEntreJugadores(List<Recurso> recursosQueOfrece, List<Recurso> recursosQuePide, Jugador jugadorElegido) {
        comercioActual = new Comercio();
        comercioActual.ejecutar( jugadorActual,  manejoTurnos);
        this.ofertaActual =  comercioActual.crearOfertaJugador(jugadorElegido, recursosQueOfrece,recursosQuePide);


        if (vista != null) {
            vista.mostrarPantallaRespuestaOferta(jugadorElegido);
        }
    }

    // ----------------------------------------------------------------------
    // RESPUESTA A LA OFERTA
    // ----------------------------------------------------------------------
    public void responderOferta(boolean aceptada) {
        if (aceptada) {
            comercioActual.aceptarOferta(ofertaActual);
        } else {
            comercioActual.rechazarOferta(ofertaActual);
        }
        if (vista != null) vista.mostrarPantallaInicial();
    }
}