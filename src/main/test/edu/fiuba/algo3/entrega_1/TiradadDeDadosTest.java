package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Fase.Dados;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.GestorDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TiradadDeDadosTest {
    @Test
    public void TiradadDeDadosTest(){
        /*Arrange*/
        GestorDeRecursos gestor1 = new GestorDeRecursos(new ArrayList<Recurso>());
        Mano mano1 = new Mano();
        Jugador jugador1 = new Jugador(gestor1, mano1);
        Dados faseDeDados = new Dados();
        /*Act*/
        faseDeDados.iniciarFase(jugador1);
        int tirada = faseDeDados.getTirada();
        /*Assert*/
        assert(tirada > 2 && tirada <= 12);

    }
}
