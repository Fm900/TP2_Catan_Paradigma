package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

public class ProducirX2 implements Producir {
    private Integer coeficienteDeConstruccion;
    public ProducirX2() {
        coeficienteDeConstruccion = 2;
    }
    public void producir(Recurso recurso, Jugador dueño){
        dueño.agregarRecurso(recurso, coeficienteDeConstruccion);
    }
}
