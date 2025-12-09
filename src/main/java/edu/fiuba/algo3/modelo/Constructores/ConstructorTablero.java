package edu.fiuba.algo3.modelo.Constructores;

import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Arista.Vacia;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Puerto;
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
    private final List<Puerto> puertos;

    public ConstructorTablero() {
        this.terrenos = new GeneradorDeTerrenos().generar();
        this.vertices = new ArrayList<>();
        this.aristas = new ArrayList<>();
        this.puertos = new ArrayList<>();
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

    public List<Puerto> generarPuertos() {
        return Collections.unmodifiableList(this.puertos);
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
        crearPuertos();
    }

    private static double redondear(double n) {
        return Math.round(n * red) / red;
    }

    private void crearPuertos() {
        List<Arista> aristasCosta = new ArrayList<>();

        for (Arista a : aristas) {
            int count = 0;
            for (Terreno t : terrenos) {
                if (t.tieneVertice(a.extremo1()) && t.tieneVertice(a.extremo2())) {
                    count++;
                    if (count > 1) break;
                }
            }
            if (count == 1) {
                aristasCosta.add(a);
            }
        }

        double cx = vertices.stream().mapToDouble(Vertice::getX).average().orElse(0);
        double cy = vertices.stream().mapToDouble(Vertice::getY).average().orElse(0);

        class AC {
            Arista a;
            double ang;
            AC(Arista arista) {
                this.a = arista;
                double mx = (arista.extremo1().getX() + arista.extremo2().getX()) / 2.0;
                double my = (arista.extremo1().getY() + arista.extremo2().getY()) / 2.0;
                this.ang = Math.atan2(my - cy, mx - cx);
            }
        }

        List<AC> lista = new ArrayList<>();
        for (Arista a : aristasCosta) {
            lista.add(new AC(a));
        }
        lista.sort(Comparator.comparingDouble(ac -> ac.ang));

        int N = lista.size();
        double step = (double) N / 9.0;

        List<Arista> seleccionadas = new ArrayList<>();
        Set<Integer> usados = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            int idx = (int) Math.round(i * step);
            if (idx >= N) idx = N - 1;

            while (usados.contains(idx) && idx < N - 1) {
                idx++;
            }
            usados.add(idx);
            seleccionadas.add(lista.get(idx).a);
        }

        List<Recurso> tipos = new ArrayList<>();
        tipos.add(null);
        tipos.add(null);
        tipos.add(null);
        tipos.add(null);
        tipos.add(new Madera());
        tipos.add(new Ladrillo());
        tipos.add(new Lana());
        tipos.add(new Grano());
        tipos.add(new Mineral());

        Collections.shuffle(tipos);

        for (int i = 0; i < 9; i++) {
            Arista arista = seleccionadas.get(i);
            Recurso tipo = tipos.get(i);

            if (tipo == null) {
                puertos.add(new Puerto(arista));
            } else {
                puertos.add(new Puerto(tipo, arista));
            }
        }
    }

}