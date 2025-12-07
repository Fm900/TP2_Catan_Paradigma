package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import edu.fiuba.algo3.modelo.Turnos.Fase.Dados;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Turnos.Normal;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TiradadDeDadosTest {
    @Test
    public void TiradadDeDadosTest(){
        /*Arrange*/
        MazoDeRecursos gestor1 = new MazoDeRecursos(new ArrayList<Recurso>());
        Mano mano1 = new Mano();
        Jugador jugador1 = new Jugador(gestor1, mano1,"Alex", Color.WHITE);
        Dados faseDeDados = new Dados();
        List<Carta> cartas = new ArrayList<>();
        Banca.reset();
        Banca banca = Banca.crearBanca(new ArrayList<Recurso>(), cartas);
        Tablero tableroVacio = new Tablero(new ArrayList<Terreno>(), new ArrayList<Vertice>(), new ArrayList<Arista>());
        Juego.reset();
        Juego.crearInstancia(List.of(jugador1), tableroVacio, banca);
        ManejoTurnos manejoTurnos = new ManejoTurnos(new ArrayList<>(List.of(jugador1)));
        manejoTurnos.cambiarTurno(new Normal());
        faseDeDados.ejecutar(jugador1, manejoTurnos);
        int tirada = faseDeDados.getTirada();
        /*Assert*/
        assert(tirada > 2 && tirada <= 12);

    }
}
