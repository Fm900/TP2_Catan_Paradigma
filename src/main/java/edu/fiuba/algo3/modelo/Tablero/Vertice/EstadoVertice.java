package edu.fiuba.algo3.modelo.Tablero.Vertice;

import java.util.List;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;

public interface EstadoVertice  {
    void construirPobladoInicial(Vertice self, Jugador jugador, List<Arista> aristas);
    void construirPoblado (Vertice self, Jugador jugador, List<Arista> aristas);
    void entregarRecursosPorConstruccion(Recurso recurso);
    boolean validarConstruccionEnVecino();
    List<Jugador> agregarPropietario(List<Jugador> propietarios);
    void mejorarPobladoACiudad(Vertice self, Jugador jugador);
}

