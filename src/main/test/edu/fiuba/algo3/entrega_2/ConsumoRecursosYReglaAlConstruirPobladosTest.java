package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Excepciones.ReglaDeDistanciaNoValida;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConsumoRecursosYReglaAlConstruirPobladosTest {
    private Jugador jugador;
    private Vertice v0;
    private Vertice v1;
    private Vertice v2;
    private Tablero tablero;

    @BeforeEach
    public void SetUP() {
        tablero = new Tablero();
        jugador = new Jugador();
        v0 = new Vertice();
        v1 = new Vertice();
        v2 = new Vertice();
        v0.conectarConVertice(v1); //v1---v0
        v0.conectarConVertice(v2); //v1--v0--v2

        // Le damos exactamente los recursos para 1 poblado
        jugador.agregarRecursos("madera", 1);
        jugador.agregarRecursos("ladrillo", 1);
        jugador.agregarRecursos("lana", 1);
        jugador.agregarRecursos("grano", 1);
    }

    @Test
    void Test01ConstruirPobladoValidoConsumeRecursosYVerticeQuedaOcupado() {

        int recursosAntes = jugador.getCantidadRecursosTotales();
        tablero.colocarPoblado(jugador,v0);
        int recursosDespues = jugador.getCantidadRecursosTotales();

        assertEquals(recursosAntes -4,recursosDespues); //se consumieron todos sus recursos
        assertFalse(v0.validarConstruccionEnVecino()); //si sus vecinos no pueden construir es porque esta ocupado

    }

    @Test
    void Test02ConstruirConstruirPobladoInvalidoNoConstruyeNiConsumeRecursos() {
        tablero.colocarPoblado(jugador,v0); //construyo en v0

        //le doy recursos de nuevo
        jugador.agregarRecursos("madera", 1);
        jugador.agregarRecursos("ladrillo", 1);
        jugador.agregarRecursos("lana", 1);
        jugador.agregarRecursos("grano", 1);

        int recursosAntes = jugador.getCantidadRecursosTotales();

        assertThrows(ReglaDeDistanciaNoValida.class, () -> {
            tablero.colocarPoblado(jugador, v1); //si trato de construir en v1 no deberia poder ya que es vecino a v0
        });

        int recursoDespues = jugador.getCantidadRecursosTotales();

        assertEquals(recursosAntes,recursoDespues); //si no pude construir no se deben consurmir recursos
    }
    @Test
    void construirDosPobladosValidosRespetaReglaDistanciaYConsumeRecursosDosVeces(){
        // Recursos para 2 poblados
        jugador.agregarRecursos("madera", 1);
        jugador.agregarRecursos("ladrillo", 1);
        jugador.agregarRecursos("lana", 1);
        jugador.agregarRecursos("grano", 1);

        int recursos_antes = jugador.getCantidadRecursosTotales();
        tablero.colocarPoblado(jugador,v1);
        tablero.colocarPoblado(jugador,v2);
        int recursos_despues = jugador.getCantidadRecursosTotales();

        assertFalse(v1.validarConstruccionEnVecino()); //v1 queda ocupado
        assertFalse(v2.validarConstruccionEnVecino()); //v2 queda ocupado
        assertEquals(recursos_antes-8,recursos_despues);
    }
}
