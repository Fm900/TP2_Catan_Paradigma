package edu.fiuba.algo3.vistas.Tablero;

import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import edu.fiuba.algo3.vistas.Principales.EscenaGeneral;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VistaTablero extends EscenaGeneral {

    private final Tablero tablero;
    private final Map<Vertice, Point2D> posiciones = new HashMap<>();

    public VistaTablero(Tablero tablero, Stage stage) {
        super(stage);
        this.tablero = tablero;

        // Inicializar el tablero
        inicializarTablero();
    }

    private void inicializarTablero() {
        calcularPosicionesDeVertices();

        // Orden de dibujo importante
        dibujarTerrenos();
        dibujarAristas();
        dibujarVertices();
    }

    @Override
    protected Pane createLayout() {
        Pane root = new Pane();
        root.setPrefSize(1600, 1000);
        // Fondo sólido en lugar de imagen
        root.setStyle("-fx-background-color: #1a1a2e;");
        return root;
    }

    @Override
    protected void createControllers(Stage stage) {
        // Aquí puedes agregar interactividad al tablero
        // Por ejemplo: clicks en vértices para construir
        setupInteracciones();
    }

    @Override
    protected void createStyles() {
        // Sin CSS - los estilos se aplican directamente en los métodos de dibujo
    }

    @Override
    protected String getBackgroundImagePath() {
        // Sin imagen de fondo
        return "";
    }

    private void setupInteracciones() {
        // Ejemplo: hacer los vértices clickeables
        for (javafx.scene.Node node : root.getChildren()) {
            if (node instanceof Circle) {
                node.setOnMouseClicked(event -> {
                    Circle circle = (Circle) node;
                    System.out.println("Vértice clickeado en: " + circle.getCenterX() + ", " + circle.getCenterY());
                    // Aquí tu lógica para construir casas/ciudades
                });
            }
        }
    }


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

        double centerX = 800;
        double startY  = 100;
        double dx = 120;
        double dy = 75;

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

            // Aplicar estilos directamente al polígono
            hex.setFill(colorPara(terreno.recurso()));
            hex.setStroke(Color.web("#8b7b6b"));
            hex.setStrokeWidth(3);

            root.getChildren().add(hex);

            // Texto para número de ficha
            double cx = sx / vs.size();
            double cy = sy / vs.size();

            int numero = terreno.numeroFicha();
            if (numero != 0) {
                Text texto = new Text(String.valueOf(numero));
                texto.setX(cx - 8);
                texto.setY(cy + 6);
                texto.setFill(Color.BLACK);
                texto.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                root.getChildren().add(texto);
            }
        }
    }


    private Paint colorPara(Recurso recurso) {
        if (recurso instanceof Madera)   return Color.web("#228B22");
        if (recurso instanceof Ladrillo) return Color.web("#B22222");
        if (recurso instanceof Grano)    return Color.web("#FFD700");
        if (recurso instanceof Lana)     return Color.web("#9ACD32");
        if (recurso instanceof Mineral)  return Color.web("#808080");
        return Color.web("#DEB887"); // Desierto
    }

    private void dibujarAristas() {
        for (Arista a : tablero.aristas()) {
            Vertice v1 = a.extremo1();
            Vertice v2 = a.extremo2();

            Point2D p1 = posiciones.get(v1);
            Point2D p2 = posiciones.get(v2);

            if (p1 == null || p2 == null) continue;

            Line line = new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            line.setStroke(Color.web("#C0B0C0"));
            line.setStrokeWidth(6);

            root.getChildren().add(line);
        }
    }

    private void dibujarVertices() {
        List<Vertice> vs = tablero.vertices();

        for (Vertice v : vs) {
            Point2D p = posiciones.get(v);
            if (p == null) continue;

            Circle c = new Circle(p.getX(), p.getY(), 12);
            c.setFill(Color.BEIGE);
            c.setStroke(Color.web("#555555"));
            c.setStrokeWidth(2);

            // Hacer vértices interactivos
            c.setOnMouseEntered(e -> c.setFill(Color.LIGHTBLUE));
            c.setOnMouseExited(e -> c.setFill(Color.BEIGE));

            root.getChildren().add(c);
        }
    }

    public void limpiarTablero() {
        root.getChildren().clear();
    }

    // Método para actualizar la vista
    public void actualizarVista() {
        limpiarTablero();
        inicializarTablero();
    }
}