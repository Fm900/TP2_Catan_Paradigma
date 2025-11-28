package edu.fiuba.algo3.vistas.Tablero;

import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VistaTablero {

    private final Tablero tablero;
    private final Pane root;
    private final Map<Vertice, Point2D> posiciones = new HashMap<>();

    public VistaTablero(Tablero tablero) {
        this.tablero = tablero;
        this.root = new Pane();
        root.setPrefSize(1200, 800);

        calcularPosicionesDeVertices();

        // 1) Primero terrenos (hexágonos de colores)
        dibujarTerrenos();

        // 2) Luego aristas (para que queden arriba del terreno)
        dibujarAristas();

        // 3) Finalmente vértices (casitas futuras)
        dibujarVertices();
    }

    public Pane getRoot() {
        return root;
    }

    // =======================
    // POSICIONES
    // =======================

    private void calcularPosicionesDeVertices() {

        int[][] filas = {
                {1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 10, 11},
                {12, 13, 14, 15, 16},
                {17, 18, 19, 20, 21},
                {22, 23, 24, 25, 26, 27},
                {28, 29, 30, 31, 32, 33},
                {34, 35, 36, 37, 38},
                {39, 40, 41, 42, 43},
                {44, 45, 46, 47},
                {48, 49, 50, 51},
                {52, 53, 54}
        };

        double centerX = 600;
        double startY  = 80;
        double dx = 90;
        double dy = 55;

        List<Vertice> vs = tablero.vertices();

        for (int fila = 0; fila < filas.length; fila++) {
            int[] ids = filas[fila];
            int n = ids.length;

            double rowWidth = (n - 1) * dx;
            double startX = centerX - rowWidth / 2.0;
            double y = startY + fila * dy;

            for (int i = 0; i < n; i++) {
                int id = ids[i];
                Vertice v = vs.get(id - 1);
                double x = startX + i * dx;

                posiciones.put(v, new Point2D(x, y));
            }
        }
    }

    // =======================
    // TERRENOS (HEXÁGONOS)
    // =======================

    private void dibujarTerrenos() {
        for (Terreno terreno : tablero.terrenos()) {

            List<Vertice> vs = terreno.verticesAdyacentes();
            if (vs == null || vs.size() < 6) continue;

            Polygon hex = new Polygon();

            double sx = 0;
            double sy = 0;

            for (Vertice v : vs) {
                Point2D p = posiciones.get(v);
                if (p == null) continue;

                hex.getPoints().addAll(p.getX(), p.getY());
                sx += p.getX();
                sy += p.getY();
            }

            // color según recurso
            hex.setFill(colorPara(terreno.recurso()));
            hex.setStroke(Color.web("#8b7b6b"));
            hex.setStrokeWidth(3);

            root.getChildren().add(hex);

            // centro geométrico para el número de la ficha
            double cx = sx / vs.size();
            double cy = sy / vs.size();

            int numero = terreno.numeroFicha();
            if (numero != 0) {
                Text texto = new Text(String.valueOf(numero));
                texto.setX(cx - 6);
                texto.setY(cy + 4);
                texto.setFill(Color.BLACK);
                texto.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
                root.getChildren().add(texto);
            }
        }
    }


    private Paint colorPara(Recurso recurso) {
        // ajustá los tipos según tus clases reales
        if (recurso instanceof Madera)   return Color.web("#228B22");   // bosque
        if (recurso instanceof Ladrillo) return Color.web("#B22222");   // colina
        if (recurso instanceof Grano)    return Color.web("#FFD700");   // campo
        if (recurso instanceof Lana)     return Color.web("#9ACD32");   // pastizal
        if (recurso instanceof Mineral)  return Color.web("#808080");   // montaña
        // desierto u otro
        return Color.web("#DEB887");
    }

    // =======================
    // ARISTAS
    // =======================

    private void dibujarAristas() {
        for (Arista a : tablero.aristas()) {
            Vertice v1 = a.extremo1();
            Vertice v2 = a.extremo2();

            Point2D p1 = posiciones.get(v1);
            Point2D p2 = posiciones.get(v2);

            if (p1 == null || p2 == null) continue;

            Line line = new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            line.setStroke(Color.web("#C0B0C0"));
            line.setStrokeWidth(4);

            root.getChildren().add(line);
        }
    }

    // =======================
    // VÉRTICES
    // =======================

    private void dibujarVertices() {
        List<Vertice> vs = tablero.vertices();

        for (Vertice v : vs) {
            Point2D p = posiciones.get(v);
            if (p == null) continue;

            Circle c = new Circle(p.getX(), p.getY(), 9);
            c.setFill(Color.BEIGE);
            c.setStroke(Color.web("#555555"));
            c.setStrokeWidth(2);

            // 👇 ya no dibujamos los IDs
            root.getChildren().add(c);
        }
    }
}

