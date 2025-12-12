package edu.fiuba.algo3.modelo.Jugador;

import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public class Jugador {
    private MazoDeRecursos recursos;
    private Mano mano;
    private int puntos = 0;

    public Jugador(MazoDeRecursos gestor, Mano manoInicial) {
        this.recursos = gestor;
        this.mano = manoInicial;
    }

    //para recursos
    public void descarteMayoria() {
        int cantidadRecursosADescartar = recursos.cantidadDescartar();
        recursos.descartarPorCantidad(cantidadRecursosADescartar);
    }

    public void agregarRecurso(Recurso recurso, int cantidad) {
        recurso.agregar(cantidad, recursos);
    }

    public void consumirRecursos(List<Recurso> precio) {
        recursos.verificarCumplimiento(precio);
        for(Recurso recurso: precio){
            recurso.eliminar(recursos);
        }
    }

    public Recurso obtenerRecursoAleatorio() {
        return recursos.obtenerRecursoAleatorio();
    }

    public int cantidadDeRecurso(Recurso recurso) {return recursos.cantidaDeRecurso(recurso);}
    // para puntos de juego

    public void sumarPuntos(int puntos){
        this.puntos += puntos;
    }

    public void restarPuntos(int puntos){
        this.puntos -= puntos;
    }

    public int calcularPuntosTotales(){
        int puntosTotales = this.puntos; //por ahora tiene solo esto, probablemente mas adelante se expanda
        return puntosTotales;
    }

    // para cartas de desarrollo
    public void agregarCarta(Carta carta) {
        mano.agregar(carta);
    }

    public void descartarCarta(Carta carta) {
        mano.descartar(carta);
    }
}