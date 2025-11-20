package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Construccion.Poblado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PobladoTest {

    @Test
    void Test01construirPobladoCobraRecursosAlJugador() {
        Jugador jugador = mock(Jugador.class);

        Construccion poblado = Poblado.construir(jugador);

        verify(jugador, times(1)).consumirRecursosParaPoblado();
    }

    @Test
    void Test02construirPobladoAsignaAlJugadorComoDueño() {
        Jugador jugador = mock(Jugador.class);

        Construccion construccion = Poblado.construir(jugador);

        assertEquals(jugador, construccion.getDueño());
    }

    @Test
    void mejorarPobladoACiudadCobraRecursosDeCiudad(){
        Jugador jugador = mock(Jugador.class);
        Construccion poblado = Poblado.construir(jugador);
        Construccion ciudad = poblado.mejorarACiudad();

        //se cobra ciudad
        verify(jugador, times(1)).consumirRecursosParaCiudad();
    }

}
