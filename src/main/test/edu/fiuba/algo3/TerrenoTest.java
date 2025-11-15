package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TerrenoTest {


    @Test
    void Test01producirSiCorrespondeLlamaAEstadoProductivoCuandoLaTiradaCoincide() {
        EstadoProductivo estado = mock(EstadoProductivo.class);
        Terreno terreno = new Terreno("madera", 5, estado);

        Vertice v1 = mock(Vertice.class);
        Vertice v2 = mock(Vertice.class);
        List<Vertice> vertices = List.of(v1, v2);
        terreno.asignarVerticesAdyacentes(vertices);

        terreno.producirSiCorresponde(5);

        verify(estado, times(1)).producir("madera", vertices);
    }

    @Test
    void Test02producirSiCorrespondeNoLlamaAEstadoProductivoCuandoLaTiradaNoCoincide() {
        EstadoProductivo estado = mock(EstadoProductivo.class);
        Terreno terreno = new Terreno("madera", 5, estado);

        Vertice v1 = mock(Vertice.class);
        terreno.asignarVerticesAdyacentes(List.of(v1));

        terreno.producirSiCorresponde(4); //no coincide

        verify(estado, never()).producir(anyString(), anyList());
    }

    @Test
    public void Test03terrenoConLadronNoProduce() {
        EstadoProductivo estado = new Alterado();
        Terreno terreno = new Terreno("madera", 5, estado);

        Vertice v1 = mock(Vertice.class);
        terreno.asignarVerticesAdyacentes(List.of(v1));

        terreno.producirSiCorresponde(5);

        verify(v1, never()).entregarRecursosPorConstruccion(any());
    }

    @Test
    void Test04correspondeADevuelveTrueCuandoElNumeroDeFichaCoincide() {
        Terreno terreno = new Terreno("madera", 5, mock(EstadoProductivo.class));

        assertTrue(terreno.correspondeA(5));
    }

    @Test
    void Test05correspondeADevuelveFalseCuandoElNumeroDeFichaNoCoincide() {
        Terreno terreno = new Terreno("madera", 5, mock(EstadoProductivo.class));

        assertFalse(terreno.correspondeA(8));
    }

    @Test
    void Test06cambiarEstadoReemplazaElEstadoProductivoPorElNuevo() {
        EstadoProductivo estadoOriginal = mock(EstadoProductivo.class);
        EstadoProductivo estadoNuevo = mock(EstadoProductivo.class);

        //cambio el estado
        when(estadoOriginal.alterarEstado()).thenReturn(estadoNuevo);

        Terreno terreno = new Terreno("madera", 5, estadoOriginal);
        terreno.cambiarEstado();

        Vertice v1 = mock(Vertice.class);
        List<Vertice> vertices = List.of(v1);
        terreno.asignarVerticesAdyacentes(vertices);

        // ahora producimos con la tirada que coincide
        terreno.producirSiCorresponde(5);

        // Se debe usar el nuevo estado para producir
        verify(estadoNuevo, times(1)).producir("madera", vertices);
    }

    @Test
    void Test07obtenerHabitantesDevuelveListaVaciaSiNoHayVerticesAsignados() {
        Terreno terreno = new Terreno("madera", 5, mock(EstadoProductivo.class));

        List<Jugador> habitantes = terreno.obtenerHabitantes();

        assertTrue(habitantes.isEmpty());
    }


    @Test
    void Test08tieneVerticeDevuelveTrueSiElVerticeEstaEnLaLista() {
        Terreno terreno = new Terreno("madera", 5, mock(EstadoProductivo.class));

        Vertice v1 = mock(Vertice.class);
        Vertice v2 = mock(Vertice.class);
        terreno.asignarVerticesAdyacentes(List.of(v1, v2));

        assertTrue(terreno.tieneVertice(v1));
    }

    @Test
    void Test09tieneVerticeDevuelveFalseSiElVerticeNoEstaEnLaLista() {
        Terreno terreno = new Terreno("madera", 5, mock(EstadoProductivo.class));

        Vertice v1 = mock(Vertice.class);
        Vertice v2 = mock(Vertice.class);
        Vertice otro = mock(Vertice.class);
        terreno.asignarVerticesAdyacentes(List.of(v1, v2));

        assertFalse(terreno.tieneVertice(otro));
    }

    @Test
    void Test10tieneVerticeDevuelveFalseSiNoHayVerticesAsignados() {
        Terreno terreno = new Terreno("madera", 5, mock(EstadoProductivo.class));
        Vertice v = mock(Vertice.class);

        assertFalse(terreno.tieneVertice(v));
    }
}
