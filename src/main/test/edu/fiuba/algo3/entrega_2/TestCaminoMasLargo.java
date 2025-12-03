package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Fase.Dados;
import edu.fiuba.algo3.modelo.Fase.PrimerTurno;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Deshabilitado;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Monopolio;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Arista.Vacia;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaminoMasLargo {

    MazoDeRecursos gestor;
    Mano mano;
    Jugador jugador;
    Jugador jugador2;
    Tablero tablero;
    List<Carta> cartas;
    Banca banca;

    @BeforeEach
    public void setUp(){
        tablero = new Tablero();
        gestor = new MazoDeRecursos(new ArrayList<>(List.of(new Madera(), new Ladrillo(), new Lana(), new Grano(), new Madera(), new Ladrillo(),
                new Madera(), new Ladrillo(), new Madera(), new Ladrillo(), new Madera(), new Ladrillo())));
        mano = new Mano();
        jugador = new Jugador(gestor, mano,"Alex");
        jugador2 = new Jugador(gestor, mano, "Max");
        cartas = List.of(new Monopolio(new Deshabilitado()));
        this.banca = Banca.crearBanca(new ArrayList<Recurso>(), cartas);
        Juego.crearInstancia(List.of(jugador, jugador2), List.of(new Dados()), List.of(new PrimerTurno()), new Tablero(), Banca.crearBanca(List.of(new Madera()), cartas));
    }

    // Utilidad: crea una arista vacía conectando dos vértices
    private Arista conectar(Vertice a, Vertice b) {
        Arista arista = new Arista(a, b, new Vacia());
        a.registrarArista(arista);
        b.registrarArista(arista);
        return arista;
    }

    @Test
    public void testCaminoLinealDe4() {

        // Crear vértices
        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Vertice v3 = new Vertice();
        Vertice v4 = new Vertice();
        Vertice v5 = new Vertice();

        // Crear camino lineal: v1 - v2 - v3 - v4 - v5
        Arista a1 = conectar(v1, v2);
        Arista a2 = conectar(v2, v3);
        Arista a3 = conectar(v3, v4);
        Arista a4 = conectar(v4, v5);

        v1.construirPobladoInicial(jugador);

        // Asigno dueño
        a1.construirCarretera(jugador);
        a2.construirCarretera(jugador);
        a3.construirCarretera(jugador);
        a4.construirCarretera(jugador);

        // Inserto las aristas al tablero
        tablero.aristas().add(a1); // si tu Tablero devuelve copia inmutable, necesitás setear aristas manualmente

        int res = tablero.caminoMasLargo(jugador);
        assertEquals(4, res);
    }


    @Test
    public void testCaminoConRamificacionEligeMasLargo() {


        /*
            Grafo:
                     v4
                      |
            v1 - v2 - v3 - v5 - v6
        */

        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Vertice v3 = new Vertice();
        Vertice v4 = new Vertice();
        Vertice v5 = new Vertice();
        Vertice v6 = new Vertice();

        Arista a1 = conectar(v1, v2);
        Arista a2 = conectar(v2, v3);
        Arista a3 = conectar(v3, v4);
        Arista a4 = conectar(v3, v5);
        Arista a5 = conectar(v5, v6);

        a1.construirCarretera(jugador);
        a2.construirCarretera(jugador);
        a3.construirCarretera(jugador);
        a4.construirCarretera(jugador);
        a5.construirCarretera(jugador);

        int res = tablero.caminoMasLargo(jugador);

        // El camino más largo es v1-v2-v3-v5-v6 (4 aristas)
        assertEquals(4, res);
    }


    @Test
    public void testElCaminoSeCortaPorUnPobladoEnemigo() {


        // v1 - v2 - v3 - v4
        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Vertice v3 = new Vertice();
        Vertice v4 = new Vertice();

        Arista a1 = conectar(v1, v2);
        Arista a2 = conectar(v2, v3);
        Arista a3 = conectar(v3, v4);

        a1.construirCarretera(jugador);
        a2.construirCarretera(jugador);
        a3.construirCarretera(jugador);

        // Jugador 2 pone poblado en v3 → rompe el camino
        v3.setDueño(jugador2);

        int res = tablero.caminoMasLargo(jugador);

        // Camino ahora solo v1-v2 (1) o v2-v3 pero v3 está bloqueado
        assertEquals(1, res);
    }


    @Test
    public void testCaminoConCicloNoCuentaDoble() {

        /*
            Ciclo:
              v1
             /  \
            v4 - v2 - v3
        */

        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Vertice v3 = new Vertice();
        Vertice v4 = new Vertice();

        Arista a1 = conectar(v1, v2);
        Arista a2 = conectar(v2, v3);
        Arista a3 = conectar(v3, v4);
        Arista a4 = conectar(v4, v1);

        // Todas del mismo jugador
        a1.construirCarretera(jugador);
        a2.construirCarretera(jugador);
        a3.construirCarretera(jugador);
        a4.construirCarretera(jugador);

        // Camino más largo NO debe ser 4 (cerrado), sino 3
        int res = tablero.caminoMasLargo(jugador);

        assertEquals(3, res);
    }


    @Test
    public void testDosJugadoresSuperpuestosGanaElCorrecto() {

        // v1 - v2 - v3 - v4 - v5
        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Vertice v3 = new Vertice();
        Vertice v4 = new Vertice();
        Vertice v5 = new Vertice();

        Arista a1 = conectar(v1, v2);
        Arista a2 = conectar(v2, v3);
        Arista a3 = conectar(v3, v4);
        Arista a4 = conectar(v4, v5);

        // jugador construye solo la mitad
        a1.construirCarretera(jugador);
        a2.construirCarretera(jugador);

        // jugador2 construye el resto
        a3.construirCarretera(jugador2);
        a4.construirCarretera(jugador2);

        assertEquals(2, tablero.caminoMasLargo(jugador));
        assertEquals(2, tablero.caminoMasLargo(jugador2));
    }
}
