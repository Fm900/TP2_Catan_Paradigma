package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Fase.FasePrincipal;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Turno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.mock;


public class TurnoTest {
    private Jugador jugador;
    private FasePrincipal fasePrincipal1;
    private FasePrincipal fasePrincipal2;
    private Banca banca;

    @BeforeEach
    public void setUp() {
        //jugador = new Jugador();
        fasePrincipal1 = mock(FasePrincipal.class);
        fasePrincipal2 = mock(FasePrincipal.class);
        List<Carta> cartas = new ArrayList<>();
        this.banca = Banca.crearBanca(new ArrayList<Recurso>(), cartas);

    }

    @Test
    public void test01TurnoEjecutaTodasLasFasesYEnOrden(){
        Turno turno = new Turno(List.of(fasePrincipal1,fasePrincipal2), jugador);
        turno.iniciarTurno();
        InOrder inOrder = Mockito.inOrder(fasePrincipal1,fasePrincipal2);
        inOrder.verify(fasePrincipal1).iniciarFase(jugador);
        inOrder.verify(fasePrincipal2).iniciarFase(jugador);
    }
}
