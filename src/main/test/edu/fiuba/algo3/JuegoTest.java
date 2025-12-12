package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Recurso.Madera;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import edu.fiuba.algo3.modelo.Turnos.Turno;
import edu.fiuba.algo3.modelo.Turnos.Fase.Fase;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class JuegoTest {

    private Jugador jugador1;
    private Jugador jugador2;
    private Turno faseInicial;
    private Fase fasePrincipal1;
    private Fase fasePrincipal2;
    private Juego juego;
    private Tablero tablero;
    private Banca banca;


    @BeforeEach
    public void setUp() {
        //jugador1 = new Jugador();
        //jugador2 = new Jugador();
        faseInicial = mock(Turno.class);
        fasePrincipal1 = mock(Fase.class);
        fasePrincipal2 = mock(Fase.class);
        tablero = mock(Tablero.class);
        List<Carta> cartas = new ArrayList<>();
        banca = Banca.crearBanca(new ArrayList<>(), cartas);
        Juego.crearInstancia(List.of(jugador1, jugador2), new Tablero(new ArrayList<Terreno>(), new ArrayList<Vertice>(), new ArrayList<Arista>()), Banca.crearBanca(List.of(new Madera()), cartas));
    }

//    @Test
//    public void test01LasFasesInicialesSeEjecutanAntesQueLasFasesPrincipales(){
//        juego.iniciarJuego();
//
//        InOrder inOrder = Mockito.inOrder(juego);
//        inOrder.verify(juego).iniciarFaseInicial();
//        inOrder.verify(juego).iniciarTurno();
//    }
//
//    @Test
//    public void test02LasFasesPrincipalesSeEjecutanUnaVezPorJugador(){
//        juego.iniciarJuego();
//
//        verify(fasePrincipal1, times(2)).iniciarFase(any(Jugador.class));
//        verify(fasePrincipal2, times(2)).iniciarFase(any(Jugador.class));
//    }

    /*
    @Test
    public void test03SeVerificaElNoDescarteDeJugadores(){
        int recursosJugador1DespuesDescarte = 5;
        int recursosJugador2DespuesDescarte = 7;

        jugador1.agregarRecursos("madera", 5);
        jugador2.agregarRecursos("madera", 7);
        juego.descarteJugadores();

        assert(recursosJugador1DespuesDescarte == jugador1.getCantidadRecursosTotales());
        assert(recursosJugador2DespuesDescarte == jugador2.getCantidadRecursosTotales());
    }

    @Test
    public void test04SeVerificaElDescarteDeJugadores(){
        int recursosJugador1DespuesDescarte = 4;
        int recursosJugador2DespuesDescarte = 5;

        jugador1.agregarRecursos("madera", 8);
        jugador2.agregarRecursos("madera", 9);

        juego.descarteJugadores();

        assert(recursosJugador1DespuesDescarte == jugador1.getCantidadRecursosTotales());
        assert(recursosJugador2DespuesDescarte == jugador2.getCantidadRecursosTotales());
    }

     */
}
