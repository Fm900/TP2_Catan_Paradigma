package edu.fiuba.algo3.modelo.Tablero.Puerto;

import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;

public class Puerto {
    private Tasa tasaDeComercio;
    private Recurso recurso;

    //para puertos especificos
    public Puerto(Recurso recurso, Arista arista) {
        this.recurso = recurso;
        this.tasaDeComercio = new Especifico(recurso);
    }
    //para puetos genericos
    public Puerto(Arista arista){
        this.tasaDeComercio = new Generico();
    }

    public Tasa getTasaDeComercio() {
        return tasaDeComercio;
    }
}
