package edu.fiuba.algo3;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;



public class TableroTest {

    @Test
    void Test01AlCrearTableroHay19TerrenosTest(){} {
        Tablero tablero = new Tablero();
        List<Terreno> terrenos = tablero.crearTerrenos();

        Integer tamaño = terrenos.size();

        assertEquals(19, tamaño);
    }

    @Test
    void Test02AlCrearTableroHaySolo1DesiertoPorLoQueNoEntregaRecursosTest(){
        Tablero tablero = new Tablero();
        List<Terreno> terrenos = tablero.crearTerrenos();
        Vertice verticeMock = mock(Vertice.class);

        //le asigno este vertice mock a todos los terrenos
        for (Terreno terreno : terrenos) {
            terreno.asignarVerticesAdyacentes(List.of(verticeMock));
        }

        //simulo todas las tiradas posibles
        for (int tirada : List.of(2,3,4,5,6,8,9,10,11,12)) {
            tablero.producirPara(tirada);
        }
        //Deben haber ocurrido 18 entregas ya que solo hay un terreno que no produce nunca y es el desierto
        verify(verticeMock, times(18)).entregarRecursosPorConstruccion(anyString());
    }

    @Test
    void Test03AlCrearTableroHayAlMenosUnBosqueQueDaMaderaTest(){
        Tablero tablero = new Tablero();
        List<Terreno> terrenos = tablero.crearTerrenos();
        Vertice verticeMock = mock(Vertice.class);

        for (Terreno terreno : terrenos) {
            terreno.asignarVerticesAdyacentes(List.of(verticeMock));
        }

        for (int tirada : List.of(2,3,4,5,6,8,9,10,11,12)) {
            tablero.producirPara(tirada);
        }

        // Al menos un terreno debio producir Madera
        verify(verticeMock, atLeastOnce()).entregarRecursosPorConstruccion("Madera");
    }

    @Test
    void Test04AlCrearTableroHayAlMenosUnaMontañaQueDaMineralTest(){
        Tablero tablero = new Tablero();
        List<Terreno> terrenos = tablero.crearTerrenos();
        Vertice verticeMock = mock(Vertice.class);

        for (Terreno terreno : terrenos) {
            terreno.asignarVerticesAdyacentes(List.of(verticeMock));
        }

        for (int tirada : List.of(2,3,4,5,6,8,9,10,11,12)) {
            tablero.producirPara(tirada);
        }

        verify(verticeMock, atLeastOnce()).entregarRecursosPorConstruccion("Mineral");
    }

    @Test
    void Test05AlCrearTableroHayAlMenosUnaColinaQueDaLadrilloTest(){
        Tablero tablero = new Tablero();
        List<Terreno> terrenos = tablero.crearTerrenos();
        Vertice verticeMock = mock(Vertice.class);

        for (Terreno terreno : terrenos) {
            terreno.asignarVerticesAdyacentes(List.of(verticeMock));
        }

        for (int tirada : List.of(2,3,4,5,6,8,9,10,11,12)) {
            tablero.producirPara(tirada);
        }

        verify(verticeMock, atLeastOnce()).entregarRecursosPorConstruccion("Ladrillo");
    }

    @Test
    void Test06AlCrearTableroHayAlMenosUnPastizalQueDaLanaTest(){
        Tablero tablero = new Tablero();
        List<Terreno> terrenos = tablero.crearTerrenos();

        Vertice verticeMock = mock(Vertice.class);
        for (Terreno terreno : terrenos) {
            terreno.asignarVerticesAdyacentes(List.of(verticeMock));
        }

        for (int tirada : List.of(2,3,4,5,6,8,9,10,11,12)) {
            tablero.producirPara(tirada);
        }

        verify(verticeMock, atLeastOnce()).entregarRecursosPorConstruccion("Lana");
    }

    @Test
    void Test07AlCrearTableroHayAlMenosUnCampoQueDaGranoTest(){
        Tablero tablero = new Tablero();
        List<Terreno> terrenos = tablero.crearTerrenos();

        Vertice verticeMock = mock(Vertice.class);
        for (Terreno terreno : terrenos) {
            terreno.asignarVerticesAdyacentes(List.of(verticeMock));
        }

        for (int tirada : List.of(2,3,4,5,6,8,9,10,11,12)) {
            tablero.producirPara(tirada);
        }

        verify(verticeMock, atLeastOnce()).entregarRecursosPorConstruccion("Grano");
    }

    @Test
    void Test08NoHayNingunHexagonoConFicha7SiSale7Test() {
        Tablero tablero = new Tablero();
        List<Terreno> terrenos = tablero.crearTerrenos();
        Vertice verticeMock = mock(Vertice.class);

        for (Terreno t : terrenos) {
            t.asignarVerticesAdyacentes(List.of(verticeMock));
        }

        // si sale 7 no debe producir nadie
        tablero.producirPara(7);

        verify(verticeMock, never()).entregarRecursosPorConstruccion(anyString());
    }

    @Test
    void Test09ColocarPobladoLlamaAConstruirPobladoEnElVertice() {
        Tablero tablero = new Tablero();
        Jugador jugadorMock = mock(Jugador.class);
        Vertice verticeMock = mock(Vertice.class);

        tablero.colocarPoblado(jugadorMock, verticeMock);

        verify(verticeMock, times(1)).construirPoblado(jugadorMock);
    }

    @Test
    void Test10ObtenerTerrenosAdyacentesDevuelveLosTerrenosQueContienenElVertice() {
        Tablero tablero = new Tablero();

        List<Terreno> terrenos = tablero.crearTerrenos();
        Vertice vertice = mock(Vertice.class);
        terrenos.get(0).asignarVerticesAdyacentes(List.of(vertice));
        List<Terreno> adyacentes = tablero.obtenerTerrenosAdy(vertice);

        assertTrue(adyacentes.contains(terrenos.get(0)));
    }

    @Test
    void Test11ObtenerTerrenosAdyacentesDevuelveNoDevuelveUnVerticeQueNoEsAdyacente() {
        Tablero tablero = new Tablero();
        List<Terreno> terrenos = tablero.crearTerrenos();
        Vertice verticeObjetivo = mock(Vertice.class);
        Vertice otroVertice = mock(Vertice.class);

        terrenos.get(0).asignarVerticesAdyacentes(List.of(verticeObjetivo));// el primer terreno tienne al vertice objetivo
        terrenos.get(1).asignarVerticesAdyacentes(List.of(otroVertice)); // el segundo terreno tiene otro vertice
        List<Terreno> adyacentes = tablero.obtenerTerrenosAdy(verticeObjetivo);

        assertFalse(adyacentes.contains(terrenos.get(1))); //el vertice objetivo no tiene al segundo terreno como adyacente
    }

    @Test
    void Test12ObtenerTerrenosAdyacentesDevuelveListaVaciaSiNingunTerrenoTieneElVertice() {
        Tablero tablero = new Tablero();
        tablero.crearTerrenos();

        Vertice verticeObjetivo = mock(Vertice.class);
        List<Terreno> adyacentes = tablero.obtenerTerrenosAdy(verticeObjetivo);

        assertTrue(adyacentes.isEmpty());
    }

    @Test
    void Test13ObtenerTerrenosAdyacentesDevuelveUnaListaConTodosLosVertices() {
        Tablero tablero = new Tablero();
        List<Terreno> terrenos = tablero.crearTerrenos();

        Vertice verticeCompartido = mock(Vertice.class);

        // 3 terrenos comparten el mismo vertice
        terrenos.get(0).asignarVerticesAdyacentes(List.of(verticeCompartido));
        terrenos.get(1).asignarVerticesAdyacentes(List.of(verticeCompartido));
        terrenos.get(2).asignarVerticesAdyacentes(List.of(verticeCompartido));

        List<Terreno> adyacentes = tablero.obtenerTerrenosAdy(verticeCompartido);

        assertEquals(3, adyacentes.size()); //contiene a los 3 vertices
    }

}
