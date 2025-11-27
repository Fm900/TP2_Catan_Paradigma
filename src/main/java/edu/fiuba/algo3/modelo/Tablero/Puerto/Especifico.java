package edu.fiuba.algo3.modelo.Tablero.Puerto;

import edu.fiuba.algo3.modelo.Exception.RecursoInvalido;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Especifico implements Tasa{
    private Recurso recursoPuerto;
    private int cantidad = 2;

    public Especifico(Recurso recurso){
        this.recursoPuerto = recurso;
    }

    @Override
    public List<Recurso> aplicarTasa(List<Recurso> recursos, Jugador jugador) {
        Recurso recurso = recursos.get(0);
        if(!recurso.equals(recursoPuerto)){
            throw new RecursoInvalido("Solo se pude comerciar con el recurso indicado por el puerto");
        }

        if(!this.esValido(jugador, recurso)){
            throw new RecursoInvalido("El jugador no puede comerciar con esta tasa porque no tiene los recursos suficientes");
        }

        List<Recurso> respuesta = new ArrayList<>();
        for(int i = 0; i < cantidad; i++){
            respuesta.add(recurso);
        }
        return respuesta;
    }


    private boolean esValido(Jugador jugador, Recurso recurso){
        return jugador.cantidadDeRecurso(recurso) > cantidad;
    }
}