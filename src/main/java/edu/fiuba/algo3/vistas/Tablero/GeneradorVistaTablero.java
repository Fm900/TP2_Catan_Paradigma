package edu.fiuba.algo3.vistas.Tablero;

import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;


import java.util.*;

public class GeneradorVistaTablero {
    private final StackPane root;
    private Group tableroPane;
    private Image imagenAgua;
    private final Map<Integer, Image> fichasCache = new HashMap<>();

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

                agregarFichaNumero(t, hex);
            }

            dibujarAristas(aristasModelo);

            dibujarVertices(verticesModelo);
        });
    }
    private Image getImagenAgua() {
        if (imagenAgua == null) {
            imagenAgua = new Image(
                    Objects.requireNonNull(
                            getClass().getResourceAsStream("/hexagonos/oceano.png")
                    )
            );
        }
        return imagenAgua;
    }
    private Polygon crearHexagonoDesdeModelo(Terreno terreno) {

        Polygon p = new Polygon();

        // El terreno ya tiene los 6 vértices asignados por modelo
        terreno.verticesAdyacentes().forEach(v ->
                p.getPoints().addAll(v.getX(), v.getY())
        );

        //textura segun el tipo de terreno
        ImagePattern textura = crearTexturaPara(terreno, p);
        p.setFill(textura);

        p.setStroke(Color.BLACK);
        p.setStrokeWidth(2);

        return p;
    }

    private void dibujarVertices(List<Vertice> verticesModelo) {

        for (Vertice v : verticesModelo) {

            Circle c = new Circle(v.getX(), v.getY(), 8, Color.WHITE);
            c.setStroke(Color.BLACK);
            c.setStrokeWidth(1.5);

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
            l.setStrokeWidth(4);

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

        // === Textura de océano ===
        Image img = getImagenAgua();

        // Bounds del hexágono
        Bounds b = hex.getBoundsInLocal();
        double x = b.getMinX();
        double y = b.getMinY();
        double w = b.getWidth();
        double h = b.getHeight();


        double margen = Math.max(w, h) * 0.20;
        ImagePattern texturaAgua = new ImagePattern(img,
                x - margen,
                y - margen,
                w + 2 * margen,
                h + 2 * margen,
                false
        );

        hex.setFill(texturaAgua);
        hex.setStroke(Color.DARKBLUE);
        hex.setStrokeWidth(2);

        tableroPane.getChildren().add(0, hex); // al fondo
    }


    private ImagePattern crearTexturaPara(Terreno terreno, Polygon p) {
        String ruta = "hexagonos/desierto.png";
        Recurso recurso = terreno.recurso();
        if (recurso instanceof Madera) {
            ruta = "/hexagonos/bosque.png";
        } else if (recurso instanceof Grano) {
            ruta = "/hexagonos/campo.png";
        } else if (recurso instanceof Desierto) {
            ruta = "/hexagonos/desierto.png";
        } else if (recurso instanceof Mineral) {
            ruta = "/hexagonos/montaña.png";
        } else if (recurso instanceof Ladrillo) {
            ruta = "/hexagonos/colina.png";
        } else if (recurso instanceof Lana) {
            ruta = "/hexagonos/pastizal.png";
        }
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
        Bounds bounds = p.getBoundsInLocal();
        double x = bounds.getMinX();
        double y = bounds.getMinY();
        double w = bounds.getWidth();
        double h = bounds.getHeight();
        return new ImagePattern(img, x, y, w, h, false);
    }

    private Image obtenerImagenFicha(int numero) {
        if (numero == 0) return null;

        if (fichasCache.containsKey(numero)) {
            return fichasCache.get(numero);
        }

        String sufijo = (numero == 6 || numero == 8) ? "B" : "";
        String ruta = String.format("/fichas/ficha_n_%d%s.png",
                numero, sufijo);

        Image img = new Image(
                Objects.requireNonNull(getClass().getResourceAsStream(ruta))
        );

        fichasCache.put(numero, img);
        return img;
    }
    private void agregarFichaNumero(Terreno terreno, Polygon hexagono) {
        int numero = terreno.numeroFicha();
        if (numero == 0) return; // desierto, sin ficha

        Image imagen = obtenerImagenFicha(numero);
        if (imagen == null) return;

        // Centro del hexágono
        Bounds b = hexagono.getBoundsInLocal();
        double cx = (b.getMinX() + b.getMaxX()) / 2.0;
        double cy = (b.getMinY() + b.getMaxY()) / 2.0;

        // Tamaño de la ficha relativo al hexágono
        double size = Math.min(b.getWidth(), b.getHeight()) * 0.45;

        ImageView fichaView = new ImageView(imagen);
        fichaView.setFitWidth(size);
        fichaView.setFitHeight(size);
        fichaView.setPreserveRatio(true);
        fichaView.setSmooth(true);

        fichaView.setLayoutX(cx - size / 2);
        fichaView.setLayoutY(cy - size / 2);

        // La agregamos encima del terreno pero antes que vértices/caminos
        tableroPane.getChildren().add(fichaView);
    }

}