package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Exception.AristaOcupadaNoSePuedeConstruir;
import edu.fiuba.algo3.modelo.Exception.NoSePuedeConstruirPorFaltaDeConexion;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConstruirCarreterasTest {
    Vertice vertice1;
    Vertice vertice2;
    Jugador jugador;
    Arista arista;
    MazoDeRecursos recursos;
    Mano mano;


    @BeforeEach
    public void setUp() {
        List<Recurso> listaRecursos = new ArrayList<>(List.of(new Madera(), new Ladrillo(), new Grano(), new Lana(), new Madera(), new Ladrillo()));
        vertice1 = new Vertice();
        vertice2 = new Vertice();
        recursos = new MazoDeRecursos(listaRecursos);
        jugador = new Jugador(recursos, mano, "Hola");
        vertice1.conectarConVertice(vertice2);
    }

    @Test
    public void noSePuedeConstruirSinUnaConstruccionPropiaEnUnVerticeAdyacente() {
        List<Arista> aristas = vertice1.aristas();
        arista = aristas.get(0);

        assertThrows(NoSePuedeConstruirPorFaltaDeConexion.class, () -> arista.construirCarretera(jugador), "El jugador no está conectado a este vértice");
    }

    @Test
    public void noSePuedeConstruirSiLaAristaEstaOcupada() {
        List<Arista> aristas = vertice1.aristas();
        arista = aristas.get(0);
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
        Vertice vertice3 = new Vertice();
        vertice2.conectarConVertice(vertice3);
        List<Arista> aristas = vertice1.aristas();
        arista = aristas.get(0);
        List<Arista> aristas2 = vertice3.aristas();
        Arista arista2 = aristas2.get(0);
        arista2.construirCarretera(jugador);

        assertDoesNotThrow(()-> arista.construirCarretera(jugador));
    }
}
