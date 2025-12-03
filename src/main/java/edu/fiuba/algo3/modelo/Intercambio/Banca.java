package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Exception.LaBancaNoHaSidoCreadaAun;
import edu.fiuba.algo3.modelo.Exception.NoSePudoRealizarElIntercambioLaBancaNoTieneSuficientesRecursos;
import edu.fiuba.algo3.modelo.Intercambio.Oferta.Oferta;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Banca {

    private static Banca banca;

    /// va a extender lo que es una estructura de almacenamiento de recursos
    private List<Recurso> recursos;

    private Banca(List<Recurso> recursos) {
        this.recursos = new ArrayList<>(recursos);
    }

    public static Banca creacBanca(List<Recurso> recursos){
        if(banca == null){
            banca = new Banca(recursos);
        }
        return banca;
    }

    public static Banca getInstance() {
        if (banca == null){
            throw new LaBancaNoHaSidoCreadaAun("La Banca no ha sido creada aun");
        }
        return banca;
    }

    public void agregarRecurso(List<Recurso> ingreso) {
        recursos.addAll(ingreso);
    }

    public void consumirRecursos(Recurso recurso) {
        if(!this.recursos.remove(recurso)){
            throw new NoSePudoRealizarElIntercambioLaBancaNoTieneSuficientesRecursos("El interambio no se realizo la Banca no tiene suficiente: ", recurso);
        }
    }
}