package edu.fiuba.algo3.modelo.Turnos.Fase;

import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

public class Construccion implements Fase {
    private Jugador jugadorActual;
    private Tablero tablero;

    @Override
    public void ejecutar(Jugador jugador, ManejoTurnos manejador) {
        this.jugadorActual = jugador;
        this.tablero = Juego.getInstancia().getTablero();
        this.jugadorActual.habilitarCartas();
    }


    public void construirCarretera(Arista arista) {
        validarFaseIniciada();
        tablero.colocarCarretera(jugadorActual, arista);
    }

    public void construirPoblado(Vertice vertice) {
        validarFaseIniciada();
        tablero.colocarPoblado(jugadorActual, vertice);
    }

    public void construirCiudad(Vertice vertice) {
        validarFaseIniciada();
        tablero.mejorarPobladoACiudad(jugadorActual, vertice);
    }

    public void terminarFase(ManejoTurnos manejador) {
        manejador.pasarSiguienteFase();
    }

    private void validarFaseIniciada() {
        if (jugadorActual == null || tablero == null) {
            throw new IllegalStateException("La fase de construcción no ha sido inicializada");
        }
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }
}