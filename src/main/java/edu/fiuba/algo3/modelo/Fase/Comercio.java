package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Intercambio.*;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Intercambio.Oferta.Oferta;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import java.util.List;

public class Comercio<T> implements FasePrincipal{
    private Jugador jugadorActual;

    public void iniciarFase(Jugador jugadorActual){
        this.jugadorActual = jugadorActual;
    }

    public Oferta crearOfertaJugador(Jugador receptor, List<Recurso> recursosOfrecidos, List<Recurso> recursosRequeridos){
        Intercambio tipoIntercambio = new EntreJugadores(jugadorActual,receptor,recursosOfrecidos,recursosRequeridos);
        return new Oferta(tipoIntercambio);
    }
    public void crearOfertaTazaEstandar(Recurso recursoOfrecido, Recurso recursoRequerido, Banca banca){
        Intercambio tipoIntercambio = new Estandar(jugadorActual,banca, recursoOfrecido, 4, recursoRequerido);
        banca.recibirOferta(recursoRequerido,new Oferta(tipoIntercambio));
    }
    public void crearOfertaTazaEspecifica(Recurso recursoDelPuerto, Recurso recursoRequerido, Banca banca){
        Intercambio tipoIntercambio = new Especifico(jugadorActual, banca,recursoDelPuerto, recursoRequerido);
        banca.recibirOferta(recursoRequerido, new Oferta(tipoIntercambio));
    }
    public void crearOfertaTazaGenerica(List<Recurso> recursosOfrecidos, Recurso recursosRequerido, Banca banca){
        Intercambio tipoIntercambio = new Generico(jugadorActual, banca, recursosOfrecidos, recursosRequerido);
        banca.recibirOferta(recursosRequerido, new Oferta(tipoIntercambio));
    }
    public void comprarCartas(List<Carta> cartas) {
        for (Carta carta : cartas) {
            carta.agregarse(jugadorActual);
        }
    }
}
