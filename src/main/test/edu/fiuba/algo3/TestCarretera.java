package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Exception.AristaOcupadaNoSePuedeConstruir;
import edu.fiuba.algo3.modelo.Exception.NoSePuedeConstruirPorFaltaDeConexion;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCarretera {

    Jugador jugador;
    Jugador otroJugador;
    Vertice v1, v2, v3;
    Arista a12, a23;

    @BeforeEach
    public void setUp() {
        // Mano vacía válida
        Mano mano = new Mano();

        // Jugador con suficientes recursos para construir carreteras
        List<Recurso> recursos = List.of(
                new Madera(), new Ladrillo(),
                new Madera(), new Ladrillo(),
                new Madera(), new Ladrillo()
        );

        jugador = new Jugador(new MazoDeRecursos(recursos), mano, "J1", Color.BLACK);
        otroJugador = new Jugador(new MazoDeRecursos(recursos), mano, "J2", Color.BLUE);

        // Grafo básico: v1 -- v2 -- v3
        v1 = new Vertice(1, 0, 0);
        v2 = new Vertice(2, 1, 0);
        v3 = new Vertice(3, 2, 0);

        // v1 <-> v2
        v1.conectarConVertice(v2);
        a12 = v1.aristas().get(0);

        // v2 <-> v3
        v2.conectarConVertice(v3);
        a23 = v2.aristas().get(1); // la segunda arista
    }

    // ------------------------------------------------------------

    @Test
    public void noSePuedeConstruirSiNoHayConexionConElJugador() {
        assertThrows(NoSePuedeConstruirPorFaltaDeConexion.class,
                () -> a12.construirCarretera(jugador));
    }

    // ------------------------------------------------------------

    @Test
    public void sePuedeConstruirUnaCarreteraSiHayPobladoPropioEnUnExtremo() {
        v1.construirPobladoInicial(jugador);

        assertDoesNotThrow(() ->
                a12.construirCarretera(jugador)
        );
    }

    // ------------------------------------------------------------

    @Test
    public void noSePuedeConstruirCarreteraEnAristaOcupada() {
        v1.construirPobladoInicial(jugador);
        a12.construirCarretera(jugador);

        assertThrows(AristaOcupadaNoSePuedeConstruir.class,
                () -> a12.construirCarretera(jugador));
    }

    // ------------------------------------------------------------

    @Test
    public void sePuedeConstruirCarreteraSiHayUnaAristaAdyacentePropia() {
        // v1 -- v2 -- v3
        // Construyo en la primera arista
        v1.construirPobladoInicial(jugador);
        a12.construirCarretera(jugador);

        // Ahora debería poder construir en la siguiente (a23)
        assertDoesNotThrow(() ->
                a23.construirCarretera(jugador)
        );
    }

    // ------------------------------------------------------------

    @Test
    public void otroJugadorNoPuedeExtenderLaCarreteraDeOtro() {
        v1.construirPobladoInicial(jugador);
        a12.construirCarretera(jugador);

        assertThrows(NoSePuedeConstruirPorFaltaDeConexion.class,
                () -> a23.construirCarretera(otroJugador));
    }

}
