package edu.fiuba.algo3.vistas.Tablero;

import edu.fiuba.algo3.controllers.ControladorDeClickTablero;
import edu.fiuba.algo3.controllers.ControladorJugarCartas;
import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Cartas.ParametrosCarta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VistaCartas {

    private final ControladorJugarCartas controlador;
    private ControladorDeClickTablero controladorDeClickTablero;
    private Stage ownerStage;
    private ParametrosCarta parametrosCarta;
    private ManejoTurnos manejoTurnos;

    public VistaCartas(ControladorJugarCartas controlador,ControladorDeClickTablero controladorDeClickTablero,ManejoTurnos manejoTurnos) {
        this.controlador = controlador;
        this.controladorDeClickTablero = controladorDeClickTablero;
        parametrosCarta = new ParametrosCarta();
        this.manejoTurnos = manejoTurnos;
    }

    public void mostrar(Stage primaryStage) {

        this.ownerStage = primaryStage;
        Stage miniStage = new Stage();
        miniStage.setTitle("Cartas del jugador");
        miniStage.initModality(Modality.APPLICATION_MODAL);
        miniStage.initOwner(primaryStage);
        miniStage.setResizable(false);

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        layout.setStyle("-fx-border-radius: 10;" + "-fx-background-color: rgba(0,0,0,0.5);" +"-fx-padding: 20;");

        Label titulo = new Label("Cartas del jugador");
        titulo.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        layout.getChildren().add(titulo);

        VBox lista = new VBox(15);
        lista.setAlignment(Pos.TOP_CENTER);

        List<Carta> cartas = controlador.obtenerCartasJugador();

        for (Carta carta : cartas) {

            HBox fila = new HBox(10);
            fila.setAlignment(Pos.CENTER_LEFT);

            // ----------- IMAGEN DE LA CARTA -----------
            String nombre = carta.getClass().getSimpleName();
            String ruta = "/imagenes/" + nombre + ".png";

            ImageView imagen = new ImageView(new Image(ruta));
            imagen.setFitWidth(80);
            imagen.setFitHeight(80);

            // ----------- NOMBRE DE LA CARTA -----------
            Label nombreCarta = new Label(nombre);
            nombreCarta.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

            // ----------- BOTÓN JUGAR -----------
            Button jugar = new Button("Jugar");
            jugar.setStyle(
                    "-fx-border-radius: 10;" +
                            " -fx-border-width: 2;" +
                            " -fx-border-color: white;" +
                            " -fx-background-radius: 10;" +
                            " -fx-background-color: linear-gradient(to bottom, #2e4d2e, #1f331f);"
            );

            jugar.setOnAction(e -> {
                controlador.jugarCarta(carta);

                // ------------------ flujo según tipo de carta ------------------
                String tipoCarta = carta.getClass().getSimpleName();

                switch (tipoCarta) {
                    case "Caballero":
                        mostrarAlerta("Debes elegir un Terreno y un Vertice.");
                        controladorDeClickTablero.moverLadron();
                        break;
                    case "Descubrimiento":
                        mostrarAlerta("Debes elegir 2 recursos de la banca.");
                        mostrarCartaDeDescubrimiento(carta);
                        break;
                    case "ConstruccionDeCarreteras":
                        mostrarAlerta("Debes elegir 2 aristas.");

                        break;
                    case "Monopolio":
                        mostrarAlerta("Debes elegir un recurso.");
                        mostrarCartasDeJugadores(carta);
                        break;
                    case "PuntosDeVictoria":
                        // no hace nada
                        break;
                    default:
                        mostrarAlerta("Carta no reconocida.");
                }

                miniStage.close();
            });

            fila.getChildren().addAll(imagen, nombreCarta, jugar);
            lista.getChildren().add(fila);
        }

        ScrollPane scroll = new ScrollPane(lista);
        scroll.setFitToWidth(true);
        scroll.setPrefHeight(300);

        layout.getChildren().add(scroll);

        Button cerrar = new Button("Cerrar");
        cerrar.setOnAction(e -> miniStage.close());
        layout.getChildren().add(cerrar);

        Scene scene = new Scene(layout, 350, 450);
        miniStage.setScene(scene);
        miniStage.showAndWait();
    }
    private void mostrarAlerta(String mensaje) {
        Stage aviso = new Stage();
        aviso.initModality(Modality.APPLICATION_MODAL);
        aviso.setTitle("Acción requerida");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label texto = new Label(mensaje);
        texto.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Button ok = new Button("OK");
        ok.setOnAction(e -> aviso.close());

        layout.getChildren().addAll(texto, ok);

        Scene scene = new Scene(layout, 300, 120);
        aviso.setScene(scene);
        aviso.showAndWait();
    }

    private void mostrarCartaDeDescubrimiento(Carta carta){
        Stage modal = crearVentanaModal("Carta de Descubrimiento");

        BorderPane root = new BorderPane();
        VBox panel = new VBox(20);
        panel.setPadding(new Insets(20));
        panel.setAlignment(Pos.TOP_CENTER);

        Label titulo = new Label("Elegí 2 recursos de la banca, como regalo");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        List<Recurso> recursosBanca = Banca.getInstance().getRecursos();
        List<Recurso> seleccionBanca = new ArrayList<>();

        VBox panelBanca = crearPanelDeRecursos("Recursos de la Banca", recursosBanca, seleccionBanca);

        Button btnConfirmar = new Button("Confirmar intercambio");
        btnConfirmar.setOnAction(e -> {
            parametrosCarta.setRecursosElegidos(seleccionBanca);
            controlador.activarCarta(carta, parametrosCarta);
            modal.close(); // Cerrar el modal después de confirmar
        });

        panel.getChildren().addAll(titulo, panelBanca, btnConfirmar);

        root.setCenter(panel);

        Scene scene = new Scene(root, 500, 400); // Ajusta el tamaño según necesites
        modal.setScene(scene);

        modal.showAndWait();
    }

    private Stage crearVentanaModal(String titulo) {
        Stage modal = new Stage();
        modal.initOwner(ownerStage);
        modal.initModality(Modality.WINDOW_MODAL);
        modal.setTitle(titulo);
        modal.setMinWidth(900);
        modal.setMinHeight(600);
        modal.setWidth(1280);
        modal.setHeight(720);

        // Blur al fondo
        if (ownerStage.getScene() != null && ownerStage.getScene().getRoot() != null) {
            ownerStage.getScene().getRoot().setEffect(new GaussianBlur(10));
        }

        // Asegurarnos de limpiar el blur siempre que la ventana se cierre
        modal.setOnHidden(e -> {
            if (ownerStage.getScene() != null && ownerStage.getScene().getRoot() != null) {
                ownerStage.getScene().getRoot().setEffect(null);
            }
        });

        return modal;
    }
    private VBox crearPanelDeRecursos(String titulo, List<Recurso> recursos, List<Recurso> listaSeleccionada) {
        VBox contenedor = new VBox(10);
        contenedor.setPadding(new Insets(10));

        Label label = new Label(titulo);
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        FlowPane cartas = new FlowPane();
        cartas.setHgap(10);
        cartas.setVgap(10);
        cartas.setPrefWrapLength(800);

        for (Recurso recurso : recursos) {
            Label carta = new Label(recurso.getClass().getSimpleName());
            carta.setPadding(new Insets(10));
            carta.setStyle("-fx-border-color: black; -fx-background-color: white;");
            carta.setMinWidth(100);
            carta.setAlignment(Pos.CENTER);

            carta.setOnMouseClicked(ev -> {
                if (!listaSeleccionada.contains(recurso)) {
                    listaSeleccionada.add(recurso);
                    carta.setStyle("-fx-border-color: green; -fx-background-color: lightgreen;");
                } else {
                    listaSeleccionada.remove(recurso);
                    carta.setStyle("-fx-border-color: black; -fx-background-color: white;");
                }
            });

            cartas.getChildren().add(carta);
        }

        contenedor.getChildren().addAll(label, cartas);
        return contenedor;
    }

    private void mostrarCartasDeJugadores(Carta carta){
        Stage modal = crearVentanaModal("Carta de Monopolio");

        BorderPane root = new BorderPane();
        VBox panel = new VBox(20);
        panel.setPadding(new Insets(20));
        panel.setAlignment(Pos.TOP_CENTER);

        Label titulo = new Label("Elegí 1 recurso");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        List<Jugador> jugadores = Juego.getInstancia().getJugadores();
        jugadores.remove(manejoTurnos.jugadorActual());

        Set<Recurso> recursosIguales = new HashSet<>(jugadores.get(0).obtenerRecursos());

        for (Jugador jugador : jugadores) {
            if (jugador == jugadores.get(0)) continue;
            recursosIguales.retainAll(jugador.obtenerRecursos());
        }

        List<Recurso> listaRecursosComunes = new ArrayList<>(recursosIguales);
        List<Recurso> seleccionBanca = new ArrayList<>();

        VBox panelBanca = crearPanelDeRecursos("Recursos en comun de jugadores", listaRecursosComunes, seleccionBanca);

        Button btnConfirmar = new Button("Confirmar intercambio");
        btnConfirmar.setOnAction(e -> {
            parametrosCarta.setRecursoMonopolio((Recurso) seleccionBanca);
            controlador.activarCarta(carta, parametrosCarta);
            modal.close();
        });

        panel.getChildren().addAll(titulo, panelBanca, btnConfirmar);

        root.setCenter(panel);

        Scene scene = new Scene(root, 500, 400);
        modal.setScene(scene);

        modal.showAndWait();
    }
}