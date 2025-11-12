package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Fase.*;


import java.util.List;

public class Juego{
    private final List<Jugador> jugadores;
    private final List<FasePrincipal> fasesPrincipales;
    private final List<FaseInicial> fasesIniciales;

    public Juego(List<Jugador> jugadores, List<FasePrincipal> fasesPrincipales,  List<FaseInicial> fasesIniciales){
        this.jugadores = jugadores;
        this.fasesPrincipales = fasesPrincipales;
        this.fasesIniciales = fasesIniciales;
    }

    public void iniciarJuego(){
        iniciarFaseInicial();
        iniciarTurno();
    }

    public void iniciarFaseInicial(){
        for (FaseInicial faseInicial : fasesIniciales){
            faseInicial.iniciarFase(jugadores);
        }
    }

    public void iniciarTurno(){
        for(Jugador jugador : jugadores){
        Turno turno = new Turno(fasesPrincipales, jugador);
        turno.iniciarTurno();
        }
    }

}
