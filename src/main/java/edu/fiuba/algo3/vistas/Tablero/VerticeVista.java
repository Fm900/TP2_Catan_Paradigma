package edu.fiuba.algo3.vistas.Tablero;

import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;

public class VerticeVista extends Group {

    private final Vertice modelo;
    private final Circle circle;
    private final SVGPath casa;
    private final SVGPath ciudad;
    private Color colorJugador;

    public VerticeVista(Vertice v) {
        this.modelo = v;

        // --- Crear círculo básico ---
        circle = new Circle(0, 0, 8);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(3);

        // --- Crear casa SVG ---
        casa = new SVGPath();
        casa.setContent("M 0 10 L 10 0 L 20 10 L 20 20 L 0 20 Z");
        casa.setFill(Color.WHITE);
        casa.setStroke(Color.BLACK);
        casa.setStrokeWidth(2);
        casa.setScaleX(1.2);
        casa.setScaleY(1.2);
        casa.setTranslateX(-6); // centrar dentro del Group
        casa.setTranslateY(-10);
        casa.setVisible(false);

        // --- Crear ciudad SVG ---
        ciudad = new SVGPath();
        ciudad.setContent("M 0 20 L 0 8 L 8 0 L 16 8 L 16 20 Z");
        ciudad.setFill(Color.WHITE);
        ciudad.setStroke(Color.BLACK);
        ciudad.setStrokeWidth(2);
        ciudad.setScaleX(1.5);
        ciudad.setScaleY(1.5);
        ciudad.setTranslateX(-5);
        ciudad.setTranslateY(-10);
        ciudad.setVisible(false);

        this.getChildren().addAll(circle, casa, ciudad);

        this.setLayoutX(v.getX());
        this.setLayoutY(v.getY());
    }

    // --- Estados gráficos ---
    public void seleccionar() {
        circle.setFill(Color.RED);
        if (casa.isVisible()) {
            casa.setFill(Color.RED);
        }
        if (ciudad.isVisible()) {
            ciudad.setFill(Color.RED);
        }
    }

    public void deseleccionar() {
        circle.setFill(Color.WHITE);
        if (casa.isVisible()) {
            casa.setFill(colorJugador);
        }
        if (ciudad.isVisible()) {
            ciudad.setFill(colorJugador);
        }
    }

    public void mostrarCasa(Color colorJugador) {
        this.colorJugador = colorJugador;
        casa.setFill(colorJugador);
        casa.setVisible(true);
        circle.setVisible(false);
    }

    public void mostrarCiudad(Color colorJugador) {
        ciudad.setFill(colorJugador);
        ciudad.setVisible(true);
        casa.setVisible(false);
        circle.setVisible(false);
    }

    public Vertice getModelo() {
        return modelo;
    }
}
