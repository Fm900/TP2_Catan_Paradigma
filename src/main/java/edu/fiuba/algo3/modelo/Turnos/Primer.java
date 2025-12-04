package edu.fiuba.algo3.modelo.Turnos;

import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

public class Primer implements Turno {

    private int indice = 0;
    private final Tablero tablero;

    public Primer() {
        this.tablero = Juego.getInstancia().getTablero();
    }

    @Override
    public Jugador jugadorActual(ManejoTurnos manejador) {
        return manejador.jugadores.get(indice);
    }

    @Override
    public void siguiente(ManejoTurnos manejador) {
        indice++;

        if (indice == manejador.getJugadores().size()) {
            manejador.cambiarTurno(new Segundo());
        }
    }
    public void construir(ManejoTurnos manejador, Vertice vertice, Arista arista){
        Jugador jugador = jugadorActual(manejador);

        tablero.colocarPobladoInicial(jugador, vertice);
        tablero.colocarCarretera(jugador, arista);
    }
}
