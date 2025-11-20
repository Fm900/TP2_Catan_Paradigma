package edu.fiuba.algo3.modelo.Tablero.Vertice;

import java.util.List;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

public interface EstadoVertice  {
    void construirPoblado (Vertice self, Jugador jugador);
    void entregarRecursosPorConstruccion(Recurso recurso);
    boolean validarConstruccionEnVecino();
    List<Jugador> agregarPropietario(List<Jugador> propietarios);
    void mejorarPobladoACiudad(Vertice self, Jugador jugador);
}

