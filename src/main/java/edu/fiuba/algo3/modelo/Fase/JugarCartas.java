package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Cartas.ParametrosCarta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

public class JugarCartas implements FasePrincipal {
    private Jugador jugadorActual;

    public void iniciarFase(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public void jugarCarta(Carta carta, ParametrosCarta parametros) {
        carta.intentarActivarEfecto(jugadorActual, parametros);
        jugadorActual.agregarCarta(carta);
    }
}