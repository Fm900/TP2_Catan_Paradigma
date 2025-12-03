package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Exception.NoAlcanzanLosRecursos;
import edu.fiuba.algo3.modelo.Fase.Dados;
import edu.fiuba.algo3.modelo.Fase.PrimerTurno;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Ladron;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Alterado;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Normal;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class VerificarQueUnJugadorActivoPuedaMoverElLadronYRobarUnaCartaAleatoreaDeUnPropietario {

    Terreno terrenoActual;
    Terreno terrenoDestino;
    Vertice vertice1;
    Vertice vertice2;
    Ladron ladron;
    Jugador jugador1;
    Jugador jugador2;
    List<Recurso> recursosJugador1;
    List<Recurso> recursosJugador2;

    @BeforeEach
    public void setUp() {
        vertice1 = new Vertice();
        vertice2 = new Vertice();
        recursosJugador1 = List.of();
        recursosJugador2 = List.of(new Madera(), new Ladrillo(), new Lana(), new Grano(), new Lana());
        terrenoActual = new Terreno (new Madera(), 5, (new Alterado()));
        terrenoDestino = new Terreno( new Madera(), 10, (new Normal()));
        jugador1 = new Jugador((new MazoDeRecursos(recursosJugador1)), new Mano(),"Alex");
        jugador2 = new Jugador((new MazoDeRecursos(recursosJugador2)), new Mano(),"Alex");
        ladron = Ladron.crearLadron(terrenoActual);
        List<Carta> cartas = new ArrayList<>();
        Juego.crearInstancia(List.of(jugador1, jugador2), List.of(new Dados()), List.of(new PrimerTurno()), new Tablero(), Banca.crearBanca(List.of(new Madera()), cartas));
    }

    @Test
    public void test01SeVerificaQueSeRobaCartaAleatoriaDeUnPropietario() {
        vertice2.construirPobladoInicial(jugador2);
        terrenoDestino.asignarVerticesAdyacentes(List.of(vertice1, vertice1, vertice1, vertice1, vertice1, vertice2));

        ladron.moverADestino(jugador1, terrenoDestino, jugador2);

        assertDoesNotThrow(() -> jugador1.consumirRecursos(List.of(new Lana())), "No tienes suficiente");
        assertThrows(NoAlcanzanLosRecursos.class, () -> jugador2.consumirRecursos(recursosJugador2), "No tienes suficiente");
    }
}