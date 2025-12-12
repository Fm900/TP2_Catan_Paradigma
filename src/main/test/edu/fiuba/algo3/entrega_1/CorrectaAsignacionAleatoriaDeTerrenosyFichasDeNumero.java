package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Constructores.GeneradorDeTerrenos;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CorrectaAsignacionAleatoriaDeTerrenosyFichasDeNumero {

    @Test
    void Testo01AlGenerarTerrenosSeCreanDiecinueveHexagonos() {
        GeneradorDeTerrenos generador = new GeneradorDeTerrenos();

        List<Terreno> terrenos = generador.generar();

        assertEquals(19, terrenos.size());
    }

    @Test
    void Test02LasFichasDeNumeroTienenLaDistribucionCorrecta() {
        GeneradorDeTerrenos generador = new GeneradorDeTerrenos();
        List<Terreno> terrenos = generador.generar();

        //cuantos terrenos responden a cada número 2..12
        int[] conteo = new int[13]; // índice = número de ficha

        for (Terreno t : terrenos) {
            for (int valor = 2; valor <= 12; valor++) {
                if (t.correspondeA(valor)) {
                    conteo[valor]++;
                }
            }
        }

        assertEquals(1, conteo[2]);
        assertEquals(2, conteo[3]);
        assertEquals(2, conteo[4]);
        assertEquals(2, conteo[5]);
        assertEquals(2, conteo[6]);
        assertEquals(2, conteo[8]);
        assertEquals(2, conteo[9]);
        assertEquals(2, conteo[10]);
        assertEquals(2, conteo[11]);
        assertEquals(1, conteo[12]);
    }

    @Test
    void Test03NingunTerrenoTieneFichaSiete() {
        GeneradorDeTerrenos generador = new GeneradorDeTerrenos();
        List<Terreno> terrenos = generador.generar();

        boolean algunoConSiete = terrenos.stream().anyMatch(terreno -> terreno.correspondeA(7));

        assertFalse(algunoConSiete);
    }

    @Test
    void HayExactamenteUnTerrenoSinNumeroDeProduccionQueEsDesierto() {
        GeneradorDeTerrenos generador = new GeneradorDeTerrenos();
        List<Terreno> terrenos = generador.generar();

        int terrenosQueProducen = 0;

        for (Terreno t : terrenos) {
            boolean produceConAlgunaTirada = false;
            for (int valor = 2; valor <= 12; valor++) {
                if (t.correspondeA(valor)) {
                    produceConAlgunaTirada = true;
                    break;
                }
            }
            if (produceConAlgunaTirada) {
                terrenosQueProducen++;
            }
        }

        assertEquals(18, terrenosQueProducen);
        assertEquals(1, terrenos.size() - terrenosQueProducen);
    }
}
