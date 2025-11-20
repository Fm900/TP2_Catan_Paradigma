package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Banca;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

public class Bancario implements Intercambiar{
    private Jugador cliente;
    private Banca banca;
    private Recurso recursoRequerido;
    private Recurso recursoOfrecido;
    private int tazaDeComercio;

    @Override
    public void intercambio() {
        // agregar y sacar recurso Ofrecido
        // agregar y sacar recurso Requerido

    }
    public Bancario(Jugador cliente, Recurso recursoRequerido, Recurso recursoOfrecido, int taza, Banca banca){
        this.cliente = cliente;
        this.banca = banca;
        this.recursoRequerido = recursoRequerido;
        this.recursoOfrecido = recursoOfrecido;
        this.tazaDeComercio = taza;
    }


}
