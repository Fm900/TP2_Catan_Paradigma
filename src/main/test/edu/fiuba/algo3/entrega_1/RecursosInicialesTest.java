package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Fase.Dados;
import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Test;

public class RecursosInicialesTest {
    @Test
    public void testRecursosInicialesCorrespondientes() {
        /*Arrange*/
        Jugador jugador1 = new Jugador();
        Dados faseDados = new Dados();
        /*Act*/
        faseDados.iniciarFase(jugador1);
        int cantidadDeRecursosObtenida;
        cantidadDeRecursosObtenida = jugador1.getCantidadRecursosTotales();
        /*Assert*/
        assert(cantidadDeRecursosObtenida > 0);
    }
}
