package edu.fiuba.algo3.modelo.Jugador;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Tablero.Tablero;


public class CalculadorDePuntosJugador {

    private Jugador jugadorCaminoMasLargo;
    private Jugador jugadorMayorCaballeria;
    private int longitudMasLargoActual;
    private int mayorCantidadCaballeros;
    private final Tablero tablero;
    private final int coeficienteDeSumaCartasEspeciales;

    public CalculadorDePuntosJugador(Tablero tablero) {
        this.tablero = tablero;
        this.longitudMasLargoActual= 0;
        this.mayorCantidadCaballeros = 0;
        this.coeficienteDeSumaCartasEspeciales = 2;
    }

    public int calcular(Jugador jugador) {
        int puntosJugador = jugador.calcularPuntosTotales();
        if ( jugador == jugadorCaminoMasLargo ) {
            puntosJugador += coeficienteDeSumaCartasEspeciales;
        }
        if ( jugador == jugadorMayorCaballeria ) {
            puntosJugador += coeficienteDeSumaCartasEspeciales;
        }
        return puntosJugador;
    }


    public void calcularCaminoMasLargo(Jugador jugador) {
        int nuevaLongitud = tablero.calcularCaminoMasLargo(jugador);
        if (nuevaLongitud >= 5 && nuevaLongitud > longitudMasLargoActual) {
            jugadorCaminoMasLargo = jugador;
            longitudMasLargoActual = nuevaLongitud;
        }
    }

    public void calcularMayorCaballeria(Jugador jugador) {
        int nuevaCantidadCaballeros = jugador.obtenerCantidadCaballeros();
        if (nuevaCantidadCaballeros >= 3 && nuevaCantidadCaballeros > mayorCantidadCaballeros) {
            this.jugadorMayorCaballeria = jugador;
            this.mayorCantidadCaballeros = nuevaCantidadCaballeros;
        }
    }
}
