package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Exception.NoAlcanzanLosRecursos;
import edu.fiuba.algo3.modelo.Exception.NoTieneRecursosSuficientesParaDescartar;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Cartas.ConstruccionCarreteras;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Deshabilitado;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Mineral;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
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

    @Test
    public void testJugadorPuedeConsumirRecursos() {
        List<Recurso> precio = new ArrayList<>(List.of(new Lana(), new Grano(), new Mineral()));
        Jugador jugador = new Jugador(new MazoDeRecursos(precio), mano, "Alex");
        assertDoesNotThrow(() -> jugador.consumirRecursos(precio));
    }

    @Test
    public void testJugadorNoPuedeConsumirRecursosDosVeces() {
        List<Recurso> precio = new ArrayList<>(List.of(new Lana(), new Grano(), new Mineral()));
        Jugador jugador = new Jugador(new MazoDeRecursos(precio), mano, "Alex");

        jugador.consumirRecursos(precio); // consume recursos
        assertThrows(NoTieneRecursosSuficientesParaDescartar.class, () -> jugador.consumirRecursos(precio), "No tienes suficientes recursos para realizar esta operacion");
    }

}
