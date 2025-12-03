package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Fase.FaseInicial;
import edu.fiuba.algo3.modelo.Fase.FasePrincipal;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class JuegoTest {

    private Jugador jugador1;
    private Jugador jugador2;
    private FaseInicial faseInicial;
    private FasePrincipal fasePrincipal1;
    private FasePrincipal fasePrincipal2;
    private Juego juego;
    private Tablero tablero;
    private Banca banca;


    @BeforeEach
    public void setUp() {
        //jugador1 = new Jugador();
        //jugador2 = new Jugador();
        faseInicial = mock(FaseInicial.class);
        fasePrincipal1 = mock(FasePrincipal.class);
        fasePrincipal2 = mock(FasePrincipal.class);
        tablero = mock(Tablero.class);
        banca = Banca.creacBanca(new ArrayList<>());
        juego = spy(Juego.crearInstancia(List.of(jugador1,jugador2), List.of(fasePrincipal1, fasePrincipal2), List.of(faseInicial), tablero, banca));
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
