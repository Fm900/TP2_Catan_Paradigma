package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Fase.Dados;
import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Test;

public class TiradadDeDadosTest {
    @Test
    public void TiradadDeDadosTest(){
        /*Arrange*/
        Jugador jugador1 = new Jugador();
        Dados faseDeDados = new Dados();
        /*Act*/
        faseDeDados.iniciarFase(jugador1);
        int tirada = faseDeDados.getTirada();
        /*Assert*/
        assert(tirada > 2 && tirada <= 12);

    }
}
