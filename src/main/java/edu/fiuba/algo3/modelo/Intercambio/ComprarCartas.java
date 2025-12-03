package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

public class ComprarCartas implements Intercambio {

    private final Jugador jugador;
    private final Carta carta;

    public ComprarCartas(Jugador jugador, Carta carta) {
        this.jugador = jugador;
        this.carta = carta;
    }

    @Override
    public void intercambio() {
        Banca.getInstance().eliminarCarta(carta);
        carta.agregarse(jugador);
        Banca.getInstance().agregarRecursoCarta(carta);
    }
}