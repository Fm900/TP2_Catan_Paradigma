package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Exception.ReglaDeDistanciaNoValida;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsumoRecursosYReglaAlConstruirPobladosTest {

    private Jugador nuevoJugador() {
        MazoDeRecursos gestor = new MazoDeRecursos(new ArrayList<>());
        Mano mano = new Mano();
        return new Jugador(gestor, mano,"Alex");
    }

    private void darRecursosParaPoblados(Jugador jugador, int cantidadPoblados) {
        for (int i = 0; i < cantidadPoblados; i++) {
            jugador.agregarRecurso(new Madera(), 1);
            jugador.agregarRecurso(new Ladrillo(), 1);
            jugador.agregarRecurso(new Grano(), 1);
            jugador.agregarRecurso(new Lana(), 1);
        }
    }

    @Test
    void Test01construirVariosPobladosConsumeRecursosHastaQueNoAlcanzaMas() {
        Jugador jugador = nuevoJugador();

        // Le doy recursos para 2 poblados
        darRecursosParaPoblados(jugador, 2);

        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Vertice v3 = new Vertice();

        // No conecto los vértices -> regla de distancia no me bloquea
        assertDoesNotThrow(() -> v1.construirPobladoInicial(jugador));
        assertDoesNotThrow(() -> v2.construirPobladoInicial(jugador));

        // ahora deberian faltar recursos
        assertThrows(RuntimeException.class, () -> v3.construirPobladoInicial(jugador));
    }


    @Test
    void Test02NoConsumeRecursosCuandoLaConstruccionEsInvalida() {
        Jugador jugador = nuevoJugador();
        darRecursosParaPoblados(jugador, 2);

        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Vertice v3 = new Vertice();

        // v1---v2, v3 aislado
        v1.conectarConVertice(v2);

        //Construyo en v1
        assertDoesNotThrow(() -> v1.construirPobladoInicial(jugador));

        //Intento construir en v2 y debe romper por ReglaDeDistancia,
        assertThrows(ReglaDeDistanciaNoValida.class, () -> v2.construirPobladoInicial(jugador));

        //Como no se consumieron recursos deberia poder construir en v3.
        assertDoesNotThrow(() -> v3.construirPobladoInicial(jugador));
    }
}
