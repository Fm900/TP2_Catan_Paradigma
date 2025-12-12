package edu.fiuba.algo3.modelo.Jugador;

import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;

import java.util.ArrayList;
import java.util.List;

public class Mano {
    private int caballeros = 0;

    private List<Carta> cartas;

    public Mano () {
        this.cartas = new ArrayList<Carta>();
    }

    public void agregar(Carta carta) {
        this.cartas.add(carta);
    }

    public void descartar(Carta carta){
        this.cartas.remove(carta);
    }

    public int cantidadCartas(){
        return this.cartas.size();
    }

    public void agregarCaballero(){
        this.caballeros = this.caballeros + 1;
    }

    public int obtenerCantidadCaballeros() {
        return caballeros;
    }

    public List<Carta> dameLasCartas() {
        return this.cartas;
    }

    public void habilitarCartas() {
        for (Carta carta : this.cartas) {
            carta.cambiarAHabilitada();
        }
    }
}
