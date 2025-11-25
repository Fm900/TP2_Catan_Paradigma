package edu.fiuba.algo3.modelo.Tablero.Puerto;

import edu.fiuba.algo3.modelo.Exception.RecursoInvalido;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Generico implements Tasa{
    private int cantidad = 3;
    @Override
    public List<Recurso> aplicarTasa(List<Recurso> recursos, Jugador jugador) {
        if(!esValido(jugador, recursos)){
            throw new RecursoInvalido("El jugador no puede comerciar con esta tasa porque no tiene los recursos suficientes");
        }
        return recursos;
    }
    private boolean esValido(Jugador jugador,List<Recurso> recursos){
        boolean respuesta = true;
        if(recursos.size() != cantidad){
            throw new RecursoInvalido("Se deben elegir: " +cantidad+"recursos disntintos");
        }
        for (Recurso recurso : recursos) {
            if(jugador.cantidadDeRecurso(recurso) < 1){
                respuesta = false;
            }
        }
        return respuesta;
    }

}
