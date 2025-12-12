package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

public class ComprarCartas implements Intercambio {

    private final Jugador jugador;

    public ComprarCartas(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public void intercambio() {
        Carta carta = Banca.getInstance().getPrimerCarta();
        carta.agregarse(jugador);
        Banca.getInstance().agregarRecursoCarta(carta);
        Banca.getInstance().eliminarCarta(carta);
    }
}