package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Exception.NoSePuedeMejorarACiudad;
import edu.fiuba.algo3.modelo.Exception.VerticeOcupadoNoPuedeConstruir;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Ocupado;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class OcupadoTest {
    @Test
    void ConstruirPobladoLanzaExcepcionPorqueElVerticeEstaOcupado() {
        Construccion construccion = mock(Construccion.class);
        Ocupado ocupado = new Ocupado(construccion);

        Vertice self = mock(Vertice.class);
        Jugador jugador = mock(Jugador.class);

        assertThrows(VerticeOcupadoNoPuedeConstruir.class, () -> ocupado.construirPoblado(self, jugador));
    }

    @Test
    void EntregarRecursosPorConstruccionDelegaEnLaConstruccion() {
        Construccion construccion = mock(Construccion.class);
        Ocupado ocupado = new Ocupado(construccion);
        Recurso recurso = mock(Recurso.class);

        ocupado.entregarRecursosPorConstruccion(recurso);

        verify(construccion).producirRecurso(recurso);
    }

    @Test
    void ValidarConstruccionEnVecinoDevuelveFalsePorqueBloquea() {
        Construccion construccion = mock(Construccion.class);
        Ocupado ocupado = new Ocupado(construccion);

        assertFalse(ocupado.validarConstruccionEnVecino());
    }

    @Test
    void MejorarPobladoACiudadLanzaExcepcionSiJugadorNoEsDueno() {
        Jugador dueño = mock(Jugador.class);
        Jugador otroJugador = mock(Jugador.class);
        Construccion construccion = mock(Construccion.class);
        when(construccion.getDueño()).thenReturn(dueño);

        Ocupado ocupado = new Ocupado(construccion);
        Vertice self = mock(Vertice.class);

        assertThrows(NoSePuedeMejorarACiudad.class, () -> ocupado.mejorarPobladoACiudad(self, otroJugador));
    }

    @Test
    void MejorarPobladoACiudadCreaCiudadYConsumeRecursosSiJugadorEsDueño() {
        Jugador dueño = mock(Jugador.class);
        Construccion construccionInicial = mock(Construccion.class);
        when(construccionInicial.getDueño()).thenReturn(dueño);
        Ocupado ocupado = new Ocupado(construccionInicial);
        Vertice self = mock(Vertice.class);

        ocupado.mejorarPobladoACiudad(self, dueño);

        // Ciudad.construir() debe llamar a consumirRecursos del dueño
        verify(dueño).consumirRecursos(anyList());
    }
}
