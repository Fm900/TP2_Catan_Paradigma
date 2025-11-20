package edu.fiuba.algo3.modelo.Recurso;


import java.util.ArrayList;
import java.util.List;

public class MazoDeRecursos {
    private List<Recurso> recursos;

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


}