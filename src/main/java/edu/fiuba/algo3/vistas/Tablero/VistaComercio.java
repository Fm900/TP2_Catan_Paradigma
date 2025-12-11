package edu.fiuba.algo3.vistas.Tablero;

import edu.fiuba.algo3.controllers.ControladorComercioConBanca;
import edu.fiuba.algo3.controllers.ControladorComercioConJugadores;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.*;

public class VistaComercio {

    // Selecciones temporales del usuario
    private final List<Recurso> recursosQueOfrece = new ArrayList<>();
    private final List<Recurso> recursosQuePide = new ArrayList<>();
    private final List<Carta> cartasSeleccionadas = new ArrayList<>();

    private final ControladorComercioConJugadores controladorConJugadores;
    private final ControladorComercioConBanca controladorConBanca;
    private final Stage ownerStage;

        public VistaComercio(Stage ownerStage,
                             ControladorComercioConJugadores controladorConJugadoresJugadores,
                             ControladorComercioConBanca controladorConBanca) {

            this.ownerStage = ownerStage;
            this.controladorConJugadores = controladorConJugadoresJugadores;
            this.controladorConBanca = controladorConBanca;
        }

    // ----------------------------------------------------------------------
    // CREAR VENTANA MODAL (no bloqueante)
    // ----------------------------------------------------------------------
    private Stage crearVentanaModal(String titulo) {
        Stage modal = new Stage();
        modal.initOwner(ownerStage);
        modal.initModality(Modality.WINDOW_MODAL); // menos intrusivo que APPLICATION_MODAL
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

    // ----------------------------------------------------------------------
    // PANTALLA INICIAL
    // ----------------------------------------------------------------------
    public void mostrarPantallaInicial() {

        Stage modal = crearVentanaModal("Opciones de Comercio");

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER_LEFT);

        Button btnJugadores = new Button("Comercio con Jugadores");
        Button btnBanca = new Button("Comercio con Maritimo");

        btnJugadores.setOnAction(e -> {
            // cerramos el modal actual y abrimos el flujo de comercio (no bloqueante)
            modal.hide();
            controladorConJugadores.abrirComercioEntreJugadores();
        });

        btnBanca.setOnAction(e -> {
                modal.hide();
                controladorConBanca.abrirComercioConBanca();
        });

        root.getChildren().addAll(new Label("Elegí el tipo de comercio:"), btnJugadores, btnBanca);

        Scene scene = new Scene(root, 400, 250);
        modal.setScene(scene);
        modal.show(); // no showAndWait()
    }

