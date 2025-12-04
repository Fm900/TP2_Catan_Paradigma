package edu.fiuba.algo3.modelo.Turnos;

import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

public class Segundo implements Turno {

    private int indice = 0;

    @Override
    public Jugador jugadorActual(ManejoTurnos manejador) {
        return manejador.jugadoresInv.get(indice);
    }

    @Override
    public void siguiente(ManejoTurnos manejador) {
        indice++;

        // CUANDO TODOS JUGARON, PASA AL TURNO NORMAL
        if (indice == manejador.getJugadoresInv().size()) {
            manejador.cambiarTurno(new Normal());
        }
    }

    public void construir(ManejoTurnos manejador, Vertice vertice, Arista arista){
        Tablero tablero = Juego.getInstancia().getTablero();
        Jugador jugador = jugadorActual(manejador);

        tablero.colocarPobladoInicial(jugador, vertice);
        tablero.colocarCarretera(jugador, arista);

        for (Terreno terreno : tablero.obtenerTerrenosAdy(vertice)){
            jugador.agregarRecurso(terreno.recursoInicial(), 1);
        }
    }
}
