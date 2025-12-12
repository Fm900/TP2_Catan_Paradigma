package edu.fiuba.algo3.modelo.Jugador.Cartas;

import edu.fiuba.algo3.modelo.Exception.CartaDeshabilitada;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

import java.util.concurrent.ExecutionException;

public class Deshabilitado implements Efecto {

    public void usar(Carta carta, Jugador jugador){
        throw new CartaDeshabilitada("Este turno no es posible usar esta carta");
    }
}
