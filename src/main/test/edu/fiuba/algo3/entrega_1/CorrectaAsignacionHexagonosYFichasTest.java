package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


class VerticeStub extends Vertice {
    int entregas = 0;
    Dictionary<String, Integer> entregas_por_recurso = new Hashtable<>();

    public void entregarRecursosPorConstruccion(String recurso){
        entregas++;
        Integer actual = entregas_por_recurso.get(recurso);
        if (actual == null) actual = 0;
        entregas_por_recurso.put(recurso, actual + 1); //si se llama a esta funcion el terreno deberia entregar ese recurso
    }
    public int totalDe(String recurso) {
        return entregas_por_recurso.get(recurso);
    }
}

public class CorrectaAsignacionHexagonosYFichasTest {

    /*Para verificar la Correcta Asignacion aleatoria de hexagonos y fichas testeo que se creen 19 hexagonos,
    * de los cuales uno debe ser desierto(por lo que no produce) y que haya al menos un terreno que produzca
    * cada tipo de recurso (Montañan, Pastizal, Campo, Colina y Bosque), para cada test se simulan todas las tiradas
    * posibles, por lo que si hubiese Hexagonos con fichas mal asignadas, este no produciria y habria test que romperian*/

    @Test
    void AlCrearTableroHay19TerrenosTest(){} {
        Tablero tablero = new Tablero();
        Integer tamaño = tablero.terrenos().size();
        assertEquals(19, tamaño);
    }

    @Test
    void AlCrearTableroHaySolo1DesiertoPorLoQueNoEntregaRecursosTest(){
        Tablero tablero = new Tablero();
        VerticeStub verticePrueba = new VerticeStub();

        //le asigno este vertice a todos los terrenos
        for (Terreno terreno : tablero.terrenos()) {
            terreno.asignarVerticesAdyacentes(List.of(verticePrueba));
        }

        //simulo todas las tiradas posibles
        for (int tirada : List.of(2,3,4,5,6,8,9,10,11,12)){
            for (Terreno terreno : tablero.terrenos()) {
                terreno.producirSiCorresponde(tirada);
            }
        }
        //Deben haber ocurrido 18 entregas ya que solo hay un terreno que no produce nunca y es el desierto
        assertEquals(18,verticePrueba.entregas,"Debe haber solo un terreno de los 19 que no entrega");
    }

    @Test
    void AlCrearTableroHayAlMenosUnBosqueQueDaMaderaTest(){
        Tablero tablero = new Tablero();

        VerticeStub verticePrueba = new VerticeStub();
        for (Terreno terreno : tablero.terrenos()) {
            terreno.asignarVerticesAdyacentes(List.of(verticePrueba));
        }

        for (int tirada : List.of(2,3,4,5,6,8,9,10,11,12)) {
            for (Terreno t : tablero.terrenos()) {
                t.producirSiCorresponde(tirada);
            }
        }

        assertTrue(verticePrueba.totalDe("Madera") > 0, "Debe haber al menos un Bosque (Madera).");
    }

    @Test
    void AlCrearTableroHayAlMenosUnaMontañaQueDaMineralTest(){
        Tablero tablero = new Tablero();

        VerticeStub verticePrueba = new VerticeStub();
        for (Terreno terreno : tablero.terrenos()) {
            terreno.asignarVerticesAdyacentes(List.of(verticePrueba));
        }

        for (int tirada : List.of(2,3,4,5,6,8,9,10,11,12)) {
            for (Terreno t : tablero.terrenos()) {
                t.producirSiCorresponde(tirada);
            }
        }

        assertTrue(verticePrueba.totalDe("Mineral") > 0, "Debe haber al menos una Montaña (Mineral).");
    }

    @Test
    void AlCrearTableroHayAlMenosUnaColinaQueDaLadrilloTest(){
        Tablero tablero = new Tablero();

        VerticeStub verticePrueba = new VerticeStub();
        for (Terreno terreno : tablero.terrenos()) {
            terreno.asignarVerticesAdyacentes(List.of(verticePrueba));
        }

        for (int tirada : List.of(2,3,4,5,6,8,9,10,11,12)) {
            for (Terreno t : tablero.terrenos()) {
                t.producirSiCorresponde(tirada);
            }
        }

        assertTrue(verticePrueba.totalDe("Ladrillo") > 0, "Debe haber al menos una Colina (Ladrillo).");
    }

    @Test
    void AlCrearTableroHayAlMenosUnPastizalQueDaLanaTest(){
        Tablero tablero = new Tablero();

        VerticeStub verticePrueba = new VerticeStub();
        for (Terreno terreno : tablero.terrenos()) {
            terreno.asignarVerticesAdyacentes(List.of(verticePrueba));
        }

        for (int tirada : List.of(2,3,4,5,6,8,9,10,11,12)) {
            for (Terreno t : tablero.terrenos()) {
                t.producirSiCorresponde(tirada);
            }
        }

        assertTrue(verticePrueba.totalDe("Lana") > 0, "Debe haber al menos un Pastizal (Lana).");
    }

    @Test
    void AlCrearTableroHayAlMenosUnCampoQueDaGranoTest(){
        Tablero tablero = new Tablero();

        VerticeStub verticePrueba = new VerticeStub();
        for (Terreno terreno : tablero.terrenos()) {
            terreno.asignarVerticesAdyacentes(List.of(verticePrueba));
        }

        for (int tirada : List.of(2,3,4,5,6,8,9,10,11,12)) {
            for (Terreno t : tablero.terrenos()) {
                t.producirSiCorresponde(tirada);
            }
        }

        assertTrue(verticePrueba.totalDe("Grano") > 0, "Debe haber al menos un Campo (Grano).");
    }

    @Test
    void NoHayNingunHexagonoConFicha7SiSale7NoProduceNadie() {
        Tablero tablero = new Tablero();
        VerticeStub verticePrueba = new VerticeStub();
        for (Terreno t : tablero.terrenos()) t.asignarVerticesAdyacentes(List.of(verticePrueba));

        // si sale 7: no debe producir nadie
        for (Terreno t : tablero.terrenos()) t.producirSiCorresponde(7);
        assertEquals(0, verticePrueba.entregas, "Con tirada 7 no debe producir ningún terreno.");
    }
}
