package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Tablero.Ladron;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class LadronTest {

    Terreno terrenoActual;
    Terreno terrenoDestino;
    Ladron ladron;

    @BeforeEach
    public void setUp() {
        terrenoActual = mock(Terreno.class);
        terrenoDestino = mock(Terreno.class);
    }

    @Test
    public void test01LadronSeMueveYLosTerrenosCambianEstado() {

        ladron = new Ladron(terrenoActual);

        ladron.moverADestino(terrenoDestino);

        // Verificar que el terreno actual cambia su estado productivo
        verify(terrenoActual, times(1)).cambiarEstado();

        // Verificar que el terreno destino también cambia su estado productivo
        verify(terrenoDestino, times(1)).cambiarEstado();
    }
}
