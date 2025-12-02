package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import java.util.List;

public interface FaseInicial {
    public void iniciarFase(List<Jugador> jugadores, Tablero tablero);
}