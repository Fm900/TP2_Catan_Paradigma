package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

import java.util.List;

public interface FaseInicial {
    public void iniciarFase(Jugador jugador, Vertice vertice, Arista arista);
}