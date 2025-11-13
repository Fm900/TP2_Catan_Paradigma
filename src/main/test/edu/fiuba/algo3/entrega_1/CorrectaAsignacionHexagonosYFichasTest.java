package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;



public class CorrectaAsignacionHexagonosYFichasTest {

    /*Para verificar la Correcta Asignacion aleatoria de hexagonos y fichas testeo que se creen 19 hexagonos,
    * de los cuales uno debe ser desierto(por lo que no produce) y que haya al menos un terreno que produzca
    * cada tipo de recurso (Montañan, Pastizal, Campo, Colina y Bosque), para cada test se simulan todas las tiradas
    * posibles, por lo que si hubiese Hexagonos con fichas mal asignadas, este no produciria y habria test que romperian*/

    @Test
    void AlCrearTableroHay19TerrenosTest(){} {
        Tablero tablero = new Tablero();
        List<Terreno> terrenos = tablero.crearTerrenos();

        Integer tamaño = terrenos.size();

        assertEquals(19, tamaño);
    }

    @Test
    void AlCrearTableroHaySolo1DesiertoPorLoQueNoEntregaRecursosTest(){
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
    void AlCrearTableroHayAlMenosUnBosqueQueDaMaderaTest(){
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
    void AlCrearTableroHayAlMenosUnaMontañaQueDaMineralTest(){
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
    void AlCrearTableroHayAlMenosUnaColinaQueDaLadrilloTest(){
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
    void AlCrearTableroHayAlMenosUnPastizalQueDaLanaTest(){
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
    void AlCrearTableroHayAlMenosUnCampoQueDaGranoTest(){
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
    void NoHayNingunHexagonoConFicha7SiSale7Test() {
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
}
