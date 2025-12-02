package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Exception.MovimientoInvalido;
import edu.fiuba.algo3.modelo.Intercambio.EntreJugadores;
import edu.fiuba.algo3.modelo.Intercambio.Intercambio;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Intercambio.EntreJugadoresAleatoreo;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;

import java.util.ArrayList;
import java.util.List;

public class Ladron {
    Terreno terrenoActual;

    public Ladron(Terreno terreno){
        this.terrenoActual = terreno;
    }

    public void moverADestino(Jugador jugadorTurno, Terreno terreno, Jugador victima){
        if (terrenoActual == terreno) { throw new MovimientoInvalido("El ladron ya esta en ese terreno"); }

        terrenoActual.cambiarEstado();
        this.terrenoActual = terreno;
        this.terrenoActual.cambiarEstado();

        this.robar(jugadorTurno, victima);
    }

    public void robar(Jugador jugadorTurno, Jugador victima) {
        Intercambio intercambiador = new EntreJugadoresAleatoreo(jugadorTurno, victima);
        intercambiador.intercambio();
    }

    public void robarAleatoreo(Jugador jugadorTurno, Jugador victima) {
        //Recurso recurso = victima.obtenerRecursoAleatorio();
        //List<Recurso> recursosARobar = new ArrayList<>(List.of(recurso));
        //List<Recurso> recursos = new ArrayList<>();
        //Intercambio intermcabio = new EntreJugadores(jugadorTurno, victima, recursos, List<Recurso> recursosARobar);
        //intermcabio.intercambio();
    }

    public void robarEspecifico(Jugador jugadorTurno, Jugador victima) {
        //Recurso recurso = jugadorTurno.obtenerRecursoARobar();
        //List<Recurso> recursosARobar = new ArrayList<>(List.of(recurso));
        //List<Recurso> recursos = new ArrayList<>();
        //Intercambio intermcabio = new EntreJugadores(jugadorTurno, victima, recursos, List<Recurso> recursosARobar);
        //intermcabio.intercambio();
    }
}
