package edu.fiuba.algo3.modelo.Tablero.Arista;

import edu.fiuba.algo3.modelo.Construccion.Carretera;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Exception.NoSePuedeConstruirPorFaltaDeConexion;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

import java.util.List;


public class Vacia implements EstadoArista {
    @Override
    public void construirCarretera(Arista self, Jugador jugador, List<Vertice> vertices) {
        for (Vertice v : vertices){
            if (v.validarConexion(jugador)) {
                Construccion carrtera = new Carretera(0, 0, jugador);
                carrtera.construir();
                self.cambiarAOcupada(jugador);
                return;
            }
        }
        throw new NoSePuedeConstruirPorFaltaDeConexion("No existe conexión con el jugador");
    }
}
