package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Exception.ElJuegoNoHaSidoCreadoAun;
import edu.fiuba.algo3.modelo.Turnos.*;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import edu.fiuba.algo3.modelo.Turnos.Fase.Fase;

import java.util.List;

public class Juego {

    private static Juego juego;

    private final List<Jugador> jugadores;
    private final Tablero tablero;
    private final Banca banca;

    private Juego(List<Jugador> jugadores, Tablero tablero, Banca banca) {
        this.jugadores = jugadores;
        this.tablero = tablero;
        this.banca = banca;
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

    public int calcularPuntosTotalesDe(Jugador jugador){
        int puntos = jugador.calcularPuntosTotales();
        return puntos;
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
}
