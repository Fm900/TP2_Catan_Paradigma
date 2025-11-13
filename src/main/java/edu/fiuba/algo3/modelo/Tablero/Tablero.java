package edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tablero {
    private List<Terreno> terrenos = new ArrayList<>();

    public Tablero() {
    }

    public List<Terreno> crearTerrenos(){
        List<Terreno> terrenos_creados = new ArrayList<>();

        List<String> TIPOS = List.of("Madera", "Ladrillo", "Grano", "Mineral", "Lana");
        List<Integer> FICHAS = new ArrayList<>(List.of(2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12));
        List<String> recursosDisponibles = new ArrayList<>();
        for (String tipo : TIPOS) {
            for (int i = 0; i < 4; i++) recursosDisponibles.add(tipo);
        }
        Collections.shuffle(recursosDisponibles);
        recursosDisponibles.remove(0);
        Collections.shuffle(FICHAS);

        //agrego 18 terrenos normales
        for (int i = 0; i < 18; i++){
            terrenos_creados.add(new Terreno((recursosDisponibles.get(i)), FICHAS.get(i), new Normal()));
        }
        //agrego 1 terreno de desierto desierto
        terrenos_creados.add(new Terreno("Desierto", 0, new Alterado()));

        /* Shuffle usa por defecto un random distinto cada vez, por lo tanto:
            El orden de los tipos de terreno cambia cada vez.
            El orden de las fichas tambien cambia cada vez.
            La combinacion terreno–ficha es distinta cada vez. */

        this.terrenos = terrenos_creados;
        return terrenos_creados;
    }

    public void colocarPoblado(Jugador jugador, Vertice vertice) throws Exception {
        vertice.construirPoblado(jugador);
    }

    public void producirPara(int tirada){
        for (Terreno t : terrenos) {
            t.producirSiCorresponde(tirada);
        }
    }
}
