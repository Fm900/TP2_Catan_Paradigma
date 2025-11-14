package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice;

import java.util.List;

public class PrimerTurno implements FaseInicial {

    public void iniciarFase(List<Jugador> jugadores, Tablero tablero) {
        for(Jugador jugador: jugadores){
            construirPoblado(jugador,  tablero);
            //construirCarretera(jugador, tablero);
        }
    }

    private void construirCarretera(Jugador jugador, Tablero tablero) {
        //
    }

    public void construirPoblado(Jugador jugador, Tablero tablero){
        agregarRecursosParaPoblado(jugador);
        Vertice vertice = elegirVertice();
        tablero.colocarPoblado(jugador, vertice);
    }

    public void agregarRecursosParaPoblado(Jugador jugador){
        jugador.agregarRecursos("madera", 1);
        jugador.agregarRecursos("ladrillo", 1);
        jugador.agregarRecursos("lana", 1);
        jugador.agregarRecursos("grano", 1);
    }

    public Vertice elegirVertice(){
        // Se elige el vertice
        Vertice vertice = new Vertice();
        return vertice;
    }
}
