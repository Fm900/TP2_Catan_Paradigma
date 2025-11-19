package edu.fiuba.algo3.modelo.Tablero;

import java.util.List;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Exception.Excepciones.NoSePuedeMejorarACiudad;
import edu.fiuba.algo3.modelo.Exception.Excepciones.VerticeOcupadoNoPuedeConstruir;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

public class Ocupado implements EstadoVertice {
    private Construccion construccion;

    public Ocupado(Construccion construccion) {
        this.construccion = construccion;
    }

    @Override
    public void construirPoblado(Vertice self, Jugador jugador) {
        throw new VerticeOcupadoNoPuedeConstruir("No puedes construir, el vertice ya esta ocupado");
    }

    @Override
    public void entregarRecursosPorConstruccion(Recurso recurso) {
        // delega en la construcción
        construccion.producirRecurso(recurso);
    }

    @Override
    public boolean validarConstruccionEnVecino() {
        return false;
    }

    public List<Jugador> agregarPropietario(List<Jugador> propietarios) {
        propietarios = (this.construccion).agregarPropietario(propietarios);
        return propietarios;
    }

    @Override
    public void mejorarPobladoACiudad(Vertice self, Jugador jugador) {
        //chequeo el dueño
        if (construccion.getDueño() != jugador){
            throw new NoSePuedeMejorarACiudad("No sos dueño de este poblado, no lo podes mejorar.");
        }
        this.construccion = this.construccion.mejorarACiudad();
    }


}
