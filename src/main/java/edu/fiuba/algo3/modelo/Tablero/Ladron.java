package edu.fiuba.algo3.modelo.Tablero;

public class Ladron {
    Terreno terrenoactual;

    public Ladron(Terreno terreno){
        this.terrenoactual = terreno;
    }

    public void moverADestino(Terreno terreno){
        terrenoactual.cambiarEstado();
        terreno.cambiarEstado();
        this.terrenoactual = terreno;
    }
}
