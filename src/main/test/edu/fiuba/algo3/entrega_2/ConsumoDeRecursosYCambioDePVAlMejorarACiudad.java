package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Exception.Excepciones.NoSePuedeMejorarACiudad;
import edu.fiuba.algo3.modelo.Exception.Excepciones.NoTieneRecursos;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.EstadoProductivo;
import edu.fiuba.algo3.modelo.Tablero.Normal;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsumoDeRecursosYCambioDePVAlMejorarACiudad {

    @Test
    void NoSePuedeMejorarACiudadSiElVerticeEstaLibre() {
        Vertice vertice = new Vertice();
        Jugador jugador = new Jugador();

        assertThrows(NoSePuedeMejorarACiudad.class, () -> vertice.mejorarPobladoACiudad(jugador));
    }

    @Test
    void Test01NoSePuedeMejorarACiudadSiElJugadorNoTieneRecursosSuficientes() {
        Jugador jugador = new Jugador();
        Vertice vertice = new Vertice();

        // primero construyo el poblado
        jugador.agregarRecursos("madera", 1);
        jugador.agregarRecursos("ladrillo", 1);
        jugador.agregarRecursos("lana", 1);
        jugador.agregarRecursos("grano", 1);
        vertice.construirPoblado(jugador);

        // no le doy recursos para ciudad y trato de construir
        assertThrows(NoTieneRecursos.class, () -> vertice.mejorarPobladoACiudad(jugador));
    }

    @Test
    void Test02NoSePuedeMejorarACiudadSiElJugadorNoEsDueñoDelPoblado() {
        Jugador dueño = new Jugador();
        Jugador otroJugador = new Jugador();
        Vertice vertice = new Vertice();

        // dueño construye el poblado
        dueño.agregarRecursos("madera", 1);
        dueño.agregarRecursos("ladrillo", 1);
        dueño.agregarRecursos("lana", 1);
        dueño.agregarRecursos("grano", 1);
        vertice.construirPoblado(dueño);

        // otro jugador intenta mejorar
        otroJugador.agregarRecursos("grano", 2);
        otroJugador.agregarRecursos("mineral", 3);

        assertThrows(NoSePuedeMejorarACiudad.class, () -> vertice.mejorarPobladoACiudad(otroJugador));
    }

    @Test
    void Testo03mejorarPobladoACiudadDesdeVerticeConsumeRecursos() {
        Jugador jugador = new Jugador();
        Vertice vertice = new Vertice();

        jugador.agregarRecursos("madera", 1);
        jugador.agregarRecursos("ladrillo", 1);
        jugador.agregarRecursos("lana", 1);
        jugador.agregarRecursos("grano", 1);
        vertice.construirPoblado(jugador);

        jugador.agregarRecursos("grano", 2);
        jugador.agregarRecursos("mineral", 3);
        int recursosAntes = jugador.getCantidadRecursosTotales();

        vertice.mejorarPobladoACiudad(jugador);

        int recursosDespues = jugador.getCantidadRecursosTotales();
        assertEquals(recursosAntes - 5, recursosDespues); // 2 grano + 3 mineral
    }

    @Test
    void AlMejorarPobladoACiudadElVerticePasaAProducirElDoble() {
        Jugador jugador = new Jugador();
        Vertice vertice = new Vertice();
        EstadoProductivo estadoNormal = new Normal();
        Terreno terreno = new Terreno("madera", 5, estadoNormal);
        terreno.asignarVerticesAdyacentes(List.of(vertice));


        jugador.agregarRecursos("madera", 1);
        jugador.agregarRecursos("ladrillo", 1);
        jugador.agregarRecursos("lana", 1);
        jugador.agregarRecursos("grano", 1);
        vertice.construirPoblado(jugador);
        terreno.producirSiCorresponde(5);
        int recursos_jugador = jugador.getCantidadRecursosTotales();

        assertEquals(1,recursos_jugador);

        jugador.agregarRecursos("grano", 2);
        jugador.agregarRecursos("mineral", 3);
        vertice.mejorarPobladoACiudad(jugador); // mejoro a ciudad
        terreno.producirSiCorresponde(5);// ahora debería producir el doble por ser una ciudad, por lo que el jugador deberia tener 3 recursos
        recursos_jugador = jugador.getCantidadRecursosTotales();

        assertEquals(3, recursos_jugador);
    }

}