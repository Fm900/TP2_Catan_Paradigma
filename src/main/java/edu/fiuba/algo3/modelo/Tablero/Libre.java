package edu.fiuba.algo3.modelo.Tablero;

import java.util.List;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Construccion.Poblado;
import edu.fiuba.algo3.modelo.Excepciones.ReglaDeDistanciaNoValida;
import edu.fiuba.algo3.modelo.Jugador;

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
        Construccion poblado = Poblado.construir(jugador);
        self.cambiarAOcupado(poblado);

    }

    @Override
    public void entregarRecursosPorConstruccion(String recurso) {
        // no tiene construccion asi que no hace nada
    }

    @Override
    public boolean validarConstruccionEnVecino() {
        return true;
    }

    public List<Jugador> agregarPropietario(List<Jugador> propietarios) {
        return propietarios;
    }
}
