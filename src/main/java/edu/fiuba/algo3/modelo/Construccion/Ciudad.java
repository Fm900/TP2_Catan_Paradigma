package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Jugador;

public class Ciudad extends Construccion{

    public Ciudad(Integer puntosDeVictoria, Producir producir, Jugador dueño) {
        super(puntosDeVictoria, producir, dueño);
    }

    public static Construccion construir(Jugador jugador) {
        jugador.consumirRecursosParaCiudad();
        ProducirX2 producirX2 = new ProducirX2();
        return new Ciudad(2, producirX2, jugador);
    }
}
