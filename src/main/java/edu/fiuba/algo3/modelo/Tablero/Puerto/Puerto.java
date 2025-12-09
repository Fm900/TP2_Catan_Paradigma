package edu.fiuba.algo3.modelo.Tablero.Puerto;

import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;

public class Puerto {
    private Tasa tasaDeComercio;
    private Recurso recurso;
    private Arista arista;


    public Puerto(Recurso recurso, Arista arista) {
        this.recurso = recurso;
        this.arista = arista;
        this.tasaDeComercio = new Especifico(recurso);
    }

    public Puerto(Arista arista){
        this.arista = arista;
        this.tasaDeComercio = new Generico();
    }

    public Tasa getTasaDeComercio() {
        return tasaDeComercio;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public Arista getArista() {
        return arista;
    }
}
