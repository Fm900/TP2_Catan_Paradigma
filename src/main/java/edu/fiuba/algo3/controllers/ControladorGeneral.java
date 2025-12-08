package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import edu.fiuba.algo3.modelo.Turnos.Normal;
import edu.fiuba.algo3.modelo.Turnos.Primer;
import edu.fiuba.algo3.modelo.Turnos.Segundo;
import edu.fiuba.algo3.modelo.Turnos.Turno;
import edu.fiuba.algo3.modelo.Turnos.Fase.Fase;
import edu.fiuba.algo3.vistas.Tablero.GeneradorVistaTablero;
import edu.fiuba.algo3.vistas.Tablero.VistaTablero;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.List;

public class ControladorGeneral implements ControladorDeClickTablero{
    private final List<Jugador> jugadores;
    private Jugador jugadorActual;
    private final Juego juego;
    private final Tablero tablero;
    private final Stage stage;
    private final ManejoTurnos manejoTurnos;
    private VistaTablero vistaTablero;
    private GeneradorVistaTablero generadorVista;
    private Vertice verticeSeleccionado;
    private Arista aristaSeleccionada;
    private Circle circuloSeleccionado;
    private Line lineaSeleccionada;

    public ControladorGeneral(Stage stage, Juego juego) {
        this.juego = juego;
        this.jugadores = juego.getJugadores();
        this.tablero = juego.getTablero();
        this.stage = stage;
        this.manejoTurnos = new ManejoTurnos(jugadores);
        this.jugadorActual = manejoTurnos.jugadorActual();

        inicializarVista();
        iniciarPrimerTurno();
    }

    private void inicializarVista() {
        boolean estabaFullScreen = stage.isFullScreen();
        boolean estabaMaximized = stage.isMaximized();

        vistaTablero = new VistaTablero(tablero, stage, jugadores, jugadorActual);
        stage.setScene(vistaTablero.getScene());

        generadorVista = vistaTablero.getGeneradorVista();
        if (generadorVista != null) {
            generadorVista.setListener(this);
        }

        if (estabaFullScreen) {
            stage.setFullScreen(true);
        } else {
            stage.setMaximized(estabaMaximized);
        }
    }

    private void iniciarPrimerTurno() {
        actualizarVista();
        mostrarMensaje("Turno de colocación inicial - Primera ronda");
    }

    public void siguienteTurno() {
        manejoTurnos.siguiente();
        jugadorActual = manejoTurnos.jugadorActual();
        actualizarVista();
        notificarCambioTurno();
    }

    public void siguienteFase() {
        manejoTurnos.pasarSiguienteFase();
        actualizarVista();
        notificarCambioFase();
    }

    private void notificarCambioTurno() {
        Turno turnoActual = manejoTurnos.getTurnoActual();

        if (turnoActual instanceof Primer) {
            mostrarMensaje("Primera ronda - Turno de " + jugadorActual.obtenerNombre());
        } else if (turnoActual instanceof Segundo) {
            mostrarMensaje("Segunda ronda - Turno de " + jugadorActual.obtenerNombre());
        } else if (turnoActual instanceof Normal) {
            Normal normal = (Normal) turnoActual;
            mostrarMensaje("Turno de " + jugadorActual.obtenerNombre() + " - Fase: " + normal.nombreFaseActual());
        }
    }

    private void notificarCambioFase() {
        Turno turnoActual = manejoTurnos.getTurnoActual();

        if (turnoActual instanceof Normal) {
            Normal normal = (Normal) turnoActual;
            mostrarMensaje("Fase: " + normal.nombreFaseActual());
        }
    }

    public ManejoTurnos getManejoTurnos() {
        return manejoTurnos;
    }

    private void actualizarVista() {
        vistaTablero.actualizarJugadorActual(manejoTurnos.jugadorActual());
        vistaTablero.actualizarInfoTurno(getNombreFaseActual());
    }

    private void mostrarMensaje(String mensaje) {
        ControladorDeAlerta.mostrarInfo(mensaje, stage);
    }

    public void verificarVictoria() {
        for (Jugador jugador : jugadores) {
            if (this.juego.chequearVictoria(jugador)){
                mostrarVictoria(jugador);
            }

        }
    }

    private void mostrarVictoria(Jugador ganador) {

    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public Turno getTurnoActual() {
        return manejoTurnos.getTurnoActual();
    }

    public Fase getFaseActual() {
        Turno turno = manejoTurnos.getTurnoActual();
        if (turno instanceof Normal) {
            return ((Normal) turno).faseActual();
        }
        return null;
    }

    public String getNombreFaseActual() {
        Turno turno = manejoTurnos.getTurnoActual();
        if (turno instanceof Normal) {
            return ((Normal) turno).nombreFaseActual();
        } else if (turno instanceof Primer) {
            return "Colocación Inicial - Primera Ronda";
        } else if (turno instanceof Segundo) {
            return "Colocación Inicial - Segunda Ronda";
        }
        return "Desconocido";
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Juego getJuego() {
        return juego;
    }

    @Override
    public void onSeleccionCancelada() {
        this.verticeSeleccionado = null;
        this.aristaSeleccionada = null;
        this.circuloSeleccionado = null;
        this.lineaSeleccionada = null;
        System.out.println("Selección cancelada");
    }

    @Override
    public void onVerticeSeleccionado(Vertice v, Circle c) {
        this.verticeSeleccionado = v;
        this.circuloSeleccionado = c;
    }

    @Override
    public void onAristaSeleccionada(Arista a, Line l) {
        this.aristaSeleccionada = a;
        this.lineaSeleccionada = l;
    }
}