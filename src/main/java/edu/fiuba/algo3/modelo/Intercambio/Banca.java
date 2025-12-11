package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Exception.LaBancaNoHaSidoCreadaAun;
import edu.fiuba.algo3.modelo.Exception.NoSePudoComprarUnaCartaSeAgotaronLasCartasDeLaBanca;
import edu.fiuba.algo3.modelo.Exception.NoSePudoRealizarElIntercambioLaBancaNoTieneSuficientesRecursos;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Banca {
    private static Banca banca;
    private final List<Recurso> recursos;
    private final List<Carta>  cartas;


    private Banca(List<Recurso> recursos, List<Carta> cartas) {
        this.recursos = new ArrayList<>(recursos);
        this.cartas = new ArrayList<>(cartas);
    }

    public static Banca crearBanca(List<Recurso> recursos, List<Carta> cartas){
        if(banca == null){
            banca = new Banca(recursos,  cartas);
        }
        return banca;
    }

    public static Banca getInstance() {
        if (banca == null){
            throw new LaBancaNoHaSidoCreadaAun("La Banca no ha sido creada aun");
        }
        return banca;
    }

    public static void reset(){
        banca = null;
    }

    public void agregarRecurso(List<Recurso> ingreso) {
        recursos.addAll(ingreso);
    }

    public void consumirRecursos(Recurso recurso) {
        if(!this.recursos.remove(recurso)){
            throw new NoSePudoRealizarElIntercambioLaBancaNoTieneSuficientesRecursos("El interambio no se realizo la Banca no tiene suficiente: ", recurso);
        }
    }

    public void eliminarCarta(Carta cartaAEliminar) {
        if(!this.cartas.remove(cartaAEliminar)){
            throw new NoSePudoComprarUnaCartaSeAgotaronLasCartasDeLaBanca("No hay mas cartas en la Banca");
        }
    }

    public void agregarRecursoCarta(Carta carta) {
        carta.pagarse(banca);
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public Carta popPrimerCarta() {
        Carta carta = this.cartas.get(0);
        cartas.remove(carta);
        return carta;
    }
}