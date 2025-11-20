package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Terreno.EstadoProductivo;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TerrenoTest {

    @Test
    void Test01ProducirSiCorrespondeLlamaAlEstadoSiCoincideConLaTirada(){
        EstadoProductivo estadoMock = mock(EstadoProductivo.class);
        Recurso recursoMock = mock(Recurso.class);
        Vertice v1 = mock(Vertice.class);

        Terreno terreno = new Terreno(recursoMock, 8, estadoMock);

        terreno.asignarVerticesAdyacentes(List.of(v1));
        terreno.producirSiCorresponde(8);

        verify(estadoMock).producir(recursoMock, List.of(v1));
    }

    @Test
    void Test02producirSiCorrespondeNoLlamaAlEstadoCuandoLaTiradaNoCoincide() {
        EstadoProductivo estadoMock = mock(EstadoProductivo.class);
        Recurso recursoMock = mock(Recurso.class);
        Vertice v1 = mock(Vertice.class);

        Terreno terreno = new Terreno(recursoMock, 8, estadoMock);
        terreno.asignarVerticesAdyacentes(List.of(v1));

        terreno.producirSiCorresponde(5);

        verify(estadoMock, never()).producir(any(), any());
    }

    @Test
    void Test03TieneVerticeDevuelveTrueCuandoEstaEnLaLista() {
        Vertice v = mock(Vertice.class);

        Terreno terreno = new Terreno(mock(Recurso.class), 4, mock(EstadoProductivo.class));
        terreno.asignarVerticesAdyacentes(List.of(v));

        assertTrue(terreno.tieneVertice(v));
    }

    @Test
    void Test04TieneVerticeDevuelveFalseCuandoNoEstaEnLaLista() {
        Vertice v1 = mock(Vertice.class);
        Vertice v2 = mock(Vertice.class);

        Terreno terreno = new Terreno(mock(Recurso.class), 4, mock(EstadoProductivo.class));
        terreno.asignarVerticesAdyacentes(List.of(v1));

        assertFalse(terreno.tieneVertice(v2));
    }
}