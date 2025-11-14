package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;

public class Jugador {
    private Map<String, Integer> recursos;
    private Collection<Carta> cartas;

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

    public void descartarRecursos(String recurso, Integer cantidad) {
        int actual = recursos.get(recurso);

        if (actual < cantidad) {
            throw new NoTieneRecursos("El jugador no posee recursos para descartar");
        }

        int nuevaCantidad = actual - cantidad;
        recursos.put(recurso, nuevaCantidad);
    }

    public void removerCarta(Carta carta){
        cartas.remove(carta);
    }
    public int getCantidadRecursosTotales() {
        int total = 0;
        for (int cantidad : recursos.values()) {
            total += cantidad;
        }
        return total;
    }
    public boolean tieneCartasParaJugar() {
        return !cartas.isEmpty();
    }

    public void consumirRecursosParaPoblado() {
    }

    public void descarteMayoria() {
        Integer cantidadTotalRecursos = 0;
        Integer cantidadRecursosADescartar;
        List<String> recursosADescartar = new ArrayList<>();
        for (String recurso : recursos.keySet()) {
            cantidadTotalRecursos = cantidadTotalRecursos + (recursos.get(recurso));
        }

        if ( cantidadTotalRecursos > 7 ) {
            cantidadRecursosADescartar = (cantidadTotalRecursos / 2);
            recursosADescartar = this.eleccionRecursosDescartar(cantidadRecursosADescartar);

            for (String recurso : recursosADescartar) {
                this.descartarRecursos(recurso, 1);
            }
        }
    }

    public List<String> eleccionRecursosDescartar(Integer cantidad) {
        List<String> listaTotalRecursos = new ArrayList<>();
        List<String> listaRecursosDescartar = new ArrayList<>();

        listaTotalRecursos = this.obtenerListadoRecursos();

        for (int i = 0; i < cantidad; i++) {
            listaRecursosDescartar.add(listaTotalRecursos.get(i));
        }

        return listaRecursosDescartar;
    }


    public String obtenerRecursoAleatorio() {        
        String recursoAleatorio;
        List<String> listaRecursos = new ArrayList<>();
        listaRecursos = this.obtenerListadoRecursos();

        if (listaRecursos.isEmpty()) {
            throw new NoTieneRecursos("El jugador no posee recursos para descartar");
        }

        int indice = ThreadLocalRandom.current().nextInt(listaRecursos.size());
        recursoAleatorio = listaRecursos.get(indice);
        return recursoAleatorio;
    }

    public List<String> obtenerListadoRecursos() {
        List<String> listaRecursos = new ArrayList<>();
        for (String recurso : (this.recursos).keySet()) {
            int cantidad = (this.recursos).get(recurso);
            if (cantidad > 0) {
                for (int i = 0; i < cantidad; i++) {
                    listaRecursos.add(recurso);
                }
            }
        }
        return listaRecursos;
    }

    public Jugador elegirVictima (List<Jugador> posiblesVictimas) {
        Jugador victima;
        victima = posiblesVictimas.get(0);
        return victima;
    }
}
