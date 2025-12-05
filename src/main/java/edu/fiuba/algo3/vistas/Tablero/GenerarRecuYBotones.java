package edu.fiuba.algo3.vistas.Tablero;

import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class GenerarRecuYBotones {
    private Jugador jugadorActual;
    private Button btnLanzarDados;
    private Button btnTerminarTurno;
    private Button btnComerciar;
    private Button btnCartas;
    private HBox panelAbajo;
    private HBox botonesDerecha;
    private HBox botonesIzquierda;
    private static final String[] RUTAS_RECURSOS = {
            "/Imagenes/madera.png", "/Imagenes/grano.png", "/Imagenes/ladrillo.png",
            "/Imagenes/lana.png", "/Imagenes/mineral.png"
    };

    private static final String[] NOMBRES_RECURSOS = {
            "Madera", "Grano", "Ladrillo", "Lana", "Mineral"
    };

    // Estilos CSS como constantes para reutilización
    private static final String ESTILO_PANEL_RECURSOS =
            "-fx-background-color: linear-gradient(to bottom, rgba(42, 85, 42, 0.95), rgba(28, 60, 28, 0.95)); " +
                    "-fx-background-radius: 25 25 0 0; " +
                    "-fx-border-color: rgba(200, 200, 200, 0.4); " +
                    "-fx-border-width: 2; " +
                    "-fx-border-radius: 25 25 0 0; " +
                    "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.6), 8, 0, 0, 2);";

    private static final String ESTILO_BOTON_BASE =
            "-fx-background-color: linear-gradient(to bottom, rgba(42, 85, 42, 0.95), rgba(28, 60, 28, 0.95)); " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-font-size: 14px; " +
                    "-fx-padding: 10 20 10 20; " +
                    "-fx-background-radius: 8; " +
                    "-fx-border-radius: 8; " +
                    "-fx-border-color: #2E8B57; " +
                    "-fx-border-width: 2; " +
                    "-fx-cursor: hand;";

    private static final String ESTILO_BOTON_HOVER =
            "-fx-background-color: linear-gradient(to bottom, #66BB6A, #4CAF50); " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-font-size: 14px; " +
                    "-fx-padding: 10 20 10 20; " +
                    "-fx-background-radius: 8; " +
                    "-fx-border-radius: 8; " +
                    "-fx-border-color: #388E3C; " +
                    "-fx-border-width: 2; " +
                    "-fx-cursor: hand;";



    public GenerarRecuYBotones(HBox panelAbajo,HBox botonesDerecha, HBox botonesIzquierda ,Jugador jugadorActual){
        this.panelAbajo = panelAbajo;
        this.botonesDerecha = botonesDerecha;
        this.jugadorActual = jugadorActual;
        this.botonesIzquierda = botonesIzquierda;
    }
    public void construir() {
        crearBotonesIzquierda();
        crearPanelInferior();
        crearBotonesDerecha();

    }

    private void crearPanelInferior() {
        configurarPanelInferior();
        HBox panelRecursos = crearPanelRecursos();
        panelAbajo.getChildren().add(panelRecursos);
        panelAbajo.setPadding(new Insets(0, 0, 10, 120));
    }

    private void configurarPanelInferior() {
        panelAbajo.setAlignment(Pos.CENTER);
        panelAbajo.setStyle("-fx-background-color: transparent;");
    }

    private HBox crearPanelRecursos() {
        HBox panel = new HBox();
        panel.setAlignment(Pos.CENTER);


        panel.setStyle(ESTILO_PANEL_RECURSOS);
        panel.setMinHeight(40);
        panel.setMinWidth(350);

        agregarRecursosAlPanel(panel);
        return panel;
    }

    private void agregarRecursosAlPanel(HBox panel) {
        List<Integer> cantidades = jugadorActual.obtenerCantidadDeRecursos();

        for (int i = 0; i < NOMBRES_RECURSOS.length; i++) {
            HBox itemRecurso = crearItemRecurso(i, cantidades.get(i));
            panel.getChildren().add(itemRecurso);

            if (i < NOMBRES_RECURSOS.length - 1) {
                panel.getChildren().add(crearSeparador());
            }
        }
    }

    private HBox crearItemRecurso(int indice, int cantidad) {
        HBox item = new HBox(5);
        item.setAlignment(Pos.CENTER);
        item.setMinWidth(60);

        Label labelCantidad = crearLabelCantidad(cantidad);
        item.getChildren().add(labelCantidad);

        agregarIconoRecurso(item, indice);
        agregarTooltip(item, NOMBRES_RECURSOS[indice]);

        return item;
    }

    private Label crearLabelCantidad(int cantidad) {
        Label label = new Label(String.valueOf(cantidad));
        label.setStyle(
                "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-family: 'Arial', sans-serif; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.8), 2, 0, 0, 1);"
        );
        return label;
    }

    private void agregarIconoRecurso(HBox item, int indice) {
        try {
            ImageView icono = cargarIconoRecurso(indice);
            item.getChildren().add(icono);
        } catch (Exception e) {
            Label fallback = crearFallbackRecurso(indice);
            item.getChildren().add(fallback);
        }
    }

    private ImageView cargarIconoRecurso(int indice) {
        ImageView icono = new ImageView();
        java.net.URL url = getClass().getResource(RUTAS_RECURSOS[indice]);

        if (url != null) {
            icono.setImage(new Image(url.toString()));
            icono.setFitWidth(20);
            icono.setFitHeight(20);
            icono.setPreserveRatio(true);
            icono.setEffect(new Glow(0.1));
        } else {
            throw new RuntimeException("Imagen no encontrada");
        }

        return icono;
    }

    private Label crearFallbackRecurso(int indice) {
        String[] fallbacks = {"M", "G", "L", "W", "O"};
        Label label = new Label(fallbacks[indice]);
        label.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 12px; -fx-font-weight: bold;");
        return label;
    }

    private Region crearSeparador() {
        Region separador = new Region();
        separador.setPrefWidth(1);
        separador.setPrefHeight(20);
        separador.setStyle("-fx-background-color: rgba(255, 255, 255, 0.15);");
        return separador;
    }

    private void agregarTooltip(HBox item, String texto) {
        Tooltip tooltip = new Tooltip(texto);
        Tooltip.install(item, tooltip);
    }

    // ==================== BOTONES DE ACCIÓN ====================
    private void crearBotonesDerecha() {
        botonesDerecha.setAlignment(Pos.BOTTOM_RIGHT);
        botonesDerecha.setPadding(new Insets(0, 10, 10, 0));

        btnLanzarDados = crearBotonConIcono("/Imagenes/dados.png");
        btnComerciar = crearBotonConIcono("/Imagenes/comerciar.png");
        btnTerminarTurno = crearBotonConIcono("/Imagenes/finalizar.png");

        configurarAccionesBotones();

        botonesDerecha.getChildren().addAll(btnLanzarDados, btnComerciar, btnTerminarTurno);
    }
    private void crearBotonesIzquierda() {
        botonesIzquierda.setAlignment(Pos.BOTTOM_LEFT);
        botonesIzquierda.setPadding(new Insets(0, 0, 10, 10));

        btnCartas = crearBotonConIcono("/Imagenes/cartas.png");

        botonesIzquierda.getChildren().addAll(btnCartas);
    }

    private void configurarAccionesBotones() {
        btnLanzarDados.setOnAction(e -> accionLanzarDados());
        btnComerciar.setOnAction(e -> accionComerciar());
        btnTerminarTurno.setOnAction(e -> accionTerminarTurno());
        btnCartas.setOnAction(e -> accionarMostrarCartas());
    }

    private Button crearBotonConIcono(String rutaIcono) {
        Button boton = new Button();

        try {
            ImageView icono = cargarIconoBoton(rutaIcono);
            boton.setGraphic(icono);
        } catch (Exception e) {
            System.err.println("Error cargando icono: " + rutaIcono);
        }

        aplicarEstiloBoton(boton);
        agregarEfectosHoverBoton(boton);
        agregarSombraBoton(boton);

        return boton;
    }

    private ImageView cargarIconoBoton(String ruta) {
        Image imagen = new Image(getClass().getResourceAsStream(ruta));
        ImageView icono = new ImageView(imagen);
        icono.setFitWidth(20);
        icono.setFitHeight(25);
        icono.setPreserveRatio(true);
        return icono;
    }

    private void aplicarEstiloBoton(Button boton) {
        boton.setStyle(ESTILO_BOTON_BASE);
    }

    private void agregarEfectosHoverBoton(Button boton) {
        boton.setOnMouseEntered(e -> {
            boton.setStyle(ESTILO_BOTON_HOVER);
            boton.setEffect(new Glow(0.3));
        });

        boton.setOnMouseExited(e -> {
            boton.setStyle(ESTILO_BOTON_BASE);
            boton.setEffect(null);
        });
    }

    private void agregarSombraBoton(Button boton) {
        DropShadow sombra = new DropShadow();
        sombra.setRadius(5);
        sombra.setOffsetX(3);
        sombra.setOffsetY(3);
        sombra.setColor(Color.color(0, 0, 0, 0.5));
        boton.setEffect(sombra);
    }

    private void accionLanzarDados() {
        System.out.println("Lanzando dados...");
        // Implementar lógica de lanzar dados
    }

    private void accionComerciar() {
        System.out.println("Comerciar...");
        // Implementar lógica de comercio
    }

    private void accionTerminarTurno() {
        System.out.println("Terminando turno...");
        // Implementar lógica de fin de turno
    }
    private void accionarMostrarCartas() {
        Stage miniStage = new Stage();
        miniStage.setTitle("Cartas");

        // ---------- CONTENEDOR PRINCIPAL ----------
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #2e4d2e, #1f331f);" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-color: #c0c0c0;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 12;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 15, 0, 0, 4);"
        );

        // ---------- TÍTULO ----------
        Label titulo = new Label("Elegí una carta:");
        titulo.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;"
        );
        layout.getChildren().add(titulo);

        // ---------- LISTA DE CARTAS ----------
        VBox listaCartas = new VBox(10);
        listaCartas.setPadding(new Insets(5));
        listaCartas.setAlignment(Pos.TOP_CENTER);

        List<Carta> cartasActuales = jugadorActual.obtenerCartas();

        for (Carta carta : cartasActuales) {

            Button opcion = new Button(carta.toString());
            opcion.setPrefWidth(220);

            opcion.setStyle(
                    "-fx-background-color: linear-gradient(to bottom, #4e8b4e, #3a6d3a);" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 14px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 8;" +
                            "-fx-border-radius: 8;" +
                            "-fx-border-color: #2a4d2a;" +
                            "-fx-border-width: 2;"
            );

            opcion.setOnMouseEntered(ev ->
                    opcion.setStyle(
                            "-fx-background-color: linear-gradient(to bottom, #6ecf6e, #5bb85b);" +
                                    "-fx-text-fill: white;" +
                                    "-fx-font-size: 14px;" +
                                    "-fx-font-weight: bold;" +
                                    "-fx-background-radius: 8;" +
                                    "-fx-border-radius: 8;" +
                                    "-fx-border-color: #3c7d3c;" +
                                    "-fx-border-width: 2;"
                    )
            );

            opcion.setOnMouseExited(ev ->
                    opcion.setStyle(
                            "-fx-background-color: linear-gradient(to bottom, #4e8b4e, #3a6d3a);" +
                                    "-fx-text-fill: white;" +
                                    "-fx-font-size: 14px;" +
                                    "-fx-font-weight: bold;" +
                                    "-fx-background-radius: 8;" +
                                    "-fx-border-radius: 8;" +
                                    "-fx-border-color: #2a4d2a;" +
                                    "-fx-border-width: 2;"
                    )
            );

            opcion.setOnAction(ev -> {
                System.out.println("Elegiste: " + carta);
                miniStage.close();
            });

            listaCartas.getChildren().add(opcion);
        }

        // ---------- SCROLL ----------
        ScrollPane scroll = new ScrollPane(listaCartas);
        scroll.setFitToWidth(true);
        scroll.setStyle(
                "-fx-background: transparent;" +
                        "-fx-background-color: transparent;"
        );
        scroll.setPrefHeight(260);

        layout.getChildren().add(scroll);

        // ---------- ESCENA ----------
        Scene scene = new Scene(layout, 300, 380);
        miniStage.setScene(scene);
        miniStage.initModality(Modality.APPLICATION_MODAL);
        miniStage.showAndWait();
    }
    public void refrescarVista() {
        panelAbajo.getChildren().clear();
        botonesDerecha.getChildren().clear();
        botonesIzquierda.getChildren().clear();
        construir();
    }


    public void actualizarJugador(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
        refrescarVista();
    }
    public void actualizarBotonesSegunFase(String nombreFase) {
        btnLanzarDados.setVisible(false);
        btnComerciar.setVisible(false);
        btnCartas.setVisible(false);
        btnTerminarTurno.setVisible(false);

        switch (nombreFase) {
            case "Colocación Inicial - Primera Ronda":
            case "Colocación Inicial - Segunda Ronda":
                // Solo permitir construir poblado + carretera inicial
//                btnColocarPobladoInicial.setVisible(true);
                btnTerminarTurno.setVisible(true);
                break;

            case "Dados":
                btnLanzarDados.setVisible(true);
                btnCartas.setVisible(true);
                break;

            case "Comercio":
                btnComerciar.setVisible(true);
                btnTerminarTurno.setVisible(true);
                break;

            case "Construccion":
//                btnConstruir.setVisible(true);
                btnComerciar.setVisible(true);
                btnTerminarTurno.setVisible(true);
                break;

            case "JugarCartas":
                btnCartas.setVisible(true);
                btnTerminarTurno.setVisible(true);
                break;
        }
    }
}
