package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Exception.CartaDeshabilitada;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Deshabilitado;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Monopolio;
import edu.fiuba.algo3.modelo.Jugador.Cartas.ParametrosCarta;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.Madera;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CartaNoPuedeSerJugadaRecienAdquiridaTest {


    @Test
    void test01CartaRecienLevantadaNoPuedeJugarse() {
        Banca.reset();
        MazoDeRecursos gestor = new MazoDeRecursos(new ArrayList<Recurso>());
        Mano mano = new Mano();
        Jugador jugadorQueLevanta = new Jugador(gestor,mano,"Alex", Color.WHITE);
        Carta cartaRecienLevantada = new Monopolio((new Deshabilitado()));
        jugadorQueLevanta.agregarCarta(cartaRecienLevantada);
        ParametrosCarta parametrosCarta = new ParametrosCarta();
        parametrosCarta.setRecursoMonopolio(new Madera());

        assertThrows(CartaDeshabilitada.class, () -> cartaRecienLevantada.intentarActivarEfecto(jugadorQueLevanta, parametrosCarta), "Este turno no es posible usar esta carta");
    }
    }
