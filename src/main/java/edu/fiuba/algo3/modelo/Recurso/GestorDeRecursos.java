package edu.fiuba.algo3.modelo.Recurso;


import edu.fiuba.algo3.modelo.Exception.NoTieneRecursosSuficientesParaDescartar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GestorDeRecursos {
    private List<Recurso> recursos;

    public GestorDeRecursos(List<Recurso> recursosIniciales) {
        this.recursos = recursosIniciales;
    }

    public int cantidadDescartar() {
        if (recursos.size() > 7) {
            return (recursos.size() / 2);
        }
        throw new NoTieneRecursosSuficientesParaDescartar("No llega a la cantidad de 7 recursos");
    }

    public void descartarPorCantidad(int cantidad) {
        recursos.subList(0, cantidad).clear();
    }

    public void agregarLana(Recurso recurso, int cantidad) {
        for(int i = 0; i < cantidad; i++){
            recursos.add(recurso);
        }
    }

    public void removerLana(Recurso recurso){
        recursos.remove(recurso);
    }

    public void agregarGrano(Recurso recurso, int cantidad) {
        for(int i = 0; i < cantidad; i++){
            recursos.add(recurso);
        }
    }

    public void removerGrano(Recurso recurso){
        recursos.remove(recurso);
    }

    public void agregarLadrillo(int cantidad, Recurso recurso) {
        for(int i = 0; i < cantidad; i++)
        {recursos.add(recurso);
        }
    }

    public void removerLadrillo(Recurso recurso) {
        recursos.remove(recurso);
    }

    public void agregarMadera(int cantidad, Recurso recurso) {
        for(int i = 0; i < cantidad; i++)
        {recursos.add(recurso);
        }
    }

    public void removerMadera(Recurso recurso) {
        recursos.remove(recurso);
    }

    public void agregarMineral(int cantidad, Recurso recurso) {
        for(int i = 0; i < cantidad; i++)
        {recursos.add(recurso);
        }
    }

    public void removerMineral(Recurso recurso) {
        recursos.remove(recurso);
    }

    public void verificarCumplimiento(List<Recurso> precio) {
        List<Recurso> recursosCopia = new ArrayList<Recurso>(this.recursos);
        for (Recurso recurso : precio) {
            if(!recursosCopia.remove(recurso)){
                throw new RuntimeException("No tienes suficiente");
            }
        }
    }

    public Recurso obtenerRecursoAleatorio(){
        return recursos.get(ThreadLocalRandom.current().nextInt(recursos.size()));
    }
}