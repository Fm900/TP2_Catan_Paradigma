package edu.fiuba.algo3.modelo.Jugador;

import edu.fiuba.algo3.modelo.Exception.NoAlcanzanLosRecursos;
import edu.fiuba.algo3.modelo.Exception.NoTieneRecursosSuficientesParaDescartar;
import edu.fiuba.algo3.modelo.Recurso.*;

import java.util.ArrayList;
import java.util.List;

public class MazoDeRecursos {

    private int lana;
    private int grano;
    private int madera;
    private int ladrillo;
    private int mineral;

    public MazoDeRecursos(List<Recurso> recursosIniciales) {
        for (Recurso recurso : recursosIniciales) {
            recurso.agregar(1, this);
        }
    }

    public int cantidadDescartar() {
        if (lana + grano + madera + ladrillo + mineral > 7) {
            return ((lana + grano + madera + ladrillo + mineral) / 2);
        }
        return 0;
    }

    public void descartarPorCantidad(int cantidad) {
        List<Recurso> tipos = List.of(new Madera(), new Ladrillo(), new Mineral(), new Grano(), new Lana());
        List<Integer> recursos = new ArrayList<>(List.of(madera, ladrillo, mineral, grano, lana));

        for (int i = 0; i < recursos.size() && cantidad > 0; i++) {
            while (recursos.get(i) > 0 && cantidad > 0) {
                tipos.get(i).eliminar(this);
                recursos.set(i, recursos.get(i) - 1);
                cantidad--;
            }
        }
    }

    public void agregarLana(Recurso recurso, int cantidad) {
        this.lana += cantidad;
    }

    public void removerLana(Recurso recurso) {
        if (this.lana == 0) {
            throw new NoTieneRecursosSuficientesParaDescartar("No tienes suficientes ", recurso);
        }
        this.lana -= 1;
    }

    public void agregarGrano(Recurso recurso, int cantidad) {
        this.grano += cantidad;
    }

    public void removerGrano(Recurso recurso) {
        if (this.grano == 0) {
            throw new NoTieneRecursosSuficientesParaDescartar("No tienes suficientes ", recurso);
        }
        this.grano -= 1;
    }

    public void agregarLadrillo(int cantidad, Recurso recurso) {
        this.ladrillo += cantidad;
    }

    public void removerLadrillo(Recurso recurso) {
        if (this.ladrillo == 0) {
            throw new NoTieneRecursosSuficientesParaDescartar("No tienes suficientes ", recurso);
        }
        this.ladrillo -= 1;
    }

    public void agregarMadera(int cantidad, Recurso recurso) {
        this.madera += cantidad;
    }

    public void removerMadera(Recurso recurso) {
        if (this.madera == 0) {
            throw new NoTieneRecursosSuficientesParaDescartar("No tienes suficientes ", recurso);
        }
        this.madera -= 1;
    }

    public void agregarMineral(int cantidad, Recurso recurso) {
        this.mineral += cantidad;
    }

    public void removerMineral(Recurso recurso) {
        if (this.mineral == 0) {
            throw new NoTieneRecursosSuficientesParaDescartar("No tienes suficientes ", recurso);
        }
        this.mineral -= 1;

    }

    public void verificarCumplimiento(List<Recurso> precio) {
        int maderaCopia = this.madera;
        int ladrilloCopia = this.ladrillo;
        int mineralCopia = this.mineral;
        int granoCopia = this.grano;
        int lanaCopia = this.lana;

        try {
            for (Recurso recurso : precio) {
                recurso.eliminar(this);
            }
        } catch (NoTieneRecursosSuficientesParaDescartar ex) {
            this.ladrillo = ladrilloCopia;
            this.mineral = mineralCopia;
            this.grano = granoCopia;
            this.lana = lanaCopia;
            this.madera = maderaCopia;
            throw new NoAlcanzanLosRecursos("No tienes suficientes recursos para realizar esta operacion");
        }
    }

    public Recurso obtenerRecursoAleatorio() {
        List<Integer> recursos = new ArrayList<>(List.of(madera, ladrillo, mineral, grano, lana));
        List<Recurso> tipos = List.of(new Madera(), new Ladrillo(), new Mineral(), new Grano(), new Lana());

        for (int i = 0; i < recursos.size(); i++) {
            if (recursos.get(i) > 0){
                return tipos.get(i);
            }
        }
        return new Desierto();
    }
}