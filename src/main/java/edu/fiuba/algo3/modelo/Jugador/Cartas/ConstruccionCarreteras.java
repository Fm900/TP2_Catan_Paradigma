package edu.fiuba.algo3.modelo.Jugador.Cartas;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Mineral;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

public class ConstruccionCarreteras extends Carta {


    public ConstruccionCarreteras(Efecto activacion) {
        super((List.of(new Lana(), new Grano(), new Mineral())));
    }

    public void activarEfecto(Jugador jugador, ParametrosCarta parametros) {
        List<Arista>  aristas = parametros.getAristas();
        Tablero tablero = Juego.getInstancia().getTablero();

        for(Arista arista: aristas){
            tablero.colocarCarretera(jugador ,arista);
        }
    }
}

