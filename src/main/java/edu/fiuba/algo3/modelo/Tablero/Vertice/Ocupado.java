package edu.fiuba.algo3.modelo.Tablero.Vertice;

import java.util.List;

import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Exception.NoSePuedeMejorarACiudad;
import edu.fiuba.algo3.modelo.Exception.VerticeOcupadoNoPuedeConstruir;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;

public class Ocupado implements EstadoVertice {
    private Construccion construccion;

    public Ocupado(Construccion construccion) {
        this.construccion = construccion;
    }

    @Override
    public void construirPobladoInicial(Vertice self, Jugador jugador, List<Arista> aristas) {
        throw new VerticeOcupadoNoPuedeConstruir("No puedes construir, el vertice ya esta ocupado");
    }

    @Override
    public void construirPoblado(Vertice self, Jugador jugador, List<Arista> aristas) {
        throw new VerticeOcupadoNoPuedeConstruir("No puedes construir, el vertice ya esta ocupado");
    }

    @Override
    public void entregarRecursosPorConstruccion(Recurso recurso) {
        construccion.producirRecurso(recurso);
    }

    @Override
    public boolean validarConstruccionEnVecino() {
        return false;
    }

    @Override
    public void mejorarPobladoACiudad(Vertice self, Jugador jugador) {
        if (construccion.getDueño() != jugador){
            throw new NoSePuedeMejorarACiudad("No sos dueño de este poblado, no lo podes mejorar.");
        }
        construccion.quitarPuntos(jugador);
        Construccion ciudad = new Ciudad(2, 2, jugador);
        ciudad.construir();
        this.construccion = ciudad;
    }


}
