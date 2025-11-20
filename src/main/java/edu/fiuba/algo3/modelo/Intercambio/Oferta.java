package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public class Oferta implements Intercambiar {
    private final Jugador emisor;
    private final List<Recurso> recursosRequeridos;
    private Jugador receptor;
    private List<Recurso> recursosOfrecidos;
    private boolean activa;

    public Oferta(Jugador jugador, List<Recurso> recursosRequeridos) {
        this.emisor = jugador;
        this.recursosRequeridos = recursosRequeridos;
        this.receptor = null;
        this.recursosOfrecidos = null;
        this.activa = true;
    }

    @Override
    public void intercambio() {
        // Transferir recursos requeridos del receptor al emisor
        receptor.consumirRecursos(recursosRequeridos);
        for (Recurso recurso : recursosRequeridos) {
            emisor.agregarRecurso(recurso, 1);
        }

        // Transferir recursos ofrecidos del emisor al receptor
        emisor.consumirRecursos(recursosOfrecidos);
        for (Recurso recurso : recursosOfrecidos) {
            receptor.agregarRecurso(recurso, 1);
        }
        activa = false;
    }

    public boolean recibirOrferta(Jugador receptor,List<Recurso> recursosOfrecidos){
        if(!activa){
            return false;
        }
        this.recursosOfrecidos = recursosOfrecidos;
        this.receptor = receptor;
        intercambio();
        return true;
    }

    public void cancelarOferta() {
        this.activa = false;
    }

}
