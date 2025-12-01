package edu.fiuba.algo3.vistas.Principales;

import edu.fiuba.algo3.controllers.ControladorMusica;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.lang.reflect.Method;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class BarraSuperior {
    private static ControladorMusica controladorMusica;

    public static void inicializarControladorMusica() {
        if (controladorMusica == null) {
            controladorMusica = new ControladorMusica();
        }
    }
    public static VBox crearLayoutConBarra(javafx.scene.Node contenido, Stage stage) {
        inicializarControladorMusica();
        VBox layoutPrincipal = new VBox();
        layoutPrincipal.getStyleClass().add("layout-principal-con-barra");
        // Crear la barra de menú
        MenuBar menuBar = crearBarraMenuDiscreta(stage);

        // Layout general
        layoutPrincipal.getChildren().addAll(menuBar, contenido);
        VBox.setVgrow(contenido, Priority.ALWAYS);

        return layoutPrincipal;
    }

    private static MenuBar crearBarraMenuDiscreta(Stage stage) {
        MenuBar menuBar = new MenuBar();

        menuBar.getStylesheets().add(
                BarraSuperior.class.getResource("/estilos/barra-superior.css").toExternalForm()
        );


        //Menú JUEGO

        Menu menuJuego = new Menu("Juego");

        MenuItem pantallaCompleta = new MenuItem("Pantalla Completa");
        MenuItem ventanaCompleta = new MenuItem("Modo Ventana");
        MenuItem salir = new MenuItem("Salir");

// Función para actualizar disponibilidad
        Runnable actualizarOpciones = () -> {
            boolean esMaximized = stage.isMaximized();

            pantallaCompleta.setDisable(!esMaximized);
            ventanaCompleta.setDisable(esMaximized);
        };

// Acción: activar pantalla completa
        pantallaCompleta.setOnAction(e -> {
            stage.setMaximized(false);
            stage.setFullScreen(true);
            actualizarOpciones.run();
        });

// Acción: salir de pantalla completa (modo ventana)
        ventanaCompleta.setOnAction(e -> {
            stage.setFullScreen(false);
            stage.setMaximized(true);
            actualizarOpciones.run();
        });

// Acción salir
        salir.setOnAction(e -> javafx.application.Platform.exit());


// Agregar items
        menuJuego.getItems().addAll(
                pantallaCompleta,
                ventanaCompleta,
                new SeparatorMenuItem(),
                salir
        );

// Inicializar estado
        actualizarOpciones.run();

        Menu menuMusica = new Menu("Música");

        MenuItem activarMusica = new MenuItem("Activar Música");
        MenuItem desactivarMusica = new MenuItem("Desactivar Música");
        MenuItem siguienteCancion = new MenuItem("Siguiente Canción");
        MenuItem estadoMusica = new MenuItem("Estado: Activada");
        Runnable actualizarEstadoMusica = () -> {
            if (controladorMusica.isMusicaActivada()) {
                estadoMusica.setText("Estado: Activada - " + controladorMusica.getCancionActual());
                activarMusica.setDisable(true);
                desactivarMusica.setDisable(false);
                siguienteCancion.setDisable(false);
            } else {
                estadoMusica.setText("Estado: Desactivada");
                activarMusica.setDisable(false);
                desactivarMusica.setDisable(true);
                siguienteCancion.setDisable(true);
            }
        };
// Acciones para los items del menú Música
        activarMusica.setOnAction(e -> {
            controladorMusica.activarMusica();
            actualizarEstadoMusica.run();
        });

        desactivarMusica.setOnAction(e -> {
            controladorMusica.desactivarMusica();
            actualizarEstadoMusica.run();
        });

        siguienteCancion.setOnAction(e -> {
            controladorMusica.siguienteCancion();
            actualizarEstadoMusica.run();
        });

        menuMusica.getItems().addAll(
                activarMusica,
                desactivarMusica,
                new SeparatorMenuItem(),
                siguienteCancion,
                new SeparatorMenuItem(),
                estadoMusica
        );
        actualizarEstadoMusica.run();

//           Menú AYUDA
        Menu menuAyuda = new Menu("Ayuda");
        MenuItem instrucciones = new MenuItem("Instrucciones");
        MenuItem acercaDe = new MenuItem("Acerca de");

        instrucciones.setOnAction(e -> mostrarInstrucciones(stage));
        acercaDe.setOnAction(e -> abrirURLGitHub());

        menuAyuda.getItems().addAll(instrucciones, acercaDe);

        menuBar.getMenus().addAll(menuJuego, menuMusica,menuAyuda);

        return menuBar;
    }


    private static void abrirURLGitHub() {
        try {
            // Método usando HostServices de JavaFX
            String url = "https://github.com/Fm900/TP2_Catan_Paradigma";

            // Si tienes acceso al Stage principal, puedes usar:
            // getHostServices().showDocument(url);

            // Método alternativo usando reflexión
            Class<?> hostServicesClass = Class.forName("javafx.application.HostServices");
            Method showDocumentMethod = hostServicesClass.getMethod("showDocument", String.class);

            // Necesitas una referencia a tu aplicación principal
            // showDocumentMethod.invoke(getHostServices(), url);

            // Método más compatible usando Desktop
            java.awt.Desktop.getDesktop().browse(new java.net.URI(url));

        } catch (Exception e) {
            System.err.println("Error al abrir URL: " + e.getMessage());

            // Mostrar la URL en consola como fallback
            System.out.println("Visita: https://github.com/Fm900/TP2_Catan_Paradigma");

            // Opcional: mostrar alerta con la URL
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Acerca de");
            alert.setHeaderText("TP2 - Catan Paradigma");
            alert.setContentText("URL del proyecto: https://github.com/Fm900/TP2_Catan_Paradigma");
            alert.showAndWait();
        }
    }
    private static void mostrarInstrucciones(Stage ownerStage) {
        Stage instruccionesStage = new Stage();
        instruccionesStage.setTitle("Instrucciones del Juego");

        instruccionesStage.initModality(Modality.WINDOW_MODAL);
        instruccionesStage.initOwner(ownerStage);

        // TextArea
        TextArea areaInstrucciones = new TextArea();
        areaInstrucciones.setText(obtenerTextoInstrucciones());
        areaInstrucciones.setEditable(false);
        areaInstrucciones.setWrapText(true);

        ScrollPane scrollPane = new ScrollPane(areaInstrucciones);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.getStyleClass().add("scroll-pane");

        Button btnCerrar = new Button("Cerrar");
        btnCerrar.getStyleClass().add("boton-cerrar");
        btnCerrar.setOnAction(e -> instruccionesStage.close());

        Label titulo = new Label("Instrucciones del Juego");
        titulo.getStyleClass().add("titulo-instrucciones");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.getStyleClass().add("contenedor-principal");
        layout.getChildren().addAll(
                titulo,
                scrollPane,
                btnCerrar
        );

        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        VBox.setMargin(btnCerrar, new Insets(10, 0, 0, 0));
        btnCerrar.setMaxWidth(Double.MAX_VALUE);

        Scene scene = new Scene(layout, 500, 450);

        // CSS
        try {
            scene.getStylesheets().add(
                    BarraSuperior.class.getResource("/estilos/instrucciones.css").toExternalForm()
            );
        } catch (Exception e) {
            System.err.println("No se pudo cargar el CSS de instrucciones: " + e.getMessage());
        }

        instruccionesStage.setScene(scene);
        instruccionesStage.setMinWidth(500);
        instruccionesStage.setMinHeight(450);
        instruccionesStage.showAndWait();
    }
    private static String obtenerTextoInstrucciones() {
        try {
            InputStream inputStream = BarraSuperior.class.getResourceAsStream("/instrucciones/reglas_catan.txt");
            if (inputStream == null) {
                return "No se pudieron cargar las instrucciones. Archivo no encontrado.";
            }

            // Leer el contenido del archivo
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            return "Error al cargar las instrucciones: " + e.getMessage();
        }
    }
}
