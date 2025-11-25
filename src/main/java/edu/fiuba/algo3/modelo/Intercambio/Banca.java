package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Intercambio.Oferta.Oferta;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import java.util.List;

public class Banca {
    /// va a extender lo que es una estructura de almacenamiento de recursos
    private List<Recurso> recursos;

    public Banca(List<Recurso> recursos) {
        this.recursos = recursos;
    }

    public boolean tieneSuficiente(Recurso recursoEvaluado) {
        int contador = 0;
        for (Recurso recurso : recursos) {
            if(recursoEvaluado.equals(recurso)) {
                contador++;
            }
        }
        return contador >= 1;
    }
    public void agregarRecurso(List<Recurso> ingreso) {
        for (Recurso recurso : ingreso) {
            this.recursos.add(recurso);
        }
    }
    public void consumirRecursos(Recurso recurso) {
        this.recursos.remove(recurso);
    }
}
