package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Randomizador.GenerarRandom;
import edu.fiuba.algo3.modelo.Randomizador.Randomizador;
import edu.fiuba.algo3.modelo.Tablero.Ladron;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;

public class Dados implements FasePrincipal {
    private int dado1;
    private int dado2;
    private Jugador jugador;
    private Randomizador rand = new GenerarRandom();

    @Override
    public void iniciarFase(Jugador jugadorActual) {
        this.jugador = jugadorActual;
        dado1 = (int) (rand.random() * 6) + 1;
        dado2 = (int) (rand.random() * 6) + 1;
    }

    public void tiradaDe7(Terreno  terreno, Jugador jugadorElegido) {
        Ladron.getInstance().moverADestino(jugador, terreno, jugadorElegido);
        Juego.getInstancia().descartarCartasJugadores();
    }

    public void generarRecursos(int tirada){
        Tablero tablero = Juego.getInstancia().getTablero();
        tablero.producirPara(tirada);
    }

    public int getTirada() {
        return dado1 + dado2;
    }
}
