package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Construccion.Ciudad;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CiudadTest {

    @Test
    void construir_haceQueElJugadorConsumaRecursos() {
        Jugador jugador = mock(Jugador.class);
        Ciudad ciudad = new Ciudad(2, 2, jugador);

        ciudad.construir();

        verify(jugador).consumirRecursos(anyList());
    }
}
