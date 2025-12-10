package edu.fiuba.algo3.vistas.Tablero;

import edu.fiuba.algo3.controllers.ControladorDeClickTablero;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Puerto;
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
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;


import java.util.*;

public class GeneradorVistaTablero {
    private final StackPane root;
    private Group tableroPane;
    private Image imagenAgua;
    private final Map<Integer, Image> fichasCache = new HashMap<>();
    private ControladorDeClickTablero controladorDeClickTablero;
    private final Map<VerticeVista, Vertice> mapaCirculoVertice = new HashMap<>();
    private final Map<Line, Arista> mapaLineaArista = new HashMap<>();
    private VerticeVista verticeSeleccionado = null;
    private Line aristaSeleccionada = null;
    private final Map<String, Image> barcosCache = new HashMap<>();


    public GeneradorVistaTablero(StackPane root) {
        this.root = root;
    }

    public void crearTablero(List<Terreno> terrenos, List<Vertice> verticesModelo, List<Arista> aristasModelo, List<Puerto> puertosModelo) {

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

            dibujarPuertos(puertosModelo);
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

    public void resetearSeleccion() {
        if (verticeSeleccionado != null) {
            verticeSeleccionado.deseleccionar();
            verticeSeleccionado = null;
        }
        if (aristaSeleccionada != null) {
            aristaSeleccionada.setStroke(Color.GRAY);
            aristaSeleccionada = null;
        }
    }

    public void colocarCasa(VerticeVista vista, Color colorJugador) {
        if (vista != null) {
            vista.mostrarCasa(colorJugador);
            vista.setOnMouseClicked(null);
        }
    }
    public void colocarCiudad(VerticeVista vista,Color color) {
        vista.mostrarCiudad(color);
        vista.setOnMouseClicked(null);
    }

    public void colorearArista(Line linea, Color colorJugador) {
        if (linea != null) {
            linea.setStroke(colorJugador);
            linea.setStrokeWidth(6);
            linea.setOnMouseClicked(null);
        }
    }

    private Polygon crearHexagonoDesdeModelo(Terreno terreno) {

        Polygon p = new Polygon();

        terreno.verticesAdyacentes().forEach(v ->
                p.getPoints().addAll(v.getX(), v.getY())
        );

        ImagePattern textura = crearTexturaPara(terreno, p);
        p.setFill(textura);

        p.setStroke(Color.BLACK);
        p.setStrokeWidth(2);

        return p;
    }

    private void dibujarVertices(List<Vertice> verticesModelo) {
        for (Vertice v : verticesModelo) {

            VerticeVista vista = new VerticeVista(v);

            mapaCirculoVertice.put(vista, v);

            vista.setOnMouseClicked(e -> {
                if (verticeSeleccionado != null && verticeSeleccionado != vista) {
                    verticeSeleccionado.deseleccionar();
                }
                if (verticeSeleccionado == vista) {
                    vista.deseleccionar();
                    verticeSeleccionado = null;
                    if (controladorDeClickTablero != null) {
                        controladorDeClickTablero.onSeleccionCancelada();
                    }
                } else {
                    vista.seleccionar();
                    verticeSeleccionado = vista;
                    if (controladorDeClickTablero != null) {
                        controladorDeClickTablero.onVerticeSeleccionado(v, vista);
                    }
                }
            });

            tableroPane.getChildren().add(vista);
        }
    }


    private void dibujarAristas(List<Arista> aristasModelo) {
        for (Arista a : aristasModelo) {
            Vertice v1 = a.extremo1();
            Vertice v2 = a.extremo2();

            Line l = new Line(v1.getX(), v1.getY(), v2.getX(), v2.getY());
            l.setStroke(Color.GRAY);
            l.setStrokeWidth(7);

            mapaLineaArista.put(l, a);

            l.setOnMouseClicked(e -> {
                if (aristaSeleccionada != null && aristaSeleccionada != l) {
                    aristaSeleccionada.setStroke(Color.GRAY);
                }

                if (aristaSeleccionada == l) {
                    l.setStroke(Color.GRAY);
                    aristaSeleccionada = null;
                    if (controladorDeClickTablero != null) {
                        controladorDeClickTablero.onSeleccionCancelada();
                    }
                } else {
                    l.setStroke(Color.BLUE);
                    aristaSeleccionada = l;
                    if (controladorDeClickTablero != null) {
                        controladorDeClickTablero.onAristaSeleccionada(a, l);
                    }
                }
            });

            tableroPane.getChildren().add(l);
        }
    }

    private void dibujarHexagonosAgua() {

        double RADIO = 70;
        double ALTURA = RADIO * Math.sqrt(3);
        int[] filas = {3, 4, 5, 4, 3};
        int filaMax = 5;

        double dx = ALTURA;
        double dy = RADIO * 1.5;

        dibujarFilaAgua(-1, 4, dx, dy, filaMax, RADIO);
        dibujarHexagonoAgua(0, -1, dx, dy, filaMax, 3, RADIO);
        dibujarHexagonoAgua(1, -1, dx, dy, filaMax, 4, RADIO);
        dibujarHexagonoAgua(2, -1, dx, dy, filaMax, 5, RADIO);
        dibujarHexagonoAgua(3, -1, dx, dy, filaMax, 4, RADIO);
        dibujarHexagonoAgua(4, -1, dx, dy, filaMax, 3, RADIO);
        dibujarHexagonoAgua(0, 3, dx, dy, filaMax, 3, RADIO);
        dibujarHexagonoAgua(1, 4, dx, dy, filaMax, 4, RADIO);
        dibujarHexagonoAgua(2, 5, dx, dy, filaMax, 5, RADIO);
        dibujarHexagonoAgua(3, 4, dx, dy, filaMax, 4, RADIO);
        dibujarHexagonoAgua(4, 3, dx, dy, filaMax, 3, RADIO);
        dibujarFilaAgua(5, 4, dx, dy, filaMax, RADIO);
    }

    private void dibujarFilaAgua(int fila, int cantidad, double dx, double dy, int filaMax, double radio) {
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

        Image img = getImagenAgua();

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

        tableroPane.getChildren().add(0, hex);
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

    public void setListener(ControladorDeClickTablero controladorGeneral) {
        this.controladorDeClickTablero = controladorGeneral;
    }


    private Image obtenerImagenBarco(Puerto puerto) {
        String nombre;

        if (puerto.getRecurso() == null) {
            nombre = "barcoGenerico.png";
        } else if (puerto.getRecurso() instanceof Madera) {
            nombre = "barcoMadera.png";
        } else if (puerto.getRecurso() instanceof Grano) {
            nombre = "barcoTrigo.png";
        } else if (puerto.getRecurso() instanceof Lana) {
            nombre = "barcoLana.png";
        } else if (puerto.getRecurso() instanceof Ladrillo) {
            nombre = "barcoLadrillo.png";
        } else if (puerto.getRecurso() instanceof Mineral) {
            nombre = "barcoMineral.png";
        } else {
            nombre = "barcoGenerico.png";
        }

        if (barcosCache.containsKey(nombre)) {
            return barcosCache.get(nombre);
        }

        String ruta = "/Imagenes/" + nombre;
        Image img = new Image(
                Objects.requireNonNull(
                        getClass().getResourceAsStream(ruta),
                        "No se encontró la imagen de barco: " + ruta
                )
        );
        barcosCache.put(nombre, img);
        return img;
    }



    private void dibujarPuertos(List<Puerto> puertosModelo) {
        if (puertosModelo == null) return;

        double cx = tableroPane.getBoundsInLocal().getCenterX();
        double cy = tableroPane.getBoundsInLocal().getCenterY();

        for (Puerto puerto : puertosModelo) {

            Arista a = puerto.getArista();
            Vertice v1 = a.extremo1();
            Vertice v2 = a.extremo2();

            double x1 = v1.getX();
            double y1 = v1.getY();
            double x2 = v2.getX();
            double y2 = v2.getY();

            // Punto medio de la arista
            double midX = (x1 + x2) / 2.0;
            double midY = (y1 + y2) / 2.0;

            // Vector normal a la arista
            double dx = x2 - x1;
            double dy = y2 - y1;

            double nx = -dy;
            double ny = dx;
            double nlen = Math.hypot(nx, ny);
            if (nlen == 0) nlen = 1;
            nx /= nlen;
            ny /= nlen;

            double candidateX1 = midX + nx * 45;
            double candidateY1 = midY + ny * 45;
            double candidateX2 = midX - nx * 45;
            double candidateY2 = midY - ny * 45;

            double d1 = Math.hypot(candidateX1 - cx, candidateY1 - cy);
            double d2 = Math.hypot(candidateX2 - cx, candidateY2 - cy);

            double portX = (d1 > d2) ? candidateX1 : candidateX2;
            double portY = (d1 > d2) ? candidateY1 : candidateY2;


            double shorten = 12.0;

            // hacia v1
            double vx1 = x1 - portX;
            double vy1 = y1 - portY;
            double len1 = Math.hypot(vx1, vy1);
            double end1X = portX + vx1 * ((len1 - shorten) / len1);
            double end1Y = portY + vy1 * ((len1 - shorten) / len1);

            // hacia v2
            double vx2 = x2 - portX;
            double vy2 = y2 - portY;
            double len2 = Math.hypot(vx2, vy2);
            double end2X = portX + vx2 * ((len2 - shorten) / len2);
            double end2Y = portY + vy2 * ((len2 - shorten) / len2);

            Line l1 = new Line(portX, portY, end1X, end1Y);
            Line l2 = new Line(portX, portY, end2X, end2Y);
            l1.setStroke(Color.BURLYWOOD);
            l2.setStroke(Color.BURLYWOOD);
            l1.setStrokeWidth(4);
            l2.setStrokeWidth(4);
            l1.setStrokeLineCap(StrokeLineCap.ROUND);
            l2.setStrokeLineCap(StrokeLineCap.ROUND);

            // ==== imagen del barco ====
            Image imgBarco = obtenerImagenBarco(puerto);
            ImageView barcoView = new ImageView(imgBarco);

            // tamaño del barco en pantalla
            double anchoBarco = 70;
            barcoView.setFitWidth(anchoBarco);
            barcoView.setPreserveRatio(true);

            double escala = anchoBarco / imgBarco.getWidth();
            double altoBarco = imgBarco.getHeight() * escala;
            barcoView.setLayoutX(portX - anchoBarco / 2.0);
            barcoView.setLayoutY(portY - altoBarco / 2.0);

            tableroPane.getChildren().addAll(l1, l2, barcoView);

        }
    }

    public void actualizarReferencias() {
        verticeSeleccionado = null;
        aristaSeleccionada = null;
    }

}