package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.GeneradorDeTerrenos;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class GeneradorDeTerrenosTest {
    @Test
    void Test01generar_devuelve19Terrenos() {
        GeneradorDeTerrenos gen = new GeneradorDeTerrenos();

        List<Terreno> terrenos = gen.generar();

        assertEquals(19, terrenos.size());
    }

    @Test
    void Test02generar_nuncaDevuelveTerrenosNulos() {
        GeneradorDeTerrenos gen = new GeneradorDeTerrenos();

        List<Terreno> terrenos = gen.generar();

        boolean hayNulos = terrenos.stream().anyMatch(Objects::isNull);

        assertFalse(hayNulos);
    }

    @Test
    void Test03hayAlMenosUnTerrenoQueProduceMadera() {
        GeneradorDeTerrenos generador = new GeneradorDeTerrenos();
        List<Terreno> terrenos = generador.generar();
        Vertice verticeMock = mock(Vertice.class);

        for (Terreno t : terrenos) {
            t.asignarVerticesAdyacentes(List.of(verticeMock));
        }

        for (int tirada : List.of(2, 3, 4, 5, 6, 8, 9, 10, 11, 12)) {
            for (Terreno t : terrenos) {
                t.producirSiCorresponde(tirada);
            }
        }

        // Verifico que al menos una vez se produjo Madera
        verify(verticeMock, atLeastOnce()).entregarRecursosPorConstruccion(isA(Madera.class));
    }

    @Test
    void Test04hayAlMenosUnTerrenoQueProduceGrano() {
        GeneradorDeTerrenos generador = new GeneradorDeTerrenos();
        List<Terreno> terrenos = generador.generar();
        Vertice verticeMock = mock(Vertice.class);

        for (Terreno t : terrenos) {
            t.asignarVerticesAdyacentes(List.of(verticeMock));
        }

        for (int tirada : List.of(2, 3, 4, 5, 6, 8, 9, 10, 11, 12)) {
            for (Terreno t : terrenos) {
                t.producirSiCorresponde(tirada);
            }
        }

        verify(verticeMock, atLeastOnce()).entregarRecursosPorConstruccion(isA(Grano.class));
    }

    @Test
    void Test05hayAlMenosUnTerrenoQueProduceLadrillo() {
        GeneradorDeTerrenos generador = new GeneradorDeTerrenos();
        List<Terreno> terrenos = generador.generar();

        Vertice verticeMock = mock(Vertice.class);

        for (Terreno t : terrenos) {
            t.asignarVerticesAdyacentes(List.of(verticeMock));
        }

        for (int tirada : List.of(2, 3, 4, 5, 6, 8, 9, 10, 11, 12)) {
            for (Terreno t : terrenos) {
                t.producirSiCorresponde(tirada);
            }
        }

        verify(verticeMock, atLeastOnce()).entregarRecursosPorConstruccion(isA(Ladrillo.class));
    }

    @Test
    void Test06hayAlMenosUnTerrenoQueProduceMineral() {
        GeneradorDeTerrenos generador = new GeneradorDeTerrenos();
        List<Terreno> terrenos = generador.generar();

        Vertice verticeMock = mock(Vertice.class);

        for (Terreno t : terrenos) {
            t.asignarVerticesAdyacentes(List.of(verticeMock));
        }

        for (int tirada : List.of(2, 3, 4, 5, 6, 8, 9, 10, 11, 12)) {
            for (Terreno t : terrenos) {
                t.producirSiCorresponde(tirada);
            }
        }

        verify(verticeMock, atLeastOnce())
                .entregarRecursosPorConstruccion(isA(Mineral.class));
    }

    @Test
    void Test07hayAlMenosUnTerrenoQueProduceLana() {
        GeneradorDeTerrenos generador = new GeneradorDeTerrenos();
        List<Terreno> terrenos = generador.generar();

        Vertice verticeMock = mock(Vertice.class);

        for (Terreno t : terrenos) {
            t.asignarVerticesAdyacentes(List.of(verticeMock));
        }

        for (int tirada : List.of(2, 3, 4, 5, 6, 8, 9, 10, 11, 12)) {
            for (Terreno t : terrenos) {
                t.producirSiCorresponde(tirada);
            }
        }

        verify(verticeMock, atLeastOnce())
                .entregarRecursosPorConstruccion(isA(Lana.class));
    }

}
