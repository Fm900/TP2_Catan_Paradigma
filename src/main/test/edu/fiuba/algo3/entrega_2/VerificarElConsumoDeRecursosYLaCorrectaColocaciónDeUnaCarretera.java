package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Madera;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Arista.Vacia;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VerificarElConsumoDeRecursosYLaCorrectaColocaciónDeUnaCarretera {
    Vertice vertice1;
    Vertice vertice2;
    Arista arista;
    Jugador jugador;
    List<Recurso> precio;
    MazoDeRecursos gestor;
    Mano mano;

    @Test
    public void test01VerificarConsumoDeRecursosAlConstruirUnaCarretera(){
        precio = new ArrayList<>(List.of(new Madera(), new Ladrillo()));
        gestor = new MazoDeRecursos(precio);
        jugador = new Jugador(gestor, mano,"Alex");
        vertice1 = new Vertice();
        vertice2 = new Vertice();
        arista = new Arista(vertice1, vertice2, new Vacia());

        assertDoesNotThrow(() -> jugador.consumirRecursos(precio), "No tienes suficiente");
        assertThrows(RuntimeException.class, () -> jugador.consumirRecursos(precio), "No tienes suficiente");
    }
}