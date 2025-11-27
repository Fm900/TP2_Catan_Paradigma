package edu.fiuba.algo3.modelo.Jugador;

import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public class Jugador {
    private final MazoDeRecursos recursos;
    private final Mano mano;
    private int puntos = 0;
    private final String nombre;

    public Jugador(MazoDeRecursos gestor, Mano manoInicial, String nombre) {
        this.recursos = gestor;
        this.mano = manoInicial;
        this.nombre = nombre;
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

    public void sumarPuntos(int puntos){
        this.puntos += puntos;
    }

    public void restarPuntos(int puntos){
        this.puntos -= puntos;
    }

    public int calcularPuntosTotales(){
        return this.puntos; //por ahora tiene solo esto, probablemente mas adelante se expanda
    }

    // para cartas de desarrollo
    public void agregarCarta(Carta carta) {
        mano.agregar(carta);
    }

    public void descartarCarta(Carta carta) {
        mano.descartar(carta);
    }
}