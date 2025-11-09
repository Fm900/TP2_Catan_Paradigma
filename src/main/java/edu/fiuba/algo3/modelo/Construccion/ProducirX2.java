package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Jugador;

public class ProducirX2 implements Producir {
    private Integer coeficienteDeConstruccion;
    public ProducirX2() {
        coeficienteDeConstruccion = 2;
    }
    public void producir(String recurso, Jugador dueño){
        dueño.agregarRecursos(recurso,coeficienteDeConstruccion);
    }
}
