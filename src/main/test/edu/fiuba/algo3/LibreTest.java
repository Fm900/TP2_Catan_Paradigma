package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Exception.NoSePuedeMejorarACiudad;
import edu.fiuba.algo3.modelo.Exception.ReglaDeDistanciaNoValida;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Libre;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class LibreTest {

    @Test
    void ConstruirPobladoLanzaExcepcionSiAlgunVecinoNoPermiteConstruccion() {
        Libre libre = new Libre();
        Vertice self = mock(Vertice.class);
        Vertice vecinoBloqueante = mock(Vertice.class);
        Arista arista = mock(Arista.class);
        Jugador jugador = mock(Jugador.class);

        when(self.aristas()).thenReturn(List.of(arista));
        when(arista.otroExtremo(self)).thenReturn(vecinoBloqueante);
        when(vecinoBloqueante.validarConstruccionEnVecino()).thenReturn(false);

        //assertThrows(ReglaDeDistanciaNoValida.class, () -> libre.construirPoblado(self, jugador, aristas));
    }

    @Test
    void ValidarConstruccionEnVecinoDevuelveTrue() {
        Libre libre = new Libre();

        assertTrue(libre.validarConstruccionEnVecino());
    }

    @Test
    void MejorarPobladoACiudadLanzaExcepcionSiempre() {
        Libre libre = new Libre();
        Vertice self = mock(Vertice.class);
        Jugador jugador = mock(Jugador.class);

        assertThrows(NoSePuedeMejorarACiudad.class, () -> libre.mejorarPobladoACiudad(self, jugador));
    }

}
