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
    public void recibirOferta(Recurso recursoRequerido, Oferta oferta) {
        if(this.cantidaDeRecurso(recursoRequerido) > 0){
            oferta.acepatar();
        }
    }
    public int cantidaDeRecurso(Recurso recu) {
        int cantidad = 0;
        for (Recurso recurso : recursos) {
            if (recu.equals(recurso)) {
                cantidad++;
            }
        }
        return cantidad;
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
