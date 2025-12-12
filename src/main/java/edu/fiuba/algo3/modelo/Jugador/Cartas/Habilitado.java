package edu.fiuba.algo3.modelo.Jugador.Cartas;

import edu.fiuba.algo3.modelo.Jugador.Jugador;

public class Habilitado implements Efecto {

    public void usar(Carta carta, Jugador jugador){
        carta.activarEfecto(jugador);
    }
}
