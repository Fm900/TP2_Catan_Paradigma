package edu.fiuba.algo3.modelo.Intercambio.Oferta;

public class Pendiente implements Resolucion{

    public void aceptar(Oferta oferta) {
        oferta.setEstado(new Aceptado());
        oferta.ejecutarIntercambio();
    }

    public void declinar(Oferta oferta) {
        oferta.setEstado(new Rechazado());
    }
}
