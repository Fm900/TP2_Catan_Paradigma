package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Turnos.Fase.Dados;
import edu.fiuba.algo3.modelo.Turnos.Primer;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Ladron;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Alterado;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Normal;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VerificarQueElTerrenoBajoElLadrónNoProduzcaRecursos {

    Jugador jugador2;
    Terreno terrenoActual;
    Terreno terrenoDestino;
    Vertice vertice;
    Ladron ladron;
    Jugador jugador;
    Recurso madera;

    List<Recurso> recursosJugador;
    List<Recurso> recursoMadera;

    @BeforeEach
    public void setUp() {
        madera = new Madera();
        vertice = new Vertice(1,0,0);
        recursosJugador = (List.of());
        recursoMadera = (List.of(madera, new Madera(), new Ladrillo(), new Grano(), new Lana()));
        terrenoActual = new Terreno (madera, 5, (new Alterado()));
        terrenoDestino = new Terreno( madera, 10, (new Normal()));
        jugador = new Jugador((new MazoDeRecursos(recursoMadera)), new Mano(),"Alex");
        jugador2 = new Jugador((new MazoDeRecursos(recursosJugador)), new Mano(),"Alex");
        ladron = Ladron.crearLadron(terrenoActual);
        List<Carta> cartas = new ArrayList<>();
        Juego.crearInstancia(List.of(jugador, jugador2), new Tablero(new ArrayList<Terreno>(), new ArrayList<Vertice>(), new ArrayList<Arista>()), Banca.crearBanca(List.of(new Madera()), cartas));
    }


    @Test
    public void test01TerrenoConLadronNoProduceRecursos(){

        vertice.construirPobladoInicial(jugador);
        terrenoDestino.asignarVerticesAdyacentes(List.of(vertice));

        ladron.moverADestino(jugador2, terrenoDestino, jugador);

        terrenoDestino.producirSiCorresponde(10);

        assertThrows(RuntimeException.class, () -> jugador2.consumirRecursos(recursoMadera), "No tienes suficiente");
    }

}