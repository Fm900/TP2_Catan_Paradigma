package edu.fiuba.algo3.modelo.Intercambio.Oferta;

public interface Resolucion {
    void aceptar(Oferta oferta);
    void declinar(Oferta oferta);
    boolean estaPendiente();
    boolean fueAceptada();
    boolean fueRechazada();
}
