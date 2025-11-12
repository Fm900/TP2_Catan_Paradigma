package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Construccion.Poblado;
import edu.fiuba.algo3.modelo.Jugador;

public class Libre implements EstadoVertice {

    @Override
    public void construirPoblado(Vertice self, Jugador jugador) {
        //Regla de distancia
        boolean sePuede = true;
        for (Arista arista : self.aristas()) {
            Vertice vecino = arista.otroExtremo(self);
            sePuede = vecino.estado.validarConstruccionEnVecino();
        }

        // si nadie bloquea, crear construcción y cambiar de estado
        if (sePuede) {
            Construccion poblado = Poblado.construir(jugador);
            self.cambiarAOcupado(poblado);
        }
    }

    @Override
    public void entregarRecursosPorConstruccion(String recurso) {
        // no tiene construccion asi que no hace nada
    }

    @Override
    public boolean validarConstruccionEnVecino() {
        return true;
    }
}
