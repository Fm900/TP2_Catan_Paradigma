package edu.fiuba.algo3.modelo.Constructores;

import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Arista.Vacia;
import edu.fiuba.algo3.modelo.Tablero.GeneradorDeTerrenos;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import java.util.ArrayList;
import java.util.List;

public class ConstructorTablero {

    private static final int[][] DEFINICION_ARISTAS = {
            {4,1},{1,5},{5,2},{2,6},{6,3},{3,7},  //fila 1
            {4,8},{5,9},{6,10},{7,11}, //fila 2
            {12,8},{8,13},{13,9},{9,14},{14,10},{10,15},{15,11},{11,16}, //fila 3
            {12, 17}, {13, 18}, {14, 19}, {15, 20}, {16, 21}, //fila 4
            {22, 17}, {17, 23}, {23, 18}, {18, 24}, {24, 19}, {19, 25}, {25, 20}, {20, 26}, {26, 21}, {21, 27}, //fila 5
            {22, 28}, {23, 29}, {24, 30}, {25, 31}, {26, 32}, {27, 33}, //fila 6
            {28, 34}, {34, 29}, {29, 35}, {35, 30}, {30, 36}, {36, 31}, {31, 37}, {37, 32}, {32, 38}, {38, 33}, //fila 7
            {34, 39}, {35, 40}, {36, 41}, {37, 42}, {38, 43}, //fila 8
            {39, 44}, {44, 40}, {40, 45}, {45, 41}, {41, 46}, {46, 42}, {42, 47}, {47, 43}, //fila 9
            {44, 48}, {45, 49}, {46, 50}, {47, 51}, //fila 10
            {48, 52}, {52, 49}, {49, 53}, {53, 50}, {50, 54}, {54, 51} //fila 11
    };

    private static final int[][] VERTICES_DE_TERRENO = {
                      {1, 4, 8, 13, 9, 5},   {2, 5, 9, 14, 10, 6},  {3, 6, 10, 15, 11, 7},
                 {8, 12, 17, 23, 18, 13}, {9, 13, 18, 24, 19, 14}, {10, 14, 19, 25, 20, 15}, {11, 15, 20, 26, 21, 16},
            {17, 22, 28, 34, 29, 23}, {18, 23, 29, 35, 30, 24}, {19, 24, 30, 36, 31, 25}, {20, 25, 31, 37, 32, 26}, {21, 26, 32, 38, 33, 27},
                 {29, 34, 39, 44, 40, 35}, {30, 35, 40, 45, 41, 36}, {31, 36, 41, 46, 42, 37}, {32, 37, 42, 47, 43, 38},
                      {40,44,48,52,49,45}, {41,45,49,53,50,46}, {42,46,50,54,51,47}
    };

    public List<Terreno> generarTerrenos(){
        GeneradorDeTerrenos generador = new GeneradorDeTerrenos();
        return generador.generar(); // 19 terrenos con recurso/ficha/estado
    }

    public List<Vertice> generarVertices() {
        List<Vertice> vertices = new ArrayList<>();
        for (int i = 0; i < 54; i++) {
            vertices.add(new Vertice());
        }
        return vertices;
    }

    public List<Arista> generarAristas(List<Vertice> vertices){
        List<Arista> aristas = new ArrayList<>();

        for (int[] extremos : DEFINICION_ARISTAS) {
            int idVertice1 = extremos[0];
            int idVertice2 = extremos[1];

            Vertice vertice1 = vertices.get(idVertice1 - 1);
            Vertice vertice2 = vertices.get(idVertice2 - 1);

            Arista arista = new Arista(vertice1, vertice2, new Vacia());
            aristas.add(arista);

            vertice1.registrarArista(arista);
            vertice2.registrarArista(arista);
        }
        return aristas;
    }

    public void asignarVerticesATerrenos(List<Terreno> terrenos, List<Vertice> vertices) {
        for (int i = 0; i < 19; i++) {
            Terreno terreno = terrenos.get(i);
            int[] idsVertices = VERTICES_DE_TERRENO[i];

            List<Vertice> verticesAdy = new ArrayList<>(6);
            for (int id : idsVertices) {
                verticesAdy.add(vertices.get(id - 1));
            }
            terreno.asignarVerticesAdyacentes(verticesAdy);
        }
    }
}

