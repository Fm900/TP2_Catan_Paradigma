package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Exception.NoAlcanzanLosRecursos;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import edu.fiuba.algo3.modelo.Turnos.Fase.Dados;
import edu.fiuba.algo3.modelo.Turnos.Primer;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Intercambio.ComprarCartas;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Caballero;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Deshabilitado;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VerificarElConsumoDeRrecursosAlComprarUnaCartaDeDesarrolloYQueEstaPaseAlMazoOculto {
    Carta carta;
    Jugador jugador;
    List<Recurso> precio;
    MazoDeRecursos gestor;
    Mano mano;
    List<Carta> cartas;

    @BeforeEach
    public void setUp() {
        mano = new Mano();
        precio = new ArrayList<>(List.of(new Lana(), new Grano(), new Mineral()));
        jugador = new Jugador(new MazoDeRecursos(precio), mano, "Alex", Color.ROSYBROWN);
        carta = new Caballero(new Deshabilitado());
        cartas = new ArrayList<>(List.of(carta));
        Juego.crearInstancia(List.of(jugador), new Tablero(new ArrayList<Terreno>(), new ArrayList<Vertice>(), new ArrayList<Arista>()), Banca.crearBanca(List.of(new Madera()), cartas));
    }

    @Test
    public void test01SeVerificaElConsumoDeRecursosConLaCompraDeUnaCarta(){
        ComprarCartas intercambio = new ComprarCartas(jugador);

        assertDoesNotThrow(()->intercambio.intercambio(), "No tienes suficiente");
        assertThrows(NoAlcanzanLosRecursos.class, ()->jugador.consumirRecursos(List.of(new Madera())), "No tienes suficiente");
    }
}
