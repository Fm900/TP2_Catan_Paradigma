package edu.fiuba.algo3.modelo.Jugador;

import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;

import java.util.List;

public class Mano {

    private List<Carta> cartas;

    public void agregar(Carta carta) {
        this.cartas.add(carta);
    }

    public void descartar(Carta carta){
        this.cartas.remove(carta);
    }

}
