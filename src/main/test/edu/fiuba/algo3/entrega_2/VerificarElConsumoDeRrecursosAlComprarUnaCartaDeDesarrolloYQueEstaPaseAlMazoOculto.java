package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Cartas.ConstruccionCarreteras;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Deshabilitado;
import edu.fiuba.algo3.modelo.Jugador.GestorDeRecursos;
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
    GestorDeRecursos gestor;
    Mano mano;

    @Test
    public void test01VerificarConsumoDeRecursosCuandoSeCompraUnCarta(){
        precio = new ArrayList<>((List.of(new Lana(), new Grano(), new Mineral())));
        jugador = new Jugador(new GestorDeRecursos(precio), mano);
        carta = new ConstruccionCarreteras(new Deshabilitado());

        assertDoesNotThrow(() -> jugador.consumirRecursos(precio), "No tienes suficiente");
        assertThrows(RuntimeException.class, () -> jugador.consumirRecursos(precio), "No tienes suficiente");
    }

}
