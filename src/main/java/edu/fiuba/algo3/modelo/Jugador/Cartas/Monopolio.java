package edu.fiuba.algo3.modelo.Jugador.Cartas;

import edu.fiuba.algo3.modelo.Intercambio.EntreJugadoresTodosLosJugadoresAUno;
import edu.fiuba.algo3.modelo.Intercambio.Intercambio;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Mineral;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public class Monopolio extends Carta {

    EntreJugadoresTodosLosJugadoresAUno intercambio;

    public Monopolio(Efecto activacion){
        super((List.of(new Lana(), new Grano(), new Mineral())));
    }

    public void activarEfecto(Jugador jugador, ParametrosCarta parametros){
        Recurso recursoPedido = parametros.getRecursoMonopolio();
        intercambio = new EntreJugadoresTodosLosJugadoresAUno(jugador, Juego.getInstancia().getJugadores(), recursoPedido);
        intercambio.intercambio();
        jugador.descartarCarta(this);
    }
}
