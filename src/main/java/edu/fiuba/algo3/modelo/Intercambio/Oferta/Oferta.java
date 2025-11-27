package edu.fiuba.algo3.modelo.Intercambio.Oferta;

import edu.fiuba.algo3.modelo.Intercambio.Intercambio;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public class Oferta{
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

    void setEstado(Resolucion nuevoEstado) {
        this.estado = nuevoEstado;
    }
    void ejecutarIntercambio() {
        intercambio.intercambio();
    }
    public Resolucion getEstado() {
        return estado;
    }

}