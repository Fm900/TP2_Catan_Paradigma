package edu.fiuba.algo3.modelo.Recurso;

import edu.fiuba.algo3.modelo.Recurso.Visitator.ContarRecursosVisitator;

import java.util.List;

public class GestorDeRecursos {
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
        ContarRecursosVisitator visitator = new ContarRecursosVisitator(tipo);
        recursos.forEach(r -> r.aceptar(visitator));
        return visitator.getResultado();
    }
}
