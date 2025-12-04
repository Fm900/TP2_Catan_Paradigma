package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import edu.fiuba.algo3.modelo.Turnos.Fase.Comercio;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Caballero;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Deshabilitado;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Especifico;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Estandar;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Generico;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Tasa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComercioConLaBancaTest {
    Jugador jugador1;
    Comercio comercio;
    Recurso recurso1;
    Recurso recurso2;
    Recurso recurso3;
    Recurso recurso4;

    @BeforeEach
    public void setUp() {
        this.recurso1 = new Madera();
        this.recurso2 = new Ladrillo();
        this.recurso3 = new Mineral();
        this.recurso4 = new Lana();
        List<Carta> cartas = new ArrayList<>(List.of(new Caballero(new Deshabilitado())));
        List<Recurso> recursosBancaIniciales = new ArrayList<>(List.of(recurso1,recurso1,recurso2,recurso2,recurso2));
        MazoDeRecursos gestor1 = new MazoDeRecursos(new ArrayList<Recurso>());
        Mano mano1 = new Mano();
        this.jugador1 = new Jugador(gestor1,mano1,"Alex");
        this.jugador1.agregarRecurso(recurso1,10);
        this.jugador1.agregarRecurso(recurso2,10);
        this.jugador1.agregarRecurso(recurso3,10);
        this.jugador1.agregarRecurso(recurso4,10);
        Banca.crearBanca(recursosBancaIniciales, cartas);
        Banca.getInstance().agregarRecurso(recursosBancaIniciales);

        Juego.crearInstancia(List.of(jugador1), new Tablero(new ArrayList<Terreno>(), new ArrayList<Vertice>(), new ArrayList<Arista>()), Banca.crearBanca(List.of(new Madera()), cartas));
        this.comercio = new Comercio();

    }
    @Test
    void test01JugadorComerciaConLaBancaMedianteLaTazaEstandar(){
        Tasa tasaDeComercio = new Estandar();
        Recurso recursoRequerido = recurso1;
        List<Recurso> recursoOfrecido =  List.of(recurso2);

        int recursos1InicialJugador1 = jugador1.cantidadDeRecurso(recurso1);
        int recursos2InicialJugador1 = jugador1.cantidadDeRecurso(recurso2);

        List<Jugador> jugadores = new ArrayList<>(List.of(jugador1));
        comercio.ejecutar(jugador1, new ManejoTurnos(jugadores));
        comercio.comerciarConBanca(tasaDeComercio, recursoOfrecido, recursoRequerido);

        assertEquals(recursos1InicialJugador1 + 1, jugador1.cantidadDeRecurso(recurso1));
        assertEquals(recursos2InicialJugador1 - 4, jugador1.cantidadDeRecurso(recurso2));
    }
    @Test
    void test02JugadorComerciaConLaBancaMedianteLaTazaEspecifica(){
        //por el momento harcodeamos el recurso del puerto
        Tasa tasaDeComercio = new Especifico(recurso1);
        List<Recurso> recursoPuertoOfrecido =  List.of(recurso1);
        Recurso recursoRequerido = recurso2;

        int recursos1InicialJugador1 = jugador1.cantidadDeRecurso(recurso1);
        int recursos2InicialJugador1 = jugador1.cantidadDeRecurso(recurso2);

        List<Jugador> jugadores = new ArrayList<>(List.of(jugador1));
        comercio.ejecutar(jugador1, new ManejoTurnos(jugadores));
        comercio.comerciarConBanca(tasaDeComercio, recursoPuertoOfrecido, recursoRequerido);

        assertEquals(recursos1InicialJugador1 -2, jugador1.cantidadDeRecurso(recurso1));
        assertEquals(recursos2InicialJugador1 + 1, jugador1.cantidadDeRecurso(recurso2));
    }
    @Test
    void test03JugadorComerciaConLaBancaMedianteLaTazagenerica(){
        Tasa tasaDeComercio = new Generico();
        List<Recurso> recursosOfrecidos = List.of(recurso2,recurso3,recurso4);
        Recurso recursoRequeridos = recurso1;

        int recursos1InicialJugador1 = jugador1.cantidadDeRecurso(recurso1);
        int recursos2InicialJugador1 = jugador1.cantidadDeRecurso(recurso2);
        int recursos3InicialJugador1 = jugador1.cantidadDeRecurso(recurso3);
        int recursos4InicialJugador1 = jugador1.cantidadDeRecurso(recurso4);

        List<Jugador> jugadores = new ArrayList<>(List.of(jugador1));
        comercio.ejecutar(jugador1, new ManejoTurnos(jugadores));
        comercio.comerciarConBanca(tasaDeComercio,recursosOfrecidos, recursoRequeridos);

        assertEquals(recursos1InicialJugador1 +1, jugador1.cantidadDeRecurso(recurso1));
        assertEquals(recursos2InicialJugador1 - 1, jugador1.cantidadDeRecurso(recurso2));
        assertEquals(recursos3InicialJugador1 - 1, jugador1.cantidadDeRecurso(recurso3));
        assertEquals(recursos4InicialJugador1 - 1, jugador1.cantidadDeRecurso(recurso4));
    }
}
