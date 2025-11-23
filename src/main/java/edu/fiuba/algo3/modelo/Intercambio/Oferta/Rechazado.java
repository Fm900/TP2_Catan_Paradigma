package edu.fiuba.algo3.modelo.Intercambio.Oferta;

public class Rechazado implements Resolucion{
    public void aceptar(Oferta oferta) {
        oferta.setEstado(new Aceptado());
        oferta.ejecutarIntercambio();
    }
    public void declinar(Oferta oferta) {
        oferta.setEstado(new Rechazado());
    }
    public boolean estaPendiente() { return false; }
    public boolean fueAceptada() { return false; }
    public boolean fueRechazada() { return true; }
}
