package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Fase.*;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;


import java.util.List;

public class Juego {

    private static Juego juego;

    private final List<Jugador> jugadores;
    private final List<FasePrincipal> fasesPrincipales;
    private final List<FaseInicial> fasesIniciales;
    private final Tablero tablero;
    private final Banca banca;

    // patron singelton para Juego, implementacion
    private Juego(List<Jugador> jugadores, List<FasePrincipal> fasesPrincipales,  List<FaseInicial> fasesIniciales,  Tablero tablero, Banca banca) {
        this.jugadores = jugadores;
        this.fasesPrincipales = fasesPrincipales;
        this.fasesIniciales = fasesIniciales;
        this.tablero = tablero;
        this.banca = banca;
    }

    public static Juego crearInstancia(List<Jugador> jugadores, List<FasePrincipal> fasesPrincipales, List<FaseInicial> fasesIniciales, Tablero tablero, Banca banca) {
        if(juego == null){
            juego = new Juego(jugadores, fasesPrincipales, fasesIniciales, tablero, banca);
        }

        return juego;
    }

    public static Juego getInstancia() {
        if (juego == null) {
            throw new IllegalStateException("El juego aún no fue inicializado.");
        }
        return juego;
    }

//    public void iniciarJuego(){
//        iniciarFaseInicial();
//        iniciarTurno();
//    }

    public void iniciarFaseInicial(){
        for (FaseInicial faseInicial : fasesIniciales){
            faseInicial.iniciarFase(jugadores, tablero);
        }
    }

    public void iniciarTurno(){
        for(Jugador jugador : jugadores){
        Turno turno = new Turno(fasesPrincipales, jugador, banca);
        turno.iniciarTurno();
        }
    }

    public int calcularPuntosTotalesDe(Jugador jugador){
        int puntos = jugador.calcularPuntosTotales();
        return puntos;
    }
    public List<Jugador> getJugadores() {
        return jugadores;
    }
    public Tablero getTablero() {
        return tablero;
    }
}
