package edu.fiuba.algo3.modelo.Intercambio.Oferta;

public class Aceptado implements Resolucion{
    public void aceptar(Oferta oferta) { }
    public void declinar(Oferta oferta) { }
    public boolean estaPendiente() { return false; }
    public boolean fueAceptada() { return true; }
    public boolean fueRechazada() { return false; }
}