    // ----------------------------------------------------------------------
    // PANTALLA DE COMERCIO ENTRE JUGADORES
    // ----------------------------------------------------------------------
    public void mostrarComercioEntreJugadores() {

        Stage modal = crearVentanaModal("Comercio entre Jugadores");

        recursosQueOfrece.clear();
        recursosQuePide.clear();

        BorderPane root = new BorderPane();
        VBox panel = new VBox(15);
        panel.setPadding(new Insets(15));
        panel.setAlignment(Pos.TOP_CENTER);

        // Combo para elegir jugador destino
        ComboBox<Jugador> jugadorObjetivo = new ComboBox<>();
        jugadorObjetivo.getItems().addAll(controladorConJugadores.obtenerJugadoresExceptoActual());
        jugadorObjetivo.setPromptText("Elegí un jugador");

        // Mostrar nombre del jugador en el combo (evita "texto raro")
        jugadorObjetivo.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Jugador item, boolean empty) {
                super.updateItem(item, empty);
                setText((item == null || empty) ? null : item.obtenerNombre());
            }
        });
        jugadorObjetivo.setConverter(new StringConverter<>() {
            @Override
            public String toString(Jugador jugador) {
                return jugador == null ? "" : jugador.obtenerNombre();
            }
            @Override
            public Jugador fromString(String string) { return null; }
        });

        // --- PANEL OFRECE (siempre basado en el jugador actual)
        VBox panelOfrece = crearPanelDeRecursos(
                "Recursos que OFRECÉS",
                controladorConJugadores.obtenerRecursosJugadorActual(),
                recursosQueOfrece
        );

        // --- PANEL PIDE (inicia vacío hasta elegir jugador)
        VBox panelPideContenedor = new VBox();
        panelPideContenedor.setSpacing(10);
        panelPideContenedor.setPadding(new Insets(10));
        Label tituloPide = new Label("Recursos que PEDÍS");
        tituloPide.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        panelPideContenedor.getChildren().add(tituloPide);

        // --- CUANDO SE ELIGE UN JUGADOR → RECARGAR PANEL PIDE
        jugadorObjetivo.valueProperty().addListener((obs, oldValue, nuevoJugador) -> {
            panelPideContenedor.getChildren().clear();
            panelPideContenedor.getChildren().add(tituloPide);

            if (nuevoJugador == null) return;

            // limpiar selección anterior
            recursosQuePide.clear();

            // obtener recursos del jugador elegido
            List<Recurso> recursosDelOtro = controladorConJugadores.obtenerRecursosJugadorElegido(nuevoJugador);

            // recrear el panel de recursos dinámicamente (usa la misma función)
            VBox nuevoPanel = crearPanelDeRecursos(
                    "Recursos del jugador " + nuevoJugador.obtenerNombre(),
                    recursosDelOtro,
                    recursosQuePide
            );

            panelPideContenedor.getChildren().add(nuevoPanel);
        });

        Button btnEnviar = new Button("Enviar Propuesta");
        btnEnviar.setOnAction(e -> {
            Jugador destino = jugadorObjetivo.getValue();
            if (!validarSeleccion(destino)) return;

            // cerrar modal y limpiar efecto antes de tocar el modelo/controladorConJugadores
            modal.hide();
            if (ownerStage.getScene() != null && ownerStage.getScene().getRoot() != null) {
                ownerStage.getScene().getRoot().setEffect(null);
            }

            // llamar al método que maneja la propuesta
            controladorConJugadores.manejarComercioEntreJugadores(
                    new ArrayList<>(recursosQueOfrece),
                    new ArrayList<>(recursosQuePide),
                    destino
            );
        });

        panel.getChildren().addAll(
                new Label("Seleccioná jugador destino:"),
                jugadorObjetivo,
                panelOfrece,
                panelPideContenedor,
                btnEnviar
        );

        ScrollPane scroll = new ScrollPane(panel);
        scroll.setFitToWidth(true);

        root.setCenter(scroll);

        Scene scene = new Scene(root, 900, 700); // ventana más grande por defecto
        modal.setScene(scene);
        modal.show(); // show en lugar de showAndWait para no bloquear
    }

    // ----------------------------------------------------------------------
    // PANEL VISUAL DE RECURSOS
    // ----------------------------------------------------------------------
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

    // ----------------------------------------------------------------------
    // VALIDACIONES
    // ----------------------------------------------------------------------
    private boolean validarSeleccion(Jugador objetivo) {
        if (objetivo == null) {
            mostrarAlerta("Tenés que elegir un jugador destino.");
            return false;
        }
        if (recursosQueOfrece.isEmpty()) {
            mostrarAlerta("Tenés que seleccionar al menos 1 recurso para ofrecer.");
            return false;
        }
        if (recursosQuePide.isEmpty()) {
            mostrarAlerta("Tenés que seleccionar al menos 1 recurso para pedir.");
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // ----------------------------------------------------------------------
    // RESPUESTA A LA OFERTA
    // ----------------------------------------------------------------------
    public void mostrarPantallaRespuestaOferta(Jugador jugadorActual) {

        Stage modal = crearVentanaModal("Oferta recibida");

        VBox panel = new VBox(20);
        panel.setPadding(new Insets(20));
        panel.setAlignment(Pos.CENTER);

        Label texto = new Label(jugadorActual.obtenerNombre() + ", recibiste una oferta de comercio.");

        Button btnAceptar = new Button("Aceptar");
        Button btnRechazar = new Button("Rechazar");

        btnAceptar.setOnAction(e -> {
            modal.hide();
            if (ownerStage.getScene() != null && ownerStage.getScene().getRoot() != null) {
                ownerStage.getScene().getRoot().setEffect(null);
            }
            controladorConJugadores.responderOferta(true);
        });

        btnRechazar.setOnAction(e -> {
            modal.hide();
            if (ownerStage.getScene() != null && ownerStage.getScene().getRoot() != null) {
                ownerStage.getScene().getRoot().setEffect(null);
            }
            controladorConJugadores.responderOferta(false);
        });

        panel.getChildren().addAll(texto, btnAceptar, btnRechazar);

        Scene scene = new Scene(panel, 420, 240);
        modal.setScene(scene);
        modal.show();
    }

    public void mostrarComercioConBanca() {

        Stage modal = crearVentanaModal("Comercio con Banca");

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titulo = new Label("Elegí una opción de comercio:");

        Button btnPuerto = new Button("Comerciar con Puerto");
        Button btnBanca = new Button("Comerciar con Banca");
        Button btnComprarCartas = new Button("Comprar Cartas");

        btnPuerto.setOnAction(e -> {
            modal.hide();
            controladorConBanca.abrirComercioConPuerto();
        });

        btnBanca.setOnAction(e -> {
            modal.hide();
            controladorConBanca.abrirComercioDirectoConBanca();
        });

        btnComprarCartas.setOnAction(e -> {
            modal.hide();
            controladorConBanca.abrirCompraDeCartas();
        });

        root.getChildren().addAll(titulo, btnPuerto, btnBanca, btnComprarCartas);

        Scene scene = new Scene(root, 400, 250);
        modal.setScene(scene);
        modal.show();
    }


    public void mostrarCompraDeCartas() {

        Stage modal = crearVentanaModal("Comprar Cartas");

        cartasSeleccionadas.clear();

        VBox root = new VBox(15);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.TOP_CENTER);

        Label titulo = new Label("Seleccioná las cartas que querés comprar");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        FlowPane panelCartas = new FlowPane();
        panelCartas.setHgap(10);
        panelCartas.setVgap(10);
        panelCartas.setPrefWrapLength(800);

        // Obtener cartas desde la banca (singleton)
        List<Carta> cartas = Banca.getInstance().getCartas();

        for (Carta carta : cartas) {

            Label cartaLabel = new Label( carta.getClass().getSimpleName());
            cartaLabel.setPadding(new Insets(12));
            cartaLabel.setStyle("-fx-border-color: black; -fx-background-color: white;");
            cartaLabel.setMinWidth(150);
            cartaLabel.setAlignment(Pos.CENTER);

            cartaLabel.setOnMouseClicked(e -> {

                if (!cartasSeleccionadas.contains(carta)) {
                    cartasSeleccionadas.add(carta);
                    cartaLabel.setStyle("-fx-border-color: green; -fx-background-color: lightgreen;");
                } else {
                    cartasSeleccionadas.remove(carta);
                    cartaLabel.setStyle("-fx-border-color: black; -fx-background-color: white;");
                }
            });

            panelCartas.getChildren().add(cartaLabel);
        }

        Button btnComprar = new Button("Confirmar Compra");
        btnComprar.setOnAction(e -> {
            modal.hide();
            controladorConBanca.comprarCartas(cartasSeleccionadas);
        });

        root.getChildren().addAll(titulo, panelCartas, btnComprar);

        Scene scene = new Scene(root, 900, 600);
        modal.setScene(scene);
        modal.show();
    }

}