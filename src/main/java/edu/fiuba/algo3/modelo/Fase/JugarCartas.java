package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Jugador.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

public class JugarCartas implements FasePrincipal {

    public void iniciarFase( Jugador jugadorActual) {
    }
    public void iniciarFase(Jugador jugadorActual, Carta cartaActual) {
        if (!jugadorActual.tieneCartasParaJugar()){
            System.out.println("El jugador no tiene cartas para jugar");
            return;
        }
        if (cartaActual != null) {
            System.out.println("El jugador no va a jugar cartas");
            return;
        }
        System.out.println("Se juega la carta");
        cartaActual.activarEfecto(jugadorActual);
        jugadorActual.removerCarta( cartaActual );
    }
}
