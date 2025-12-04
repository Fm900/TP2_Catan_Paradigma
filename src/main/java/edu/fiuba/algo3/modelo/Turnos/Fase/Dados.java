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

        if (tirada == 7) {
            manejarLadron(jugador, manejador);
        } else {
            producirRecursos(manejador);
        }
    }

    private void tirarDados() {
        int dado1 = (int)(rand.random() * 6) + 1;
        int dado2 = (int)(rand.random() * 6) + 1;
        tirada = dado1 + dado2;
    }

    private void manejarLadron(Jugador jugador, ManejoTurnos manejador) {

        Juego.getInstancia().descartarCartasJugadores();

        // 2) Esperar movimiento del ladrón desde la UI
        // NO avanza automáticamente - espera la interacción del usuario
        manejador.esperarMovimientoLadron(jugador);
    }

    private void producirRecursos(ManejoTurnos manejador) {
        // Producir recursos normalmente
        Juego.getInstancia().getTablero().producirPara(tirada);

        // Pasar a la siguiente fase automáticamente
        manejador.pasarSiguienteFase();
    }

    public int getTirada() {
        return tirada;
    }
    //se completa mov
    public void moverLadron(Jugador jugador, Terreno terreno, Jugador robado, ManejoTurnos manejador) {
        Ladron.getInstance().moverADestino(jugador, terreno, robado);

        // Ahora sí avanzar a la siguiente fase
        manejador.pasarSiguienteFase();
    }
}
