package edu.fiuba.algo3.modelo.Turnos.Fase;

import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Cartas.ParametrosCarta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

public class JugarCartas implements Fase {
    private Jugador jugadorActual;

    @Override
    public void ejecutar(Jugador jugador, ManejoTurnos manejador) {
        this.jugadorActual = jugador;
        if (!jugador.tieneCartasDesarrollo()) {
            manejador.pasarSiguienteFase();
        }
    }

    public void jugarCarta(Carta carta, ParametrosCarta parametros) {
        validarFaseIniciada();
        carta.intentarActivarEfecto(jugadorActual, parametros);

        jugadorActual.descartarCarta(carta);
    }

    public void terminarFase(ManejoTurnos manejador) {
        manejador.pasarSiguienteFase();
    }

    public void saltarFase(ManejoTurnos manejador) {
        manejador.pasarSiguienteFase();
    }

    private void validarFaseIniciada() {
        if (jugadorActual == null) {
            throw new IllegalStateException("La fase de jugar cartas no ha sido inicializada");
        }
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }
}