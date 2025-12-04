package edu.fiuba.algo3.modelo.Turnos;

import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

public interface Turno {
    Jugador jugadorActual(ManejoTurnos manejador);
    void siguiente(ManejoTurnos manejador);
}