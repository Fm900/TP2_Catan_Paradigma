package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Exception.NoAlcanzanLosRecursos;
import edu.fiuba.algo3.modelo.Exception.NoTieneRecursosSuficientesParaDescartar;
import edu.fiuba.algo3.modelo.Fase.Dados;
import edu.fiuba.algo3.modelo.Fase.PrimerTurno;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Cartas.ConstruccionCarreteras;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Deshabilitado;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
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

    @BeforeEach
    public void setUp() {
        precio = new ArrayList<>(List.of(new Lana(), new Grano(), new Mineral()));
        jugador = new Jugador(new MazoDeRecursos(precio), mano, "Alex");
        Juego.crearInstancia(List.of(jugador), List.of(new Dados()), List.of(new PrimerTurno()), new Tablero(), Banca.creacBanca(List.of(new Madera())));
    }

    @Test
    public void testJugadorPuedeConsumirRecursos() {
        assertDoesNotThrow(() -> jugador.consumirRecursos(precio));
    }

    @Test
    public void testJugadorNoPuedeConsumirRecursosDosVeces() {
        jugador.consumirRecursos(precio); // consume recursos
        assertThrows(NoAlcanzanLosRecursos.class, () -> jugador.consumirRecursos(precio), "No tienes suficientes recursos para realizar esta operacion");
    }

}
