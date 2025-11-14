package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Jugador;

public class ProducirX1 implements Producir {
    private Integer coeficienteDeConstruccion;
    public ProducirX1() {
        coeficienteDeConstruccion = 1;
    }
    public void producir(String recurso, Jugador dueño){
        dueño.agregarRecursos(recurso,coeficienteDeConstruccion);
    }
}
