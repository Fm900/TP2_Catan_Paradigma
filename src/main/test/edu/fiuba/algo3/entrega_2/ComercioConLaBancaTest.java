package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Fase.Comercio;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Especifico;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Estandar;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Generico;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Tasa;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComercioConLaBancaTest {
    Jugador jugado1;
    Banca banca;
    Comercio comercio;
    Recurso recurso1;
    Recurso recurso2;
    Recurso recurso3;
    Recurso recurso4;
    @BeforeEach
    public void setUp() {
        MazoDeRecursos gestor1 = new MazoDeRecursos(new ArrayList<Recurso>());
        Mano mano1 = new Mano();
        this.jugado1 = new Jugador(gestor1,mano1);
        this.comercio = new Comercio();
        this.recurso1 = new Madera();
        this.recurso2 = new Ladrillo();
        this.recurso3 = new Mineral();
        this.recurso4 = new Lana();
        List<Recurso> recursosBancaIniciales = new ArrayList<>(List.of(recurso1,recurso1,recurso2,recurso2,recurso2));
        this.banca = new Banca(recursosBancaIniciales);
        this.jugado1.agregarRecurso(recurso1,10);
        this.jugado1.agregarRecurso(recurso2,10);
        this.jugado1.agregarRecurso(recurso3,10);
        this.jugado1.agregarRecurso(recurso4,10);
    }
    @Test
    void test01JugadorComerciaConLaBancaMedianteLaTazaEstandar(){
        Tasa tasaDeComercio = new Estandar();
        Recurso recursoRequerido = recurso1;
        List<Recurso> recursoOfrecido =  List.of(recurso2);

        int recursos1InicialJugador1 = jugado1.cantidadDeRecurso(recurso1);
        int recursos2InicialJugador1 = jugado1.cantidadDeRecurso(recurso2);

        comercio.iniciarFase(jugado1,banca);
        comercio.crearOfertaBanca(tasaDeComercio, recursoOfrecido, recursoRequerido);


        assertEquals(recursos1InicialJugador1 + 1, jugado1.cantidadDeRecurso(recurso1));
        assertEquals(recursos2InicialJugador1 - 4, jugado1.cantidadDeRecurso(recurso2));
    }
    @Test
    void test02JugadorComerciaConLaBancaMedianteLaTazaEspecifica(){
        //por el momento harcodeamos el recurso del puerto
        Tasa tasaDeComercio = new Especifico(recurso1);
        List<Recurso> recursoPuertoOfrecido =  List.of(recurso1);
        Recurso recursoRequerido = recurso2;

        int recursos1InicialJugador1 = jugado1.cantidadDeRecurso(recurso1);
        int recursos2InicialJugador1 = jugado1.cantidadDeRecurso(recurso2);

        comercio.iniciarFase(jugado1,banca);
        comercio.crearOfertaBanca(tasaDeComercio, recursoPuertoOfrecido, recursoRequerido);

        assertEquals(recursos1InicialJugador1 -2, jugado1.cantidadDeRecurso(recurso1));
        assertEquals(recursos2InicialJugador1 + 1, jugado1.cantidadDeRecurso(recurso2));
    }
    @Test
    void test02JugadorComerciaConLaBancaMedianteLaTazagenerica(){
        Tasa tasaDeComercio = new Generico();
        List<Recurso> recursosOfrecidos = List.of(recurso2,recurso3,recurso4);
        Recurso recursoRequeridos = recurso1;

        int recursos1InicialJugador1 = jugado1.cantidadDeRecurso(recurso1);
        int recursos2InicialJugador1 = jugado1.cantidadDeRecurso(recurso2);
        int recursos3InicialJugador1 = jugado1.cantidadDeRecurso(recurso3);
        int recursos4InicialJugador1 = jugado1.cantidadDeRecurso(recurso4);

        comercio.iniciarFase(jugado1,banca);
        comercio.crearOfertaBanca(tasaDeComercio,recursosOfrecidos, recursoRequeridos);

        assertEquals(recursos1InicialJugador1 +1, jugado1.cantidadDeRecurso(recurso1));
        assertEquals(recursos2InicialJugador1 - 1, jugado1.cantidadDeRecurso(recurso2));
        assertEquals(recursos3InicialJugador1 - 1, jugado1.cantidadDeRecurso(recurso3));
        assertEquals(recursos4InicialJugador1 - 1, jugado1.cantidadDeRecurso(recurso4));
    }
}
