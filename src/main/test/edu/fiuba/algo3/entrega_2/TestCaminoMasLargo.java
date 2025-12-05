package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
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
        tablero = new Tablero(new ArrayList<Terreno>(), new ArrayList<Vertice>(), new ArrayList<Arista>());
        gestor = new MazoDeRecursos(new ArrayList<>(List.of(new Madera(), new Ladrillo(), new Lana(), new Grano(), new Madera(), new Ladrillo(),
                new Madera(), new Ladrillo(), new Madera(), new Ladrillo(), new Madera(), new Ladrillo(), new Madera(), new Ladrillo(), new Lana(), new Grano(), new Madera(), new Ladrillo(),
                new Madera(), new Madera(), new Ladrillo(), new Lana(), new Grano(), new Madera(), new Ladrillo(),
                new Madera(), new Madera(), new Ladrillo(), new Lana(), new Grano(), new Madera(), new Ladrillo(),
                new Madera())));
        mano = new Mano();
        jugador = new Jugador(gestor, mano,"Alex");
        jugador2 = new Jugador(gestor, mano, "Max");
        cartas = List.of(new Monopolio(new Deshabilitado()));
        this.banca = Banca.crearBanca(new ArrayList<Recurso>(), cartas);
        Juego juego = Juego.crearInstancia(List.of(jugador), new Tablero(new ArrayList<Terreno>(), new ArrayList<Vertice>(), new ArrayList<Arista>()), Banca.crearBanca(List.of(new Madera()), cartas));
    }

    @Test
    public void test01CaminoLinealDe4() {
        Vertice v1 = new Vertice(0,1,1);
        Vertice v2 = new Vertice(1,1,1);
        Vertice v3 = new Vertice(2,1,1);
        Vertice v4 = new Vertice(3, 1, 1);
        Vertice v5 = new Vertice(4, 1, 1);

        // Crear camino lineal: v1 - v2 - v3 - v4 - v5

        v1.conectarConVertice(v2);
        v2.conectarConVertice(v3);
        v3.conectarConVertice(v4);
        v4.conectarConVertice(v5);

        List<Arista> aristas1 = v2.aristas();
        Arista a1 = aristas1.get(0);
        Arista a2 = aristas1.get(1);
        List<Arista> aristas2 = v4.aristas();
        Arista a3 = aristas2.get(0);
        Arista a4 = aristas2.get(1);

        v1.construirPobladoInicial(jugador);

        a1.construirCarretera(jugador);
        a2.construirCarretera(jugador);
        a3.construirCarretera(jugador);
        a4.construirCarretera(jugador);


        Tablero tableroTest = new Tablero((new ArrayList<Terreno>()), (new ArrayList<>(List.of(v1, v2, v3, v4, v5))), (new ArrayList<>(List.of(a1, a2, a3, a4))));
        int res = tableroTest.calcularCaminoMasLargo(jugador);
        assertEquals(4, res);
    }


    @Test
    public void test02CaminoConRamificacionEligeMasLargo() {
        Vertice v1 = new Vertice(0,1,1);
        Vertice v2 = new Vertice(1,1,1);
        Vertice v3 = new Vertice(2,1,1);
        Vertice v4 = new Vertice(3, 1, 1);
        Vertice v5 = new Vertice(4, 1, 1);
        Vertice v6 = new Vertice(5, 1, 1);
        /*
            Grafo:
                     v4
                      |
            v1 - v2 - v3 - v5 - v6
        */

        v1.conectarConVertice(v2);
        v3.conectarConVertice(v2);
        v3.conectarConVertice(v4);
        v3.conectarConVertice(v5);
        v6.conectarConVertice(v5);

        List<Arista> aristas1 = v1.aristas();
        Arista a1 = aristas1.get(0);
        List<Arista> aristas2 = v3.aristas();
        Arista a2 = aristas2.get(0);
        Arista a3 = aristas2.get(1);
        Arista a4 = aristas2.get(2);
        List<Arista> aristas3 = v6.aristas();
        Arista a5 = aristas3.get(0);

        v1.construirPobladoInicial(jugador);

        a1.construirCarretera(jugador);
        a2.construirCarretera(jugador);
        a3.construirCarretera(jugador);
        a4.construirCarretera(jugador);
        a5.construirCarretera(jugador);

        Tablero tableroTest = new Tablero((new ArrayList<Terreno>()), (new ArrayList<>(List.of(v1, v2, v3, v4, v5, v6))), (new ArrayList<>(List.of(a1, a2, a3, a4, a5))));
        int res = tableroTest.calcularCaminoMasLargo(jugador);
        assertEquals(4, res);
    }


    @Test
    public void test03ElCaminoSeCortaPorUnPobladoEnemigo() {
        Vertice v1 = new Vertice(0,1,1);
        Vertice v2 = new Vertice(1,1,1);
        Vertice v3 = new Vertice(2,1,1);
        Vertice v4 = new Vertice(3, 1, 1);

        // v1 - v2 - v3* - v4

        v1.conectarConVertice(v2);
        v2.conectarConVertice(v3);
        v3.conectarConVertice(v4);

        List<Arista> aristas1 = v1.aristas();
        Arista a1 = aristas1.get(0);
        List<Arista> aristas2 = v3.aristas();
        Arista a2 = aristas2.get(0);
        Arista a3 = aristas2.get(1);

        v1.construirPobladoInicial(jugador);
        v3.construirPobladoInicial(jugador2);

        a1.construirCarretera(jugador);
        a2.construirCarretera(jugador);
        a3.construirCarretera(jugador);

        Tablero tableroTest = new Tablero((new ArrayList<Terreno>()), (new ArrayList<>(List.of(v1, v2, v3, v4))), (new ArrayList<>(List.of(a1, a2, a3))));
        int res = tableroTest.calcularCaminoMasLargo(jugador);
        assertEquals(1, res);
    }

    @Test
    public void test04CaminoConCicloNoCuentaDoble() {
        Vertice v1 = new Vertice(0,1,1);
        Vertice v2 = new Vertice(1,1,1);
        Vertice v3 = new Vertice(2,1,1);
        Vertice v4 = new Vertice(3, 1, 1);

        /*
            Ciclo:
              v2
             /  \
            v1 - v3 - v4
        */

        v1.conectarConVertice(v2);
        v3.conectarConVertice(v2);
        v3.conectarConVertice(v1);
        v3.conectarConVertice(v4);

        List<Arista> aristas1 = v1.aristas();
        Arista a1 = aristas1.get(0);
        List<Arista> aristas2 = v3.aristas();
        Arista a2 = aristas2.get(0);
        Arista a3 = aristas2.get(1);
        Arista a4 = aristas2.get(2);

        v1.construirPobladoInicial(jugador);

        a1.construirCarretera(jugador);
        a2.construirCarretera(jugador);
        a3.construirCarretera(jugador);
        a4.construirCarretera(jugador);

        Tablero tableroTest = new Tablero((new ArrayList<Terreno>()), (new ArrayList<>(List.of(v1, v2, v3, v4))), (new ArrayList<>(List.of(a1, a2, a3, a4))));
        int res = tableroTest.calcularCaminoMasLargo(jugador);

        assertEquals(4, res);
    }


    @Test
    public void test05DosJugadoresSuperpuestosGanaElCorrecto() {
        Vertice v1 = new Vertice(0,1,1);
        Vertice v2 = new Vertice(1,1,1);
        Vertice v3 = new Vertice(2,1,1);
        Vertice v4 = new Vertice(3, 1, 1);
        Vertice v5 = new Vertice(4, 1, 1);

        // v1 - v2 - v3 - v4 - v5

        v1.conectarConVertice(v2);
        v2.conectarConVertice(v3);
        v3.conectarConVertice(v4);
        v4.conectarConVertice(v5);

        List<Arista> aristas1 = v2.aristas();
        Arista a1 = aristas1.get(0);
        Arista a2 = aristas1.get(1);
        List<Arista> aristas2 = v4.aristas();
        Arista a3 = aristas2.get(0);
        Arista a4 = aristas2.get(1);

        v1.construirPobladoInicial(jugador);
        v5.construirPobladoInicial(jugador2);

        a1.construirCarretera(jugador);
        a2.construirCarretera(jugador);

        a4.construirCarretera(jugador2);
        a3.construirCarretera(jugador2);

        Tablero tableroTest = new Tablero((new ArrayList<Terreno>()), (new ArrayList<>(List.of(v1, v2, v3, v4, v5))), (new ArrayList<>(List.of(a1, a2, a3, a4))));

        assertEquals(2, tableroTest.calcularCaminoMasLargo(jugador));
        assertEquals(2, tableroTest.calcularCaminoMasLargo(jugador2));
    }
}