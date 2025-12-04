package edu.fiuba.algo3;
import edu.fiuba.algo3.modelo.Exception.AristaOcupadaNoSePuedeConstruir;
import edu.fiuba.algo3.modelo.Exception.NoSePuedeConstruirPorFaltaDeConexion;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Turnos.Fase.Dados;
import edu.fiuba.algo3.modelo.Turnos.Primer;
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

public class ConstruirCarreterasTest {
    Vertice vertice1;
    Vertice vertice2;
    Jugador jugador1;
    Jugador jugador2;
    Jugador jugador;
    Arista arista;
    MazoDeRecursos recursos;
    Mano mano;
    List<Carta> cartas;
    Juego juego;
    Banca banca;

    @BeforeEach
    public void setUp() {
        Juego.reset();
        Banca.reset();
        List<Recurso> listaRecursos = new ArrayList<>(List.of(new Madera(), new Ladrillo(), new Lana(), new Grano(), new Madera(), new Ladrillo(), new Madera(), new Ladrillo()));
        vertice1 = new Vertice(1, 0,0 );
        vertice2 = new Vertice(1,0,0);
        recursos = new MazoDeRecursos(listaRecursos);
        jugador1 = new Jugador(recursos, mano, "Hola");
        jugador2 = new Jugador(recursos, mano, "Hola");
        jugador = new Jugador(recursos, mano, "Hola");
        vertice1.conectarConVertice(vertice2);
        cartas = List.of(new Monopolio(new Deshabilitado()));

        banca = Banca.crearBanca(listaRecursos ,cartas);
        Juego.crearInstancia(List.of(jugador1, jugador2), new Tablero(new ArrayList<Terreno>(), new ArrayList<Vertice>(), new ArrayList<Arista>()), Banca.crearBanca(List.of(new Madera()), cartas));

    }
    @Test
    public void noSePuedeConstruirSinUnaConstruccionPropiaEnUnVerticeAdyacente() {
        List<Arista> aristas = vertice1.aristas();
        arista = aristas.get(0);
        assertThrows(NoSePuedeConstruirPorFaltaDeConexion.class, () -> arista.construirCarretera(jugador), "No existe conexión con el jugador");
    }
    @Test
    public void noSePuedeConstruirSiLaAristaEstaOcupada() {
        List<Arista> aristas = vertice1.aristas();
        arista = aristas.get(0);
        vertice1.construirPobladoInicial(jugador);
        arista.construirCarretera(jugador);
        assertThrows(AristaOcupadaNoSePuedeConstruir.class, () -> arista.construirCarretera(jugador), "La Arista esta ocupada");
    }
    @Test
    public void sePuedeConstruirUnaCarreteraSiHayUnVerticeAdyacenteConUnaConstruccionPropia() {
        List<Arista> aristas = vertice1.aristas();
        vertice1.construirPobladoInicial(jugador);
        arista = aristas.get(0);
        assertDoesNotThrow(()-> arista.construirCarretera(jugador));
    }
    @Test
    public void sePuedeConstruirUnaCarreteraSiHayUnaAristaAdcyacenteConUnaConstruccionPropia() {
        vertice1.construirPobladoInicial(jugador);
        List<Arista> aristas1 = vertice1.aristas();
        Arista arista1 = aristas1.get(0);
        arista1.construirCarretera(jugador);
        Vertice vertice3 = new Vertice(1,0,0);
        vertice3.conectarConVertice(vertice2);
        List<Arista> aristas = vertice3.aristas();
        Arista arista2 = aristas.get(0);
        assertDoesNotThrow(()-> arista2.construirCarretera(jugador));
    }
}