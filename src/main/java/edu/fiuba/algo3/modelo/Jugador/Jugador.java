package edu.fiuba.algo3.modelo.Jugador;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Recurso.*;

import java.util.ArrayList;
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
        Juego.getInstancia().chequearVictoria(this);
    }

    public void restarPuntos(int puntos){
        this.puntos -= puntos;
    }

    public int calcularPuntosTotales(){
        return this.puntos;
    }
    public int cantidadDeRecurso(Recurso recurso){
        return recurso.getCantidad(recursos);
    }
    public List<Integer> obtenerCantidadDeRecursos(){
        List<Integer> respuesta = new ArrayList<>();
        List<Recurso> recursos = new ArrayList<>(List.of(new Madera(),new Grano(),new Ladrillo(), new Lana(), new Mineral()));
        for (Recurso recurso : recursos) {
            respuesta.add(cantidadDeRecurso(recurso));
        }
        return respuesta;
    }
    // para cartas de desarrollo
    public void agregarCarta(Carta carta) {
        mano.agregar(carta);
    }

    public void descartarCarta(Carta carta) {
        mano.descartar(carta);
    }

    public String obtenerNombre() {
        return nombre;
    }

}