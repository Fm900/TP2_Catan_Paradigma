package edu.fiuba.algo3.modelo.Jugador.Cartas;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Mineral;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;

import java.util.List;

public class ConstruccionCarreteras extends Carta {

    private Arista arista;

    public ConstruccionCarreteras(ActivarEfecto activacion) {
        super((List.of(new Lana(), new Grano(), new Mineral())));
    }


    public void usar(Jugador jugador) {

    }

    public void setArista (Arista arista) {
        this.arista = arista;
    }

}

