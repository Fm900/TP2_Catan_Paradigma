package edu.fiuba.algo3.modelo.Tablero.Vertice;

import java.util.List;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Construccion.Poblado;
import edu.fiuba.algo3.modelo.Exception.NoSePuedeMejorarACiudad;
import edu.fiuba.algo3.modelo.Exception.ReglaDeDistanciaNoValida;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;

public class Libre implements EstadoVertice {

    @Override
    public void construirPoblado(Vertice self, Jugador jugador) {
        //Regla de distancia
        for (Arista arista : self.aristas()) {
            Vertice vecino = arista.otroExtremo(self);
            if (!vecino.validarConstruccionEnVecino()) {
                throw new ReglaDeDistanciaNoValida("No se puede construir un poblado en este vértice");
            }
        }
        // si nadie bloquea, crear construcción y cambiar de estado

        Construccion poblado = new Poblado(1, 1, jugador);
        poblado.construir();
        self.cambiarAOcupado(poblado);
    }

    @Override
    public void entregarRecursosPorConstruccion(Recurso recurso) {
        // no tiene construccion asi que no hace nada
    }

    @Override
    public boolean validarConstruccionEnVecino() {
        return true;
    }

    public List<Jugador> agregarPropietario(List<Jugador> propietarios) {
        return propietarios;
    }

    @Override
    public void mejorarPobladoACiudad(Vertice self, Jugador jugador) {
        throw new NoSePuedeMejorarACiudad("No hay poblado para mejorar en este vertice.");
    }
}
