package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Exception.CartaDeshabilitada;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Deshabilitado;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Monopolio;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CartaNoPuedeSerJugadaRecienAdquiridaTest {


    @Test
    void test01CartaRecienLevantadaNoPuedeJugarse() {

        MazoDeRecursos gestor = new MazoDeRecursos(new ArrayList<Recurso>());
        Mano mano = new Mano();
        Jugador jugadorQueLevanta = new Jugador(gestor,mano);
        Carta cartaRecienLevantada = new Monopolio((new Deshabilitado()));
        jugadorQueLevanta.agregarCarta(cartaRecienLevantada);


        assertThrows(CartaDeshabilitada.class, () -> cartaRecienLevantada.intentarActivarEfecto(jugadorQueLevanta), "Este turno no es posible usar esta carta");
    }
    }
