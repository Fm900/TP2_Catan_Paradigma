package edu.fiuba.algo3.modelo.Tablero.Arista;

import edu.fiuba.algo3.modelo.Exception.NoSePuedeConstruirPorFaltaDeConexion;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

import java.util.List;


public class Vacia implements EstadoArista {
    @Override
    public void construirCarretera(Arista self, Jugador jugador, List<Vertice> vertices) {
        for (Vertice v : vertices){
            try {
                v.validarConexion(jugador);
                self.construirCarretera(jugador);
                return;

            } catch (NoSePuedeConstruirPorFaltaDeConexion ignored) {
            }
        }

        throw new NoSePuedeConstruirPorFaltaDeConexion("No existe conexión con el jugador");
    }
}
