package edu.fiuba.algo3.vistas.Tablero;

import edu.fiuba.algo3.controllers.ControladorComercioConBanca;
import edu.fiuba.algo3.controllers.ControladorComercioConJugadores;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import edu.fiuba.algo3.modelo.Tablero.Puerto.Especifico;
import edu.fiuba.algo3.modelo.Tablero.Puerto.Tasa;
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

        // Botones
        Button btnJugadores = new Button("Comercio con Jugadores");
        Button btnBanca = new Button("Comercio con Banca");
        Button btnComprarCarta = new Button("Comprar Carta");

        // == Acciones ==
        btnJugadores.setOnAction(e -> {
            modal.hide();
            controladorConJugadores.abrirComercioEntreJugadores();
        });

        btnBanca.setOnAction(e -> {
            modal.hide();
            controladorConBanca.abrirComercioConBanca(); // se asume que ya lo creaste
        });

        btnComprarCarta.setOnAction(e -> {
            modal.hide();
            controladorConBanca.abrirCompraDeCartas();
        });


        root.getChildren().addAll(
                new Label("Elegí el tipo de comercio:"),
                btnJugadores,
                btnBanca,
                btnComprarCarta
        );

        Scene scene = new Scene(root, 400, 260);
        modal.setScene(scene);
        modal.show();
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

        btnPuerto.setOnAction(e -> {
            modal.hide();
            controladorConBanca.abrirComercioConPuerto();
        });

        btnBanca.setOnAction(e -> {
            modal.hide();
            controladorConBanca.abrirComercioDirectoConBanca();
        });

        root.getChildren().addAll(titulo, btnPuerto, btnBanca);

        Scene scene = new Scene(root, 400, 250);
        modal.setScene(scene);
        modal.show();
    }

    public void comprarCarta() {
        try {
            var carta = controladorConBanca.comprarCarta();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Carta obtenida");
            alert.setContentText("Compraste: " + carta.getClass().getSimpleName());
            alert.showAndWait();

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No se pudo comprar la carta");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }


    public void mostrarEleccionDeTasa() {

        Stage modal = crearVentanaModal("Comercio con Banca (" + "4" + ":1)");

        BorderPane root = new BorderPane();
        VBox panel = new VBox(20);
        panel.setPadding(new Insets(20));
        panel.setAlignment(Pos.TOP_CENTER);

        Label titulo = new Label("Elegí 4 recursos iguales y 1 recurso de la banca");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // -----------------------------
        // Recursos del jugador
        // -----------------------------
        Label lblJugador = new Label("Tus recursos (elige 4 iguales):");
        lblJugador.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        List<Recurso> recursosJugador = controladorConBanca.obtenerRecursosJugadorActual();
        List<Recurso> seleccionJugador = new ArrayList<>();

        VBox panelJugador = crearPanelDeRecursos("Tus recursos", recursosJugador, seleccionJugador);

        // -----------------------------
        // Recursos de la banca
        // -----------------------------
        Label lblBanca = new Label("Recursos disponibles en la Banca (elige 1):");
        lblBanca.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        List<Recurso> recursosBanca = Banca.getInstance().getRecursos();
        List<Recurso> seleccionBanca = new ArrayList<>();

        VBox panelBanca = crearPanelDeRecursos("Recursos de la Banca", recursosBanca, seleccionBanca);

        // -----------------------------
        // Botón confirmar intercambio
        // -----------------------------
        Button btnConfirmar = new Button("Confirmar intercambio");
        btnConfirmar.setOnAction(e -> {

            // Validaciones
            if (seleccionJugador.size() != 4) {
                mostrarAlerta("Tenés que elegir EXACTAMENTE 3 recursos.");
                return;
            }

            // Verificar que los tres sean del mismo tipo
            Class<?> tipo = seleccionJugador.get(0).getClass();
            boolean todosIguales = seleccionJugador.stream().allMatch(r -> r.getClass().equals(tipo));

            if (!todosIguales) {
                mostrarAlerta("Los 3 recursos deben ser del MISMO tipo.");
                return;
            }

            if (seleccionBanca.size() != 1) {
                mostrarAlerta("Tenés que elegir 1 recurso de la banca.");
                return;
            }

            // Cerrar ventana limpia
            modal.hide();
            if (ownerStage.getScene() != null && ownerStage.getScene().getRoot() != null) {
                ownerStage.getScene().getRoot().setEffect(null);
            }

            // Llamar al controlador
            controladorConBanca.iniciarComercioConBanca(
                    new ArrayList<>(seleccionJugador),
                    seleccionBanca.get(0)

            );
        });

        panel.getChildren().addAll(
                titulo,
                lblJugador,
                panelJugador,
                lblBanca,
                panelBanca,
                btnConfirmar
        );

        ScrollPane scroll = new ScrollPane(panel);
        scroll.setFitToWidth(true);
        root.setCenter(scroll);

        Scene scene = new Scene(root, 900, 700);
        modal.setScene(scene);
        modal.show();
    }


    public void mostrarPantallaPuertos() {

        Stage modal = crearVentanaModal("Puertos disponibles");

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        Label titulo = new Label("Elegí un puerto / tasa de comercio");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        List<Tasa> tasas = controladorConBanca.obtenerTasasDisponibles();

        for (Tasa t : tasas) {
            Button btn = new Button(nombreTasa(t));
            btn.setPrefWidth(250);
            btn.setOnAction(e -> {
                modal.hide();
                // limpiar blur
                if (ownerStage.getScene() != null && ownerStage.getScene().getRoot() != null)
                    ownerStage.getScene().getRoot().setEffect(null);

                controladorConBanca.seleccionarTasa(t);
            });
            root.getChildren().add(btn);
        }

        Scene scene = new Scene(root, 400, 350);
        modal.setScene(scene);
        modal.show();  // NO cambia la escena del juego, solo superpone la ventana
    }

    private String nombreTasa(Tasa t) {
        String tipo = t.getClass().getSimpleName();
        if (tipo.equals("Especifico")){
            Especifico tipoEspecifico = (Especifico)t;
            Recurso recurso = tipoEspecifico.getRecurso();
            return "Puerto 2:1 (Específico)" + recurso.getClass().getSimpleName();}
        if (tipo.equals("Generico")) return "Puerto 3:1 (Genérico)";
        return tipo;
    }
    public void mostrarIntercambioConPuerto(Tasa tasa) {

        Stage modal = crearVentanaModal("Comercio con Puerto");

        BorderPane root = new BorderPane();
        VBox panel = new VBox(20);
        panel.setPadding(new Insets(20));
        panel.setAlignment(Pos.TOP_CENTER);

        String tituloTexto = "Comercio con Puerto (" + nombreTasa(tasa) + ")";
        Label titulo = new Label(tituloTexto);
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // -----------------------------
        // Recursos del jugador
        // -----------------------------
        Label lblJugador = new Label("Tus recursos (elige según la tasa):");
        lblJugador.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        List<Recurso> recursosJugador = controladorConBanca.obtenerRecursosJugadorActual();
        List<Recurso> seleccionJugador = new ArrayList<>();

        VBox panelJugador = crearPanelDeRecursos("Tus recursos", recursosJugador, seleccionJugador);

        // -----------------------------
        // Recursos de la banca
        // -----------------------------
        List<Recurso> recursosBanca = Banca.getInstance().getRecursos();
        List<Recurso> seleccionBanca = new ArrayList<>();

        VBox panelBanca;

        boolean esEspecifico = tasa instanceof Especifico;

        Label lblBanca = new Label(
                esEspecifico ?
                        "El recurso recibido es automático según tu puerto:" :
                        "Recursos disponibles en la Banca (elige 1):"
        );
        lblBanca.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        if (esEspecifico) {
            // ⚠ NO permitir elección → se muestra 1 solo recurso fijo
            Recurso recursoFijo = ((Especifico) tasa).getRecurso();

            Label carta = new Label(recursoFijo.getClass().getSimpleName());
            carta.setPadding(new Insets(10));
            carta.setStyle("-fx-border-color: blue; -fx-background-color: lightblue;");
            carta.setMinWidth(120);
            carta.setAlignment(Pos.CENTER);

            seleccionBanca.add(recursoFijo); // agregado automático

            FlowPane cont = new FlowPane(carta);
            cont.setHgap(10);

            panelBanca = new VBox(10, new Label("Recurso recibido automáticamente:"), cont);

        } else {
            // GENÉRICO (3:1) → usuario elige 1 recurso
            panelBanca = crearPanelDeRecursos("Recursos de la Banca", recursosBanca, seleccionBanca);
        }

        // -----------------------------
        // Botón confirmar
        // -----------------------------
        Button btnConfirmar = new Button("Confirmar intercambio");
        btnConfirmar.setOnAction(e -> {

            if (!esEspecifico) {
                if (seleccionBanca.size() != 1) {
                    mostrarAlerta("Debés elegir 1 recurso de la banca.");
                    return;
                }
            }

            modal.hide();
            if (ownerStage.getScene() != null && ownerStage.getScene().getRoot() != null)
                ownerStage.getScene().getRoot().setEffect(null);

            // El recurso recibido ya está en seleccionBanca (automático si es específico)
            controladorConBanca.efectuarComercioConPuerto(
                    tasa,
                    new ArrayList<>(seleccionJugador),
                    seleccionBanca.get(0)
            );
        });

        panel.getChildren().addAll(
                titulo,
                lblJugador,
                panelJugador,
                lblBanca,
                panelBanca,
                btnConfirmar
        );

        ScrollPane scroll = new ScrollPane(panel);
        scroll.setFitToWidth(true);

        root.setCenter(scroll);

        Scene scene = new Scene(root, 900, 700);
        modal.setScene(scene);
        modal.show();
    }

}