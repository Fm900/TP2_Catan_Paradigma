package edu.fiuba.algo3.modelo.Jugador;

import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.GestorDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class Jugador {
    private GestorDeRecursos recursos;
    private Mano mano;
    private int puntos = 0;

    public Jugador(GestorDeRecursos gestor, Mano manoInicial) {
        this.recursos = gestor;
        this.mano = manoInicial;
    }

    //para recursos
    public void descarteMayoria() {
        int cantidadRecursosADescartar = recursos.cantidadDescartar();
        recursos.descartarPorCantidad(cantidadRecursosADescartar);
    }

    public void agregarRecurso(@NotNull Recurso recurso, int cantidad) {
        recurso.agregar(cantidad, recursos);
    }

    public void consumirRecursos(List<Recurso> precio) {
        recursos.verificarCumplimiento(precio);
        for(Recurso recurso: precio){
            recurso.eliminar(recursos);
        }
    }
    // para puntos de juego
    public Recurso obtenerRecursoAleatorio() {
        return recursos.obtenerRecursoAleatorio();
    }

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