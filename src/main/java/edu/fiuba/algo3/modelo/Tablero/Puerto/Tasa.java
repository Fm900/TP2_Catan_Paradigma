package edu.fiuba.algo3.modelo.Tablero.Puerto;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public interface Tasa {
    public List<Recurso> aplicarTasa(List<Recurso> recurso, Jugador jugador);
}
