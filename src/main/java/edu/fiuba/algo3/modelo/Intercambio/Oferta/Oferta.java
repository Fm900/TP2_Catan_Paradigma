package edu.fiuba.algo3.modelo.Intercambio.Oferta;

import edu.fiuba.algo3.modelo.Intercambio.Intercambio;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public class Oferta<T>{
    private Resolucion estado;
    private Intercambio intercambio;

    public Oferta(Intercambio tipoIntercambio) {
        this.estado = new Pendiente();
        this.intercambio = tipoIntercambio;
    }
    public void acepatar(){
        estado.aceptar(this);
    }
    public void declinar(){
        estado.declinar(this);
    }
    public boolean estaPendiente() { return estado.estaPendiente(); }
    public boolean fueAceptada() { return estado.fueAceptada(); }
    public boolean fueRechazada() { return estado.fueRechazada(); }

    void setEstado(Resolucion nuevoEstado) {
        this.estado = nuevoEstado;
    }
    void ejecutarIntercambio() {
        intercambio.intercambio();
    }

}