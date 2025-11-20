package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Oferta;

public class CC implements FasePrincipal {
    Jugador jugadorActual;
    Oferta ofertaActual;
    public void iniciarFase(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }
    public void crearOferta(){
        this.ofertaActual = new Oferta();
    }

}
