package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Estandar;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Tasa;
import edu.fiuba.algo3.modelo.Turnos.Fase.Comercio;
import edu.fiuba.algo3.vistas.Tablero.VistaComercio;

import java.util.ArrayList;
import java.util.List;

public class ControladorComercioConBanca {

    private VistaComercio vista;
    private final Jugador jugadorActual;
    private final Comercio comercioActual;
    private final ManejoTurnos manejoTurnos;
    private final Estandar estandar;
    private Tasa tasa;

    public ControladorComercioConBanca(Jugador jugadorActual, ManejoTurnos manejadorDeTurnos) {
        this.jugadorActual = jugadorActual;
        comercioActual = new Comercio();
        this.manejoTurnos = manejadorDeTurnos;
        this.estandar = new Estandar();
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


    public void abrirComercioDirectoConBanca() {
        comercioActual.ejecutar(jugadorActual, manejoTurnos);
        vista.mostrarEleccionDeTasa();
    }

    public void abrirCompraDeCartas() {
        comercioActual.ejecutar(jugadorActual, manejoTurnos);
        vista.comprarCarta();
    }

    public void abrirComercioConPuerto() {
        comercioActual.ejecutar(jugadorActual, manejoTurnos);
        vista.mostrarPantallaPuertos();
    }

    public Carta comprarCarta() {
        Carta carta = Banca.getInstance().popPrimerCarta();
        comercioActual.comprarCarta();
        return carta;
    }


    public void iniciarComercioConBanca(List<Recurso> recursosJugador, Recurso recursosBanca) {
        comercioActual.comerciarConBanca(estandar, recursosJugador, recursosBanca);
    }

    public List<Tasa> obtenerTasasDisponibles() {
        return Juego.getInstancia().getTasas(jugadorActual);
    }


    public void seleccionarTasa(Tasa t) {
        this.tasa = t;
        vista.mostrarIntercambioConPuerto(t);
    }

    public void efectuarComercioConPuerto(Tasa tasa, ArrayList<Recurso> recursos, Recurso recurso) {
        comercioActual.comerciarConBanca(tasa, recursos, recurso);
    }
}