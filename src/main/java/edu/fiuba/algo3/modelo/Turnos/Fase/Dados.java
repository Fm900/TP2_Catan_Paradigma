package edu.fiuba.algo3.modelo.Turnos.Fase;

import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Randomizador.GenerarRandom;
import edu.fiuba.algo3.modelo.Randomizador.Randomizador;
import edu.fiuba.algo3.modelo.Tablero.Ladron;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;


public class Dados implements Fase {

    private final Randomizador rand;
    private int tirada;


    public Dados() {
        this.rand = new GenerarRandom();
    }

    @Override
    public void ejecutar(Jugador jugador, ManejoTurnos manejador) {
        tirarDados();
    }

    private void tirarDados() {
        int dado1 = (int)(rand.random() * 6) + 1;
        int dado2 = (int)(rand.random() * 6) + 1;
        tirada = dado1 + dado2;
    }

    public void producirRecursos(ManejoTurnos manejador) {
        Juego.getInstancia().getTablero().producirPara(tirada);
        manejador.pasarSiguienteFase();
    }

    public int getTirada() {
        return tirada;
    }

    public void moverLadron(Jugador jugador, Terreno terreno, Jugador victima, ManejoTurnos manejador) {
        Juego.getInstancia().descartarCartasJugadores();
        Ladron.getInstance().moverADestino(jugador, terreno, victima);
        manejador.pasarSiguienteFase();
    }
}
