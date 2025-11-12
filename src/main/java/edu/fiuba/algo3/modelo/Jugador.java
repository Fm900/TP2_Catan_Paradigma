package edu.fiuba.algo3.modelo;

import java.util.Dictionary;
import java.util.Hashtable;

public class Jugador {
    private Dictionary<String, Integer> recursos;

    public Jugador() {
        recursos = new Hashtable<>();
        recursos.put("madera", 0);
        recursos.put("ladrillo", 0);
        recursos.put("lana", 0);
        recursos.put("grano", 0);
        recursos.put("mineral", 0);
    }

    public void agregarRecursos(String recurso, Integer cantidad) {
        recursos.put(recurso, recursos.get(recurso) + cantidad);
    }

    public Integer getCantidadRecurso(String recurso) {
        return recursos.get(recurso);
    }
}
