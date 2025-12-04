package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Exception.ElJuegoNoHaSidoCreadoAun;
import edu.fiuba.algo3.modelo.Fase.*;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import java.util.List;

public class Juego {

    private static Juego juego;

    private final List<Jugador> jugadores;
    private final List<FasePrincipal> fasesPrincipales;
    private final List<FaseInicial> fasesIniciales;
    private final Tablero tablero;
    private final Banca banca;

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
            throw new ElJuegoNoHaSidoCreadoAun("El juego aún no fue inicializado.");
        }
        return juego;
    }


    public void iniciarTurnoUno(Jugador jugador, Vertice vertice, Arista arista){
        fasesIniciales.get(0).iniciarFase(jugador, vertice, arista);
    }


    public void iniciarTurnoDos(Jugador jugador, Vertice vertice, Arista arista){
        fasesIniciales.get(1).iniciarFase(jugador, vertice, arista);
    }

    public void iniciarTurno(){
        for(Jugador jugador : jugadores){
        Turno turno = new Turno(fasesPrincipales, jugador);
        turno.iniciarTurno();
        }
    }

    public int calcularPuntosTotalesDe(Jugador jugador){
        int puntos = jugador.calcularPuntosTotales();
        return puntos;
    }
    public void chequearVictoria(Jugador jugador){
        if (jugador.calcularPuntosTotales() >= 10){
            this.finalizarJuego();
        }
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

    public void finalizarJuego(){
        //por ahora no hace nada
    }

    public static void reset(){
        juego = null;
    }
}
