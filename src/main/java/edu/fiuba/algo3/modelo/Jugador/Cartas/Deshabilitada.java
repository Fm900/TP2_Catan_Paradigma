package edu.fiuba.algo3.modelo.Jugador.Cartas;

import edu.fiuba.algo3.modelo.Jugador.Jugador;

public class Deshabilitada implements ActivarEfecto{

    @Override
    public ActivarEfecto activar() {
        return (new Habilitada());
    }
}
