package edu.fiuba.algo3.modelo.Jugador.Cartas;

import edu.fiuba.algo3.modelo.Jugador.Jugador;

public interface Efecto {

    public void usar(Carta carta, Jugador jugador, ParametrosCarta parametros);
}
