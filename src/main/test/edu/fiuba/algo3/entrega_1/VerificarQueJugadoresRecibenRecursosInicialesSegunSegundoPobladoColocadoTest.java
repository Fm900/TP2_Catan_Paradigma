package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Fase.PrimerTurno;
import edu.fiuba.algo3.modelo.Fase.SegundoTurno;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.GeneradorDeTerrenos;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Normal;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class VerificarQueJugadoresRecibenRecursosInicialesSegunSegundoPobladoColocadoTest {

    class GeneradorDeTerrenosControlado extends GeneradorDeTerrenos {
        private final List<Terreno> terrenos;
        public GeneradorDeTerrenosControlado(List<Terreno> terrenos) {this.terrenos = terrenos;}
        @Override
        public List<Terreno> generar() {return terrenos;}
    } //esto es para armar los terrenos a mano, asi se lo que debo esperar al darle los recursos al jugador por el poblado que coloca

    private Jugador nuevoJugadorConRecursosIniciales() {
        MazoDeRecursos gestor = new MazoDeRecursos(new ArrayList<>(List.of(new Madera(), new Ladrillo(), new Lana(), new Grano(),new Madera(), new Ladrillo(), new Lana(), new Grano())));
        Mano mano = new Mano();
        return new Jugador(gestor, mano,"Alex");
    }


    @Test
    void Test01JugadorRecibeRecursosInicialesSegunTerrenosAdyacentes(){
        Jugador jugador = nuevoJugadorConRecursosIniciales();

        Vertice verticePrimerTurno = new Vertice();
        Vertice verticeSegundoTurno = new Vertice();

        //creo un tablero de forma controlada
        Terreno terrenoConMineral = new Terreno(new Mineral(), 5, new Normal());
        terrenoConMineral.asignarVerticesAdyacentes(List.of(verticeSegundoTurno));
        Tablero tablero = new Tablero(new GeneradorDeTerrenosControlado(List.of(terrenoConMineral)));
        List<Recurso> precio = List.of(new Mineral());


        // el primer poblado no debe dar recursos, asi que no deberia tener Mineral
        PrimerTurno primerTurno = new PrimerTurno();
        primerTurno.setVerticeParaConstruir(verticePrimerTurno);
        primerTurno.iniciarFase(List.of(jugador),tablero);
        assertThrows(RuntimeException.class, () -> jugador.consumirRecursos(precio), "Antes del segundo poblado no deberia poder pagar un mineral");


        // en el segundo turno construyo un poblado que me debe dar recursos
        SegundoTurno segundoTurno = new SegundoTurno();
        segundoTurno.setVerticeParaConstruir(verticeSegundoTurno);
        segundoTurno.iniciarFase(List.of(jugador), tablero);
        assertDoesNotThrow(() -> jugador.consumirRecursos(precio), "Despues del segundo poblado deberia poder pagar un mineral");

    }
}
