package edu.fiuba.algo3.modelo.Jugador.Cartas;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Mineral;
import edu.fiuba.algo3.modelo.Tablero.Ladron;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;

import java.util.List;

public class Caballero extends Carta {

    public Caballero(Efecto activacion){
        super((List.of(new Lana(), new Grano(), new Mineral())));
    }

    @Override
    public void activarEfecto(Jugador jugador, ParametrosCarta parametros){
        Terreno terreno = parametros.getTerrenoDestino();
        Jugador victima = parametros.getVictima();

        Ladron.getInstance().moverADestino(jugador, terreno, victima);
        jugador.agregarCaballero();
        Juego.getInstancia().calcularMayorCaballeria(jugador);
        jugador.descartarCarta(this);
    }
}