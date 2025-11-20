package edu.fiuba.algo3.modelo.Recurso;


import java.util.List;

public class MazoDeRecursos {
    private List<Recurso> recursos;

    public void agregar(Recurso recurso, int coeficiente) {
        for (int i = 0; i < coeficiente; i++) {
            recursos.add(recurso);
        }
    }
    public void remover(Recurso tipo, int cant){
        for (int i = 0; i < cant; i++) {
            recursos.removeIf(recurso -> recurso.equals(tipo));
        }
    }
    public int cantidadRecurso(Recurso tipo) {
        int cantidad = 0;
        for (Recurso recurso : recursos) {
            if (recurso.esMismoTipo(tipo)) {
                cantidad++;
            }
        }
        return cantidad;
    }
}
