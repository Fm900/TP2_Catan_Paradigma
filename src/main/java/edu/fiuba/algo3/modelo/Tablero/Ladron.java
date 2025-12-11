package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Exception.ElLadronNoHaSidoCreadoAun;
import edu.fiuba.algo3.modelo.Exception.MovimientoInvalido;
import edu.fiuba.algo3.modelo.Intercambio.Intercambio;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Intercambio.EntreJugadoresAleatoreo;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;

public class Ladron {
    Terreno terrenoActual;

    private static Ladron ladron;

    private Ladron(Terreno terreno){
        this.terrenoActual = terreno;
    }

    public static Ladron crearLadron(Terreno terreno) {
        if (ladron == null) {
            ladron = new Ladron(terreno);
        }
        return ladron;
    }


    public static Ladron getInstance(){
        if(ladron == null){
            throw new ElLadronNoHaSidoCreadoAun("El Ladron no se ha inicializado.");
        }
        return ladron;
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
}