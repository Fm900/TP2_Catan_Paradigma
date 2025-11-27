package edu.fiuba.algo3.modelo.Tablero.Puerto;

import edu.fiuba.algo3.modelo.Exception.RecursoInvalido;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public class Generico implements Tasa{
    private int cantidad = 3;
    @Override
    public List<Recurso> aplicarTasa(List<Recurso> recursos, Jugador jugador) {
        if(!esValido(recursos)){
            throw new RecursoInvalido("El jugador no puede comerciar con esta tasa porque no tiene los recursos suficientes");
        }
        return recursos;
    }
    private boolean esValido(List<Recurso> recursos){
        boolean respuesta = true;
        if(recursos.size() != cantidad){
            respuesta = false;
            throw new RecursoInvalido("Se deben elegir: " +cantidad+"recursos disntintos");
        }
        return respuesta;
    }

}
