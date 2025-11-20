package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Construccion.Poblado;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PobladoTest {

    @Test
    void ConstruirHaceQueElJugadorConsumaRecursos() {
        Jugador jugador = mock(Jugador.class);
        Poblado poblado = new Poblado(1, 1, jugador);

        poblado.construir();

        verify(jugador).consumirRecursos(anyList());
    }
}
