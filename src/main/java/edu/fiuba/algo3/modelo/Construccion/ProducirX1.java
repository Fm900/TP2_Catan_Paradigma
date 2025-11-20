package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

public class ProducirX1 implements Producir {
    private Integer coeficienteDeConstruccion;
    public ProducirX1() {
        coeficienteDeConstruccion = 1;
    }
    public void producir(Recurso recurso, Jugador dueño){
        dueño.agregarRecurso(recurso, coeficienteDeConstruccion);
    }
}
