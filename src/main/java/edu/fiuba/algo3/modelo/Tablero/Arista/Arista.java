package edu.fiuba.algo3.modelo.Tablero.Arista;

import edu.fiuba.algo3.modelo.Construccion.Carretera;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Exception.NoSePuedeConstruirElJugadorNoEsDueñoDeLaAristaAdyacente;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Puerto;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import java.util.List;

public class Arista {
    private final Vertice extremo1;
    private final Vertice extremo2;
    private EstadoArista estado;
    private Jugador dueño;
    private Puerto puerto;

    public Arista(Vertice a, Vertice b, EstadoArista estado){
        this.extremo1 = a;
        this.extremo2 = b;
        this.estado = estado;
    }
    public void agregarPuerto(Puerto puerto){
        this.puerto = puerto;
    }

    public Arista obtenerme(){
        return this;
    }

    public Vertice otroExtremo(Vertice vertice) {
        if (vertice == extremo1) return extremo2;
        if (vertice == extremo2) return extremo1;
        throw new IllegalStateException("El vertice no pertenece a la arista");
    }

    public void cambiarAOcupada(){
        this.estado = new Ocupada();
    }

    public boolean elMismoDueño(Jugador jugador){
        return (this.dueño == jugador);
    }

    public void verificarLogicaDeConstruccionDeAristas(Jugador jugador) {
        estado.construirCarretera(this, jugador, List.of(extremo1, extremo2));
    }

    public void construirCarretera(Jugador jugador) {
        Construccion carrtera = new Carretera(0, 0, jugador);
        carrtera.construir();
        this.dueño = jugador;
        this.cambiarAOcupada();
    }
    public Vertice extremo1() {
        return extremo1;
    }

    public Vertice extremo2() {
        return extremo2;
    }

}