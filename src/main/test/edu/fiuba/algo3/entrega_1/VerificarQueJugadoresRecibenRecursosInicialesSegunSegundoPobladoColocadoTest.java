package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Turnos.*;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Constructores.GeneradorDeTerrenos;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Arista.Vacia;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Normal;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import edu.fiuba.algo3.modelo.Turnos.Fase.*;
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
        //le damos recursos para dos poblados y dos carreteras
        MazoDeRecursos gestor = new MazoDeRecursos(new ArrayList<>(List.of(new Madera(),new Madera(), new Madera(), new Madera(),
                                                                           new Ladrillo(), new Ladrillo(), new Ladrillo(), new Ladrillo(),
                                                                           new Lana(), new Lana(),
                                                                           new Grano(), new Grano())));
        Mano mano = new Mano();
        return new Jugador(gestor, mano,"Roberto");
    }

    private void inicializarJuego(Tablero tablero, Jugador jugador) {
        List<Fase> fasesPrincipales = List.of(new Dados(), new Construccion(), new Comercio(), new JugarCartas());
        List<Turno> fasesIniciales = List.of(new Primer(), new Segundo()
        );
        List<Carta> cartas = new ArrayList<>();
        Banca banca = Banca.crearBanca(new ArrayList<>(), cartas);
        Juego.crearInstancia(List.of(jugador), new Tablero(new ArrayList<Terreno>(), new ArrayList<Vertice>(), new ArrayList<Arista>()), Banca.crearBanca(List.of(new Madera()), cartas));

    }

    @Test
    void Test01JugadorRecibeRecursosInicialesSegunTerrenosAdyacentes(){
        Juego.reset();
        Jugador jugador = nuevoJugadorConRecursosIniciales();

        Vertice verticePrimerTurno = new Vertice(1,1,1);
        Vertice verticeSegundoTurno = new Vertice(2,2,2);


        //creo un tablero de forma controlada
        Terreno terrenoConMineral = new Terreno(new Mineral(), 5, new Normal());
        terrenoConMineral.asignarVerticesAdyacentes(List.of(verticeSegundoTurno));
        Tablero tablero = new Tablero(new GeneradorDeTerrenosControlado(List.of(terrenoConMineral)));

        inicializarJuego(tablero, jugador);
        List<Recurso> precio = List.of(new Mineral());
        Arista aristaPrimerTurno = new Arista(verticePrimerTurno, new Vertice(0,1,1), new Vacia());
        Arista aristaSegundoTurno = new Arista(verticeSegundoTurno, new Vertice(0,1,1), new Vacia());


        // el primer poblado no debe dar recursos, asi que no deberia tener Mineral
        Primer primerTurno = new Primer();
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);
        primerTurno.construir(new ManejoTurnos(jugadores), verticePrimerTurno,aristaPrimerTurno);
        assertThrows(RuntimeException.class, () -> jugador.consumirRecursos(precio), "Antes del segundo poblado no deberia poder pagar un mineral");


        // en el segundo turno construyo un poblado que me debe dar recursos
        Segundo segundoTurno = new Segundo();
        segundoTurno.construir(new ManejoTurnos(jugadores), verticeSegundoTurno, aristaSegundoTurno);
        assertDoesNotThrow(() -> jugador.consumirRecursos(precio), "Despues del segundo poblado deberia poder pagar un mineral");

    }
}
