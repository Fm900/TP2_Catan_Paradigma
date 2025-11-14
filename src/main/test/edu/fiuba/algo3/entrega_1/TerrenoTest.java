package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Tablero.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class TerrenoTest {

    Ladron ladron;
    Terreno terreno;
    EstadoProductivo estadoProductivo;

    @Test
    public void terrenoConLadronNoProduce() {
        EstadoProductivo estado = new Alterado();
        Terreno terreno = new Terreno("madera", 5, estado);

        Vertice v1 = mock(Vertice.class);
        terreno.asignarVerticesAdyacentes(List.of(v1));

        terreno.producirSiCorresponde(5);

        verify(v1, never()).entregarRecursosPorConstruccion(any());
    }

}
