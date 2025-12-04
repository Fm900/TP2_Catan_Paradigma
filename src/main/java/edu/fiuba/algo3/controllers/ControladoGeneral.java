package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.vistas.Tablero.VistaTablero;
import javafx.stage.Stage;

import java.util.List;

public class ControladoGeneral {
    private List<Jugador> jugadores;
    private Jugador jugadorActual;
    private Juego juego;
    private Tablero tablero;
    private Stage stage;


    public ControladoGeneral(Stage stage, Juego juego) {
        this.juego = juego;
        this.jugadores = juego.getJugadores();
        this.tablero = juego.getTablero();
        this.stage = stage;
        this.jugadorActual = jugadores.get(0);
        mostrar();
        iniciarPrimerTurno();
    }
    public void mostrar() {
        boolean estabaFullScreen = stage.isFullScreen();
        boolean estabaMaximized = stage.isMaximized();
        VistaTablero vistaTablero = new VistaTablero(tablero, stage, this.jugadores, jugadorActual);
        stage.setScene(vistaTablero.getScene());

        if (estabaFullScreen) {
            stage.setFullScreen(true);
        } else {
            stage.setMaximized(estabaMaximized);
        }
    }
    public void iniciarPrimerTurno(){

    }
}
