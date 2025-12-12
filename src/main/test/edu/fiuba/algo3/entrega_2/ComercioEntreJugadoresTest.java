package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Turnos.Fase.Comercio;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Intercambio.Oferta.Rechazado;
import edu.fiuba.algo3.modelo.Intercambio.Oferta.Resolucion;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Intercambio.Oferta.Oferta;
import edu.fiuba.algo3.modelo.Recurso.*;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ComercioEntreJugadoresTest {
    Jugador jugador1;
    Jugador jugador2;
    Comercio comercio;
    Recurso recurso1;
    Recurso recurso2;
    Recurso recurso3;
    List<Recurso> recursosRequeridos;
    List<Recurso> recursosOfrecidos;
    Banca banca;
    @BeforeEach
    public void setUp() {
        MazoDeRecursos gestor1 = new MazoDeRecursos(new ArrayList<Recurso>());
        MazoDeRecursos gestor2 = new MazoDeRecursos(new ArrayList<Recurso>());
        Mano mano1 = new Mano();
        Mano mano2 = new Mano();
        this.jugador1 = new Jugador(gestor1,mano1,"Alex", Color.CORAL);
        this.jugador2 = new Jugador(gestor2,mano2,"Felix",Color.BLACK);
        this.comercio = new Comercio();
        this.recurso1 = new Madera();
        this.recurso2 = new Ladrillo();
        this.recurso3 = new Mineral();
        this.recursosRequeridos = List.of(recurso1);
        this.recursosOfrecidos = List.of(recurso2,recurso2);
        List<Carta> cartas = new ArrayList<>();
        this.banca = Banca.crearBanca(new ArrayList<Recurso>(), cartas);

    }
    @Test
    void test01JugadorComerciaConOtroJugador() {
        //Preparamos a los jugadores para el intercambio

        this.jugador1.agregarRecurso(recurso1, 5);//6
        this.jugador1.agregarRecurso(recurso2, 3);//1

        this.jugador2.agregarRecurso(recurso1, 2);//1
        this.jugador2.agregarRecurso(recurso2, 5);//7
        // vemos los recursos por el momento
        int recursos1InicialJugador1 = jugador1.cantidadDeRecurso(recurso1);
        int recursos2InicialJugador1 = jugador1.cantidadDeRecurso(recurso2);
        int recursos1InicialJugador2 = jugador2.cantidadDeRecurso(recurso1);
        int recursos2InicialJugador2 = jugador2.cantidadDeRecurso(recurso2);
        //iniciamos fase de comercio
        comercio.ejecutar(jugador1, new ManejoTurnos(new ArrayList<>(List.of(jugador1, jugador2))));

        // jugador1 le hace una oferta a jugador2
        Oferta oferta = comercio.crearOfertaJugador(jugador2,recursosOfrecidos,recursosRequeridos);

        // jugador2 acepta la oferta y se realiza el intercambio
        oferta.aceptar();
        //se verifica el intercambio
        assertEquals(recursos1InicialJugador1 + 1, jugador1.cantidadDeRecurso(recurso1));
        assertEquals(recursos2InicialJugador1 - 2, jugador1.cantidadDeRecurso(recurso2));
        assertEquals(recursos1InicialJugador2 - 1, jugador2.cantidadDeRecurso(recurso1));
        assertEquals(recursos2InicialJugador2 + 2, jugador2.cantidadDeRecurso(recurso2));
    }

    @Test
    void test02JugadorIntentaComerciarPeroRechazanSuOferta() {
        Resolucion estadoEsperado = new Rechazado();
        //Preparamos a los jugadores para el intercambio
        jugador1.agregarRecurso(recurso1, 5);
        jugador1.agregarRecurso(recurso2, 3);

        jugador2.agregarRecurso(recurso1, 2);
        jugador2.agregarRecurso(recurso2, 5);
        // vemos los recursos por el momento
        //iniciamos fase de comercio
        comercio.ejecutar(jugador1, new ManejoTurnos(new ArrayList<>(List.of(jugador1, jugador2))));

        // jugador1 le hace una oferta a jugador2
        Oferta oferta = comercio.crearOfertaJugador(jugador2,recursosOfrecidos,recursosRequeridos);

        // jugador2 acepta la oferta y se realiza el intercambio
        oferta.declinar();
        //se verifica el intercambio
        assertEquals(estadoEsperado.getClass(), oferta.getEstado().getClass());
    }

}
