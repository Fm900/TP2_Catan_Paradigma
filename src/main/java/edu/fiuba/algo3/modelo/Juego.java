package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Fase.*;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;


import java.util.List;

public class Juego {
    private final List<Jugador> jugadores;
    private final List<FasePrincipal> fasesPrincipales;
    private final List<FaseInicial> fasesIniciales;
    private final Tablero tablero;

    public Juego(List<Jugador> jugadores, List<FasePrincipal> fasesPrincipales,  List<FaseInicial> fasesIniciales,  Tablero tablero) {
        this.jugadores = jugadores;
        this.fasesPrincipales = fasesPrincipales;
        this.fasesIniciales = fasesIniciales;
        this.tablero = tablero;
    }

    public void iniciarJuego(){
        iniciarFaseInicial();
        iniciarTurno();
    }

    public void iniciarFaseInicial(){
        for (FaseInicial faseInicial : fasesIniciales){
            faseInicial.iniciarFase(jugadores, tablero);
        }
    }

    public void iniciarTurno(){
        for(Jugador jugador : jugadores){
        Turno turno = new Turno(fasesPrincipales, jugador);
        turno.iniciarTurno();
        }
    }

    public void descarteJugadores() {
        for (Jugador jugador : jugadores) {
            jugador.descarteMayoria();
        }
    }

    public int calcularPuntosTotalesDe(Jugador jugador){
        int puntos = jugador.calcularPuntosTotales();
        return puntos;
    }
}
