package edu.fiuba.algo3.modelo.Constructores;

import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Arista.Vacia;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;

import java.util.*;

public class ConstructorTablero {

    // Constantes de geometría
    private static final double radio = 70;
    private static final double altura = radio * Math.sqrt(3);
    private static final int[] hex_por_fila = {3, 4, 5, 4, 3};
    private static final int cant_max = 5;
    private static final int vertices_hexagono = 6;
    private static final double ang_ini = -30;
    private static final double red = 1000.0;

    // Estado interno
    private int idCounter = 0;
    private final List<Terreno> terrenos;
    private final List<Vertice> vertices;
    private final List<Arista> aristas;

    public ConstructorTablero() {
        this.terrenos = new GeneradorDeTerrenos().generar();
        this.vertices = new ArrayList<>();
        this.aristas = new ArrayList<>();
        generarGeometria();
    }

    public List<Terreno> generarTerrenos() {
        return Collections.unmodifiableList(this.terrenos);
    }

    public List<Vertice> generarVertices() {
        return Collections.unmodifiableList(this.vertices);
    }

    public List<Arista> generarAristas() {
        return Collections.unmodifiableList(this.aristas);
    }

    private void generarGeometria() {
        Map<String, Vertice> mapaVertices = new HashMap<>();
        Map<String, Arista> mapaAristas = new HashMap<>();

        double desplazamientoHorizontal = altura;
        double desplazamientoVertical = radio * 1.5;

        int indexTerreno = 0;

        for (int fila = 0; fila < hex_por_fila.length; fila++) {
            int cantidadHexagonos = hex_por_fila[fila];
            double offsetX = calcularOffsetHorizontal(cantidadHexagonos, desplazamientoHorizontal);

            for (int col = 0; col < cantidadHexagonos; col++) {
                if (indexTerreno >= terrenos.size()) {
                    finalizarGeometria(mapaVertices, mapaAristas);
                    return;
                }

                Terreno terreno = terrenos.get(indexTerreno++);
                double centroX = col * desplazamientoHorizontal + offsetX;
                double centroY = fila * desplazamientoVertical;

                procesarTerreno(terreno, centroX, centroY, mapaVertices, mapaAristas);
            }
        }

        finalizarGeometria(mapaVertices, mapaAristas);
    }

    private double calcularOffsetHorizontal(int cantidadHexagonos, double desplazamiento) {
        return (cant_max - cantidadHexagonos) * (desplazamiento / 2.0);
    }

    private void procesarTerreno(Terreno terreno, double centroX, double centroY,
                                 Map<String, Vertice> mapaVertices,
                                 Map<String, Arista> mapaAristas) {

        List<Vertice> verticesTerreno = generarVerticesHexagono(centroX, centroY, mapaVertices);
        terreno.asignarVerticesAdyacentes(verticesTerreno);
        crearAristasParaTerreno(verticesTerreno, mapaAristas);
    }

    private List<Vertice> generarVerticesHexagono(double centroX, double centroY,
                                                  Map<String, Vertice> mapaVertices) {
        List<Vertice> verticesTerreno = new ArrayList<>(vertices_hexagono);

        for (int k = 0; k < vertices_hexagono; k++) {
            double angulo = Math.toRadians(60 * k + ang_ini);
            double vx = centroX + radio * Math.cos(angulo);
            double vy = centroY + radio * Math.sin(angulo);

            Vertice vertice = obtenerOCrearVertice(vx, vy, mapaVertices);
            verticesTerreno.add(vertice);
        }

        return verticesTerreno;
    }

    private Vertice obtenerOCrearVertice(double vx, double vy, Map<String, Vertice> mapaVertices) {
        String key = generarKeyVertice(vx, vy);
        return mapaVertices.computeIfAbsent(key, k -> new Vertice(idCounter++, vx, vy));
    }

    private void crearAristasParaTerreno(List<Vertice> verticesTerreno,
                                         Map<String, Arista> mapaAristas) {
        for (int k = 0; k < vertices_hexagono; k++) {
            Vertice v1 = verticesTerreno.get(k);
            Vertice v2 = verticesTerreno.get((k + 1) % vertices_hexagono);

            String keyArista = generarKeyArista(v1, v2);
            mapaAristas.computeIfAbsent(keyArista, key -> new Arista(v1, v2, new Vacia()));
        }
    }

    private String generarKeyVertice(double x, double y) {
        return redondear(x) + "," + redondear(y);
    }

    private String generarKeyArista(Vertice v1, Vertice v2) {
        // Normalizar el orden para evitar aristas duplicadas
        int id1 = Math.min(v1.getId(), v2.getId());
        int id2 = Math.max(v1.getId(), v2.getId());
        return id1 + "_" + id2;
    }

    private void finalizarGeometria(Map<String, Vertice> mapaVertices,
                                    Map<String, Arista> mapaAristas) {
        vertices.addAll(mapaVertices.values());
        aristas.addAll(mapaAristas.values());
    }

    private static double redondear(double n) {
        return Math.round(n * red) / red;
    }
}