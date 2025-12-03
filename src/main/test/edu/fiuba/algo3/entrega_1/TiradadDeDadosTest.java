package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Fase.Dados;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TiradadDeDadosTest {
    @Test
    public void TiradadDeDadosTest(){
        /*Arrange*/
        MazoDeRecursos gestor1 = new MazoDeRecursos(new ArrayList<Recurso>());
        Mano mano1 = new Mano();
        Jugador jugador1 = new Jugador(gestor1, mano1,"Alex");
        Dados faseDeDados = new Dados();
        List<Carta> cartas = new ArrayList<>();
        Banca banca = Banca.crearBanca(new ArrayList<Recurso>(), cartas);
        /*Act*/
        faseDeDados.iniciarFase(jugador1);
        int tirada = faseDeDados.getTirada();
        /*Assert*/
        assert(tirada > 2 && tirada <= 12);

    }
}
