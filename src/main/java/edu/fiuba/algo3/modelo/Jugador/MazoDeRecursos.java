package edu.fiuba.algo3.modelo.Jugador;


import edu.fiuba.algo3.modelo.Exception.NoAlcanzanLosRecursos;
import edu.fiuba.algo3.modelo.Exception.NoTieneRecursosSuficientesParaDescartar;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MazoDeRecursos {
    private final List<Recurso> recursos;

    public MazoDeRecursos(List<Recurso> recursosIniciales) {
        this.recursos = new ArrayList<>(recursosIniciales);
    }

    public int cantidadDescartar() {
        if (recursos.size() > 7) {
            return (recursos.size() / 2);
        }
        return 0;
    }

    public void descartarPorCantidad(int cantidad) {
        recursos.subList(0, cantidad).clear();
    }

    public void agregarRecurso(Recurso recurso, int cantidad) {
        for (int i=0; i<cantidad; i++) {
            recursos.add(recurso);
        }
    }
    public void removerRecurso(Recurso recurso) {
        for (int i=0; i<recursos.size(); i++) {
            if (recursos.get(i).mismaClaseQue(recurso)) {
                recursos.remove(i);
                break;
            }
        }
    }

    public void verificarCumplimiento(List<Recurso> precio) {
        List<Recurso> copia = new ArrayList<>(recursos);
        for (Recurso recursoPrecio : precio) {
            /*
            boolean eliminado = false;
            for (int i = 0; i < copia.size(); i++) {
                if (copia.get(i).getClass().equals(recursoPrecio.getClass())){
                    copia.remove(i);
                    eliminado = true;
                    break;
                }
            }
            if (!eliminado) {
                throw new NoAlcanzanLosRecursos("No tienes suficiente");
            }
            */
             recursos.remove(recursoPrecio);
        }
    }


    public Recurso obtenerRecursoAleatorio(){
        return recursos.get(ThreadLocalRandom.current().nextInt(recursos.size()));
    }

    public int getCantidadDe(Recurso recu) {
        int cantidad = 0;
        for (Recurso recurso : recursos) {
            if (recurso.mismaClaseQue(recu)) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public List<Recurso> obtenerRecursos() {
        return recursos;
    }
}