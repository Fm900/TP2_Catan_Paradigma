package edu.fiuba.algo3.modelo.Turnos.Fase;

import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Cartas.ParametrosCarta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

public class JugarCartas implements Fase {
    private Jugador jugadorActual;

    @Override
    public void ejecutar(Jugador jugador, ManejoTurnos manejador) {
        // Inicializar la fase
        this.jugadorActual = jugador;


        // Si el jugador no tiene cartas jugables, avanza automáticamente
        if (!jugador.tieneCartasDesarrollo()) {
            manejador.pasarSiguienteFase();
        }
    }


    public void jugarCarta(Carta carta, ParametrosCarta parametros) {
//        validarFaseIniciada();
//
//        // Verificar que la carta se puede jugar (no fue comprada este turno)
//        if (!carta.sePuedeJugar()) {
//            throw new IllegalStateException("Esta carta fue comprada este turno y no se puede jugar aún");
//        }
//
//        // Activar el efecto de la carta
//        carta.intentarActivarEfecto(jugadorActual, parametros);
//
//        // Remover la carta del mazo del jugador (se usa)
//        jugadorActual.descartarCarta(carta);
//
//        //validar pv
//        if (carta.esPuntoVictoria()) {
//            jugadorActual.agregarCarta(carta);
//        }
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