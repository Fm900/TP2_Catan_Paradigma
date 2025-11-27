package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Fase.FasePrincipal;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import java.util.List;

public class Turno {
    private List<FasePrincipal> fases;
    private Jugador jugadorActual;
    private Banca banca;

    public Turno(List<FasePrincipal> fases, Jugador jugadorActual, Banca banca) {
        this.fases = fases;
        this.jugadorActual = jugadorActual;
        this.banca = banca;
    }
    public void iniciarTurno(){
        for(FasePrincipal fase : fases){
            fase.iniciarFase(jugadorActual, banca);
        }
    }
}