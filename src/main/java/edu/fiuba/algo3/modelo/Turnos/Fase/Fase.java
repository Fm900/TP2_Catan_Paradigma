package edu.fiuba.algo3.modelo.Turnos.Fase;

import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Turnos.Normal;

public interface Fase {
    void ejecutar(Jugador jugador, ManejoTurnos manejador);
}
