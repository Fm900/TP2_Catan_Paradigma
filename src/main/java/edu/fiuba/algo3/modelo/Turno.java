package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Fase.FasePrincipal;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

import java.util.List;

public class Turno {
    private List<FasePrincipal> fases;
    private Jugador jugadorActual;

    public Turno(List<FasePrincipal> fases, Jugador jugadorActual) {
        this.fases = fases;
        this.jugadorActual = jugadorActual;
    }
    public void iniciarTurno(){
        for(FasePrincipal fase : fases){
            fase.iniciarFase(jugadorActual);
        }
    }
}