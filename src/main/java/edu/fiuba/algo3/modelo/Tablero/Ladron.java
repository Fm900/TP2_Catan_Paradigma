package edu.fiuba.algo3.modelo.Tablero;

import java.util.List;
import java.util.ArrayList;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Intercambiar;
import edu.fiuba.algo3.modelo.Obligado;
import edu.fiuba.algo3.modelo.Excepciones.*;

public class Ladron {
    Terreno terrenoActual;

    public Ladron(Terreno terreno){
        this.terrenoActual = terreno;
    }

    public void moverADestino(Jugador jugadorTurno, Terreno terreno){
        if (terrenoActual == terreno) { throw new MovimientoInvalido("El ladron ya esta en ese terreno"); }

        terrenoActual.cambiarEstado();
        this.terrenoActual = terreno;
        this.terrenoActual.cambiarEstado();

        List<Jugador> posiblesVictimas = terrenoActual.obtenerHabitantes();

        if ( posiblesVictimas == null || posiblesVictimas.isEmpty()) { return; }

        posiblesVictimas.remove(jugadorTurno);
        Jugador victima = jugadorTurno.elegirVictima(posiblesVictimas);
        this.robar(jugadorTurno, victima);
    }

    public void robar(Jugador jugadorTurno, Jugador victima) {
        Intercambiar intercambiador = new Obligado(jugadorTurno, victima);
        intercambiador.intercambio();
    }
}
