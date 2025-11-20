package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Fase.CC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ComercioEntreJugadoresTest {
    Jugador jugado1;
    Jugador jugado2;
    CC comercio;
    String recurso1;
    String recurso2;
    @BeforeEach
    public void setUp() {
        this.jugado1 = new Jugador();
        this.jugado2 = new Jugador();
        this.comercio = new CC();
        this.recurso1 = "madera";
        this.recurso2 = "ladrillo";
    }
    @Test
    void test02JugadorComerciaConOtroJugador() {
        jugado1.agregarRecursos(recurso1,5);
        jugado2.agregarRecursos(recurso2,2);

        comercio.iniciarFase(jugado1);
        comercio.crearOferta(recursoRequerido, recursoOfrecido);
        comercio.responderOferta(jugador2, True);
    }
}
