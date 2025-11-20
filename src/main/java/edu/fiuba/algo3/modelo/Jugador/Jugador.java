package edu.fiuba.algo3.modelo.Jugador;

import edu.fiuba.algo3.modelo.Recurso.GestorDeRecursos;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class Jugador {
    private GestorDeRecursos recursos;
    private Mano mano;

    public Jugador(GestorDeRecursos gestor, Mano manoInicial) {
        this.recursos = gestor;
        this.mano = manoInicial;
    }

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

}