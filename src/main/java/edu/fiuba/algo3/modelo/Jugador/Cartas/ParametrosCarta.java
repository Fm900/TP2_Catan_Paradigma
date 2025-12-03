package edu.fiuba.algo3.modelo.Jugador.Cartas;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import java.util.List;
import java.util.ArrayList;

public class ParametrosCarta {

    private Terreno terrenoDestino;
    private Jugador victima;

    private List<Recurso> recursosElegidos = new ArrayList<>();

    private List<Arista> aristas = new ArrayList<>();

    private Recurso recursoMonopolio;


    public List<Recurso> getRecursosElegidos() {
        return recursosElegidos;
    }

    public void setRecursosElegidos(List<Recurso> recursosElegidos) {
        this.recursosElegidos = recursosElegidos;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public void setAristas(List<Arista> aristas) {
        this.aristas = aristas;
    }

    public Terreno getTerrenoDestino() {
        return terrenoDestino;
    }

    public void setTerrenoDestino(Terreno terrenoDestino) {
        this.terrenoDestino = terrenoDestino;
    }

    public Jugador getVictima() {
        return victima;
    }

    public void setVictima(Jugador victima) {
        this.victima = victima;
    }

    public Recurso getRecursoMonopolio() {
        return recursoMonopolio;
    }

    public void setRecursoMonopolio(Recurso recursoMonopolio) {
        this.recursoMonopolio = recursoMonopolio;
    }
}
