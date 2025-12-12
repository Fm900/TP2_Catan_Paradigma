package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Alterado;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Normal;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneradorDeTerrenos {
    public List<Terreno> generar() {
        List<Terreno> terrenos = new ArrayList<>();
        List<Integer> fichas = new ArrayList<>(List.of(2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12));
        List<Recurso> recursosDisponibles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            recursosDisponibles.add(new Madera());
            recursosDisponibles.add(new Ladrillo());
            recursosDisponibles.add(new Grano());
            recursosDisponibles.add(new Mineral());
            recursosDisponibles.add(new Lana());
        }

        recursosDisponibles.remove(0);
        Collections.shuffle(fichas);
        Collections.shuffle(recursosDisponibles);

        for (int i = 0; i < 18; i++) {
            Recurso recurso = recursosDisponibles.get(i);
            Integer ficha = fichas.get(i);
            terrenos.add(new Terreno(recurso, ficha, new Normal()));
        }

        Recurso recursoNulo = new Desierto();
        terrenos.add(new Terreno(recursoNulo, 0, new Alterado()));
        
        Collections.shuffle(terrenos);

        return terrenos;
    }
}
