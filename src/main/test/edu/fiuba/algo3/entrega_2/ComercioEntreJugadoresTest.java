package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Fase.CC;
import edu.fiuba.algo3.modelo.Jugador.GestorDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ComercioEntreJugadoresTest {
    Jugador jugado1;
    Jugador jugado2;
    CC comercio;
    Recurso recurso1;
    Recurso recurso2;
    Recurso recurso3;
    List<Recurso> recursosRequeridos;
    List<Recurso> recursosOfrecidos;
    @BeforeEach
    public void setUp() {
        GestorDeRecursos gestor1 = new GestorDeRecursos(new ArrayList<Recurso>());
        Mano mano1 = new Mano();
        this.jugado1 = new Jugador(gestor1,mano1);
        this.jugado2 = null;
        this.recursosRequeridos = null;
        this.recursosOfrecidos = null;
        this.comercio = new CC();
        this.recurso1 = new Madera();
        this.recurso2 = new Ladrillo();
        this.recurso3 = new Mineral();

    }
    @Test
    void test01JugadorComerciaConOtroJugador() {
        GestorDeRecursos gestor2 = new GestorDeRecursos(new ArrayList<Recurso>());
        Mano mano2 = new Mano();
        this.jugado2 = new Jugador(gestor2,mano2);
        this.recursosRequeridos = List.of(recurso1, recurso2);
        this.recursosOfrecidos = List.of(recurso2);
        //damos recursos a los jugadores que van a comerciar
        jugado1.agregarRecurso(recurso1, 5);
        jugado1.agregarRecurso(recurso2, 3);
        jugado2.agregarRecurso(recurso1, 1);
        jugado2.agregarRecurso(recurso2, 5);

        comercio.iniciarFase(jugado1);
        comercio.crearOferta(recursosRequeridos);
        boolean respuesta = comercio.realizarIntercambio( recursosOfrecidos,jugado2);

        assertTrue(respuesta);
    }

    @Test
    void test02JugadorIntentaComerciarPeroNadieQuiere() {
        //en el caso de que no quieran comerciar, no hay receptor
        this.recursosRequeridos = List.of(recurso1, recurso2);
        //damos recursos a los jugadores que van a comerciar
        jugado1.agregarRecurso(recurso1, 5);
        jugado1.agregarRecurso(recurso2, 3);

        comercio.iniciarFase(jugado1);
        comercio.crearOferta(recursosRequeridos);
        boolean respuesta = comercio.realizarIntercambio(recursosOfrecidos,jugado2);

        assertFalse(respuesta);
    }
    @Test
    void test03JugadorIntentaComerciarPeroElOtroJugadorQuiereRecursosQueNotiene() {
        //en el caso de que no quieran comerciar, no hay receptor
        this.recursosRequeridos = List.of(recurso1, recurso2);
        this.recursosOfrecidos = List.of(recurso3);
        //damos recursos a los jugadores que van a comerciar
        jugado1.agregarRecurso(recurso1, 5);
        jugado1.agregarRecurso(recurso2, 3);

        comercio.iniciarFase(jugado1);
        comercio.crearOferta(recursosRequeridos);
        boolean respuesta = comercio.realizarIntercambio(recursosOfrecidos,jugado2);

        assertFalse(respuesta);
    }
}
