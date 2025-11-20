package edu.fiuba.algo3.modelo.Fase;

import edu.fiuba.algo3.modelo.Banca;
import edu.fiuba.algo3.modelo.Intercambio.Bancario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Intercambio.Oferta;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public class CC implements FasePrincipal {
    Jugador jugadorActual;
    Oferta ofertaActual;
    Bancario bancarioActual;
    Banca banca;
    public void iniciarFase(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
        ofertaActual = null;
        bancarioActual = null;
    }
    public void crearOferta(List<Recurso>recursosRequeridos){
        this.ofertaActual = new Oferta(this.jugadorActual, recursosRequeridos);
    }
    public boolean realizarIntercambio(List<Recurso> recursosOfrecidos, Jugador receptor) {
        if (ofertaActual == null || receptor == null) {
            return false;
        }
        return ofertaActual.recibirOrferta(receptor, recursosOfrecidos);
    }

}
