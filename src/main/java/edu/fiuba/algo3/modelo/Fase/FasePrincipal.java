package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public interface FasePrincipal {
    void iniciarFase(Jugador jugadorActual, Banca banca);
}
