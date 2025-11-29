package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Randomizador.GenerarRandom;
import edu.fiuba.algo3.modelo.Randomizador.Randomizador;

public class Dados implements FasePrincipal {
    private int dado1;
    private int dado2;
    private Jugador jugador;
    private Randomizador rand = new GenerarRandom();

    public Dados() {
    }
    @Override
    public void iniciarFase(Jugador jugadorActual, Banca banca) {
        this.jugador = jugadorActual;
        dado1 = (int) (rand.random() * 6) + 1;
        dado2 = (int) (rand.random() * 6) + 1;
    }
    public int getTirada() {
        return dado1 + dado2;
    }
}
