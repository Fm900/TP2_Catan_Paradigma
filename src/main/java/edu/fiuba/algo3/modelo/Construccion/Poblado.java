package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Jugador;

public class Poblado extends Construccion {

    public Poblado(Integer puntosDeVictoria, Producir producir, Jugador dueño) {
        super(puntosDeVictoria, producir, dueño);
    }



    public static Construccion construir(Jugador jugador){
        jugador.consumirRecursosParaPoblado();

        ProducirX1 producirX1 = new ProducirX1();
        Construccion poblado = new Poblado (1, producirX1, jugador);
        return poblado;
    }
}
