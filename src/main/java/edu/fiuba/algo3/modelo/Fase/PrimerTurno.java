package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

import java.util.List;

public class PrimerTurno implements FaseInicial {
    List<Jugador> jugadores;
    Tablero tablero;
    private Vertice verticeParaConstruir;

    @Override
    public void iniciarFase(List<Jugador> jugadores, Tablero tablero) {

        for (int i = jugadores.size() - 1; i >= 0; i--) {
            Jugador jugador = jugadores.get(i);
            tablero.colocarPobladoInicial(jugador, this.verticeParaConstruir);
        }
    }

    public void setVerticeParaConstruir(Vertice vertice){
        this.verticeParaConstruir = vertice;
    }
}