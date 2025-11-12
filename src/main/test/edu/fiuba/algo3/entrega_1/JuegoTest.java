package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Fase.FaseInicial;
import edu.fiuba.algo3.modelo.Fase.FasePrincipal;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;

public class JuegoTest {

    private Jugador jugador1;
    private Jugador jugador2;
    private FaseInicial faseInicial;
    private FasePrincipal fasePrincipal1;
    private FasePrincipal fasePrincipal2;
    private Juego juego;


    @BeforeEach
    public void setUp() {
        jugador1 = new Jugador();
        jugador2 = new Jugador();
        faseInicial = mock(FaseInicial.class);
        fasePrincipal1 = mock(FasePrincipal.class);
        fasePrincipal2 = mock(FasePrincipal.class);
        juego = spy(new Juego(List.of(jugador1,jugador2), List.of(fasePrincipal1, fasePrincipal2), List.of(faseInicial)));
    }

    @Test
    public void test01LasFasesInicialesSeEjecutanAntesQueLasFasesPrincipales(){
        juego.iniciarJuego();

        InOrder inOrder = Mockito.inOrder(juego);
        inOrder.verify(juego).iniciarFaseInicial();
        inOrder.verify(juego).iniciarTurno();
    }

    @Test
    public void test02LasFasesPrincipalesSeEjecutanUnaVezPorJugador(){
        juego.iniciarJuego();

        verify(fasePrincipal1, times(2)).iniciarFase(any(Jugador.class));
        verify(fasePrincipal2, times(2)).iniciarFase(any(Jugador.class));
    }
}
