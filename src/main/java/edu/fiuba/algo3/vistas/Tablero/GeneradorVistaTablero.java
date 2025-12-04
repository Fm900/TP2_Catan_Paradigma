package edu.fiuba.algo3.vistas.Tablero;

import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

import java.util.*;

public class GeneradorVistaTablero {
    private final StackPane root;
    private Group tableroPane;

    public GeneradorVistaTablero(StackPane root) {
        this.root = root;
    }

    public void crearTablero(List<Terreno> terrenos,
                             List<Vertice> verticesModelo,
                             List<Arista> aristasModelo) {

        tableroPane = new Group();
        root.getChildren().add(tableroPane);

        Platform.runLater(() -> {

            dibujarHexagonosAgua();

            for (Terreno t : terrenos) {
                Polygon hex = crearHexagonoDesdeModelo(t);
                tableroPane.getChildren().add(hex);
            }

            dibujarAristas(aristasModelo);

            dibujarVertices(verticesModelo);
        });
    }

    private Polygon crearHexagonoDesdeModelo(Terreno terreno) {

        Polygon p = new Polygon();

        // El terreno ya tiene los 6 vértices asignados por modelo
        terreno.verticesAdyacentes().forEach(v ->
                p.getPoints().addAll(v.getX(), v.getY())
        );

        p.setFill(Color.BEIGE);
        p.setStroke(Color.BLACK);
        p.setStrokeWidth(2);

        return p;
    }

    private void dibujarVertices(List<Vertice> verticesModelo) {

        for (Vertice v : verticesModelo) {

            Circle c = new Circle(v.getX(), v.getY(), 8, Color.WHITE);
            c.setStroke(Color.BLACK);

            // ejemplo de interacción
            c.setOnMouseClicked(e -> c.setFill(Color.RED));

            tableroPane.getChildren().add(c);
        }
    }

    private void dibujarAristas(List<Arista> aristasModelo) {

        for (Arista a : aristasModelo) {

            Vertice v1 = a.extremo1();
            Vertice v2 = a.extremo2();

            Line l = new Line(v1.getX(), v1.getY(), v2.getX(), v2.getY());
            l.setStroke(Color.GRAY);
            l.setStrokeWidth(8);

            // ejemplo de interacción
            l.setOnMouseClicked(e -> l.setStroke(Color.BLUE));

            tableroPane.getChildren().add(l);
        }
    }

    private void dibujarHexagonosAgua() {
        // Constantes (deben coincidir con ConstructorTablero)
        double RADIO = 70;
        double ALTURA = RADIO * Math.sqrt(3);
        int[] filas = {3, 4, 5, 4, 3};
        int filaMax = 5;

        double dx = ALTURA;
        double dy = RADIO * 1.5;

        // Fila superior de agua (fila -1)
        dibujarFilaAgua(-1, 4, dx, dy, filaMax, RADIO);

        // Lateral izquierdo superior
        dibujarHexagonoAgua(0, -1, dx, dy, filaMax, 3, RADIO);
        dibujarHexagonoAgua(1, -1, dx, dy, filaMax, 4, RADIO);

        // Lateral izquierdo medio
        dibujarHexagonoAgua(2, -1, dx, dy, filaMax, 5, RADIO);

        // Lateral izquierdo inferior
        dibujarHexagonoAgua(3, -1, dx, dy, filaMax, 4, RADIO);
        dibujarHexagonoAgua(4, -1, dx, dy, filaMax, 3, RADIO);

        // Lateral derecho superior
        dibujarHexagonoAgua(0, 3, dx, dy, filaMax, 3, RADIO);
        dibujarHexagonoAgua(1, 4, dx, dy, filaMax, 4, RADIO);

        // Lateral derecho medio
        dibujarHexagonoAgua(2, 5, dx, dy, filaMax, 5, RADIO);

        // Lateral derecho inferior
        dibujarHexagonoAgua(3, 4, dx, dy, filaMax, 4, RADIO);
        dibujarHexagonoAgua(4, 3, dx, dy, filaMax, 3, RADIO);

        // Fila inferior de agua (fila 5)
        dibujarFilaAgua(5, 4, dx, dy, filaMax, RADIO);
    }

    private void dibujarFilaAgua(int fila, int cantidad, double dx, double dy, int filaMax, double radio) {
        double offsetX = (filaMax - cantidad) * (dx / 2.0);

        for (int col = 0; col < cantidad; col++) {
            dibujarHexagonoAgua(fila, col, dx, dy, filaMax, cantidad, radio);
        }
    }

    private void dibujarHexagonoAgua(int fila, int col, double dx, double dy, int filaMax, int cantidadEnFila, double radio) {
        double offsetX = (filaMax - cantidadEnFila) * (dx / 2.0);
        double cx = col * dx + offsetX;
        double cy = fila * dy;

        Polygon hex = new Polygon();

        for (int k = 0; k < 6; k++) {
            double ang = Math.toRadians(60 * k - 30);
            double vx = cx + radio * Math.cos(ang);
            double vy = cy + radio * Math.sin(ang);
            hex.getPoints().addAll(vx, vy);
        }

        hex.setFill(Color.LIGHTBLUE);
        hex.setStroke(Color.DARKBLUE);
        hex.setStrokeWidth(2);

        tableroPane.getChildren().add(0, hex); // Agregar al fondo
    }
}