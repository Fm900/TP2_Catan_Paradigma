package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Jugador;

public class Dados implements FasePrincipal {
    private int dado1;
    private int dado2;
    private Jugador jugador;

    public Dados() {
    }
    @Override
    public void iniciarFase(Jugador jugadorActual) {
        this.jugador = jugadorActual;
        dado1 = (int) (Math.random() * 6) + 1;
        dado2 = (int) (Math.random() * 6) + 1;
    }
    public int getTirada() {
        return dado1 + dado2;
    }
}
