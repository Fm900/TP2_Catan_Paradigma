package edu.fiuba.algo3.modelo.Tablero;

import java.util.List;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Excepciones.VerticeOcupadoNoPuedeConstruir;
import edu.fiuba.algo3.modelo.Jugador;

public class Ocupado implements EstadoVertice {
    private final Construccion construccion;

    public Ocupado(Construccion construccion) {
        this.construccion = construccion;
    }

    @Override
    public void construirPoblado(Vertice self, Jugador jugador) {
        throw new VerticeOcupadoNoPuedeConstruir("No puedes construir, el vertice ya esta ocupado");
    }

    @Override
    public void entregarRecursosPorConstruccion(String recurso) {
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
}
