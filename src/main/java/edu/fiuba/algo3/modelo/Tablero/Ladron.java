package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Exception.MovimientoInvalido;
import edu.fiuba.algo3.modelo.Intercambio.Intercambiar;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Intercambio.Obligado;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;

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
        Intercambiar intercambiador = new Obligado(jugadorTurno, victima);
        intercambiador.intercambio();
    }
}
