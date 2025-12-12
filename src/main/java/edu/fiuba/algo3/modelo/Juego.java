package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Exception.ElJuegoNoHaSidoCreadoAun;
import edu.fiuba.algo3.modelo.Jugador.CalculadorDePuntosJugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Tasa;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import java.util.List;

public class Juego {

    private static Juego juego;
    private CalculadorDePuntosJugador calculador;
    private final List<Jugador> jugadores;
    private final Tablero tablero;
    private final Banca banca;

    private Juego(List<Jugador> jugadores, Tablero tablero, Banca banca) {
        this.jugadores = jugadores;
        this.tablero = tablero;
        this.banca = banca;
        this.calculador = new CalculadorDePuntosJugador(this.tablero);
    }

    public static Juego crearInstancia(List<Jugador> jugadores, Tablero tablero, Banca banca) {
        if(juego == null){
            juego = new Juego(jugadores, tablero, banca);
        }
        return juego;
    }

    public static Juego getInstancia() {
        if (juego == null) {
            throw new ElJuegoNoHaSidoCreadoAun("El juego aún no fue inicializado.");
        }
        return juego;
    }

    public static void reset(){
        juego = null;
    }

    public int calcularPuntosTotalesDe(Jugador jugador){
        return calculador.calcular(jugador);
    }

    public void calcularCaminoMasLargo(Jugador jugador) {
        calculador.calcularCaminoMasLargo(jugador);
    }

    public void calcularMayorCaballeria(Jugador jugador) {
        calculador.calcularMayorCaballeria(jugador);
    }

    public boolean chequearVictoria(Jugador jugador){
        return calcularPuntosTotalesDe(jugador) >= 10;
    }

    public List<Jugador> getJugadores() {
        return this.jugadores;
    }

    public  Tablero getTablero() {
        return tablero;
    }

    public void descartarCartasJugadores(){
        for (Jugador jugador : jugadores) {
            jugador.descarteMayoria();
        }
    }
    public List<Tasa> getTasas(Jugador jugador){
        return tablero.dameLasTasasDe(jugador);
    }
}
