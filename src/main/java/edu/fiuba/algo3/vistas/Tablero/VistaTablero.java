package edu.fiuba.algo3.vistas.Tablero;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.vistas.Principales.EscenaGeneral;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class VistaTablero extends EscenaGeneral {
    private Tablero tablero;
    private List<Jugador> jugadores;
    private Jugador actual;

    private StackPane tableroPane;

    private HBox panelArriba;

    private HBox barraAbajo;
    private HBox espacioCentro;
    private HBox espacioDerecha;
    private HBox espacioIzquierda;


    public VistaTablero(Tablero tablero, Stage stage, List<Jugador> jugadores, Jugador jugador) {
        super(stage);
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.actual = jugador;
        iniciar();
    }
    public void iniciar() {
        new GenerarVistaDeJugadores(jugadores,actual, panelArriba).construir();

        new GeneradorVistaTablero(tableroPane).crearTablero(tablero.terrenos(), tablero.vertices(),tablero.aristas());

        new GenerarRecuYBotones(espacioCentro, espacioDerecha,espacioIzquierda,actual).construir();
    }

    @Override
    protected Pane createLayout() {
        BorderPane root = new BorderPane();

        //PARTE DE ARRIBA
        panelArriba = new HBox();
        root.setTop(panelArriba);
        panelArriba.setAlignment(Pos.CENTER);


        //PARTE DEL CENTRO
        tableroPane = new StackPane();
        root.setCenter(tableroPane);


        barraAbajo = new HBox();
        barraAbajo.setPadding(new Insets(10));
        barraAbajo.setSpacing(50);
        barraAbajo.setAlignment(Pos.CENTER);

        espacioIzquierda = new HBox();
        espacioIzquierda.setAlignment(Pos.CENTER_LEFT);

        espacioCentro = new HBox();
        espacioCentro.setAlignment(Pos.CENTER);

        espacioDerecha = new HBox();
        espacioDerecha.setAlignment(Pos.CENTER_RIGHT);

        barraAbajo.getChildren().addAll(espacioIzquierda, espacioCentro, espacioDerecha);
        HBox.setHgrow(espacioCentro, Priority.ALWAYS);

        root.setBottom(barraAbajo);



        return root;
    }

    @Override
    protected void createControllers(Stage stage) {
        // acá van listeners futuros si querés clickear aristas, vértices, etc.
    }

    @Override
    protected void createStyles() {
    }

    @Override
    protected String getBackgroundImagePath() {
        return "Imagenes/FondoSeleccionarJugadoresCatan.png";
    }

    public void actualizarJugadorActual(Jugador jugadorActual) {
    }

    public void actualizarInfoTurno(String nombreFaseActual) {

    }


}


//
//// En VistaTablero.java
//public void actualizarInfoTurno(String nombreFase) {
//    // 1. Actualizar el label de la fase actual
//    lblFaseActual.setText(nombreFase);
//
//    // 2. Mostrar/ocultar botones según la fase
//    actualizarBotonesSegunFase(nombreFase);
//
//    // 3. Actualizar el panel de información del turno
//    actualizarPanelInfo(nombreFase);
//
//    // 4. Opcional: Mostrar instrucciones para el jugador
//    mostrarInstruccionesFase(nombreFase);
//}
//
//private void actualizarBotonesSegunFase(String fase) {
//    // Ocultar todos los botones primero
//    btnTirarDados.setVisible(false);
//    btnComerciar.setVisible(false);
//    btnConstruir.setVisible(false);
//    btnJugarCarta.setVisible(false);
//    btnTerminarFase.setVisible(false);
//    btnSiguienteTurno.setVisible(false);
//
//    switch (fase) {
//        case "Colocación Inicial - Primera Ronda":
//        case "Colocación Inicial - Segunda Ronda":
//            // Solo permitir construir poblado + carretera inicial
//            btnColocarPobladoInicial.setVisible(true);
//            btnSiguienteTurno.setVisible(true);
//            btnSiguienteTurno.setText("Confirmar construcción");
//            break;
//
//        case "Dados":
//            btnTirarDados.setVisible(true);
//            btnJugarCarta.setVisible(true); // Solo carta Caballero
//            btnJugarCarta.setText("Jugar Caballero");
//            break;
//
//        case "Comercio":
//            btnComerciar.setVisible(true);
//            btnTerminarFase.setVisible(true);
//            btnTerminarFase.setText("Terminar Comercio");
//            break;
//
//        case "Construccion":
//            btnConstruir.setVisible(true);
//            btnComprarCarta.setVisible(true);
//            btnTerminarFase.setVisible(true);
//            btnTerminarFase.setText("Terminar Construcción");
//            break;
//
//        case "JugarCartas":
//            btnJugarCarta.setVisible(true);
//            btnTerminarFase.setVisible(true);
//            btnTerminarFase.setText("Terminar Turno");
//            break;
//    }
//}
//
//private void actualizarPanelInfo(String fase) {
//    // Actualizar un panel con información contextual
//    switch (fase) {
//        case "Dados":
//            lblInfo.setText("Tira los dados para producir recursos");
//            panelInfo.setStyle("-fx-background-color: #FFE082;"); // Amarillo suave
//            break;
//
//        case "Comercio":
//            lblInfo.setText("Comercia con otros jugadores o con la banca");
//            panelInfo.setStyle("-fx-background-color: #90CAF9;"); // Azul suave
//            break;
//
//        case "Construccion":
//            lblInfo.setText("Construye caminos, poblados o ciudades");
//            panelInfo.setStyle("-fx-background-color: #A5D6A7;"); // Verde suave
//            break;
//
//        case "JugarCartas":
//            lblInfo.setText("Puedes jugar cartas de desarrollo");
//            panelInfo.setStyle("-fx-background-color: #CE93D8;"); // Púrpura suave
//            break;
//
//        default:
//            lblInfo.setText("Coloca tu poblado y carretera iniciales");
//            panelInfo.setStyle("-fx-background-color: #FFCCBC;"); // Naranja suave
//            break;
//    }
//}

//private void mostrarInstruccionesFase(String fase) {
//    String instrucciones = "";
//
//    switch (fase) {
//        case "Colocación Inicial - Primera Ronda":
//            instrucciones = "1. Haz clic en un vértice para colocar tu poblado\n" +
//                    "2. Haz clic en una arista adyacente para tu carretera";
//            break;
//
//        case "Colocación Inicial - Segunda Ronda":
//            instrucciones = "1. Coloca tu segundo poblado\n" +
//                    "2. Coloca tu segunda carretera\n" +
//                    "3. Recibirás recursos de los hexágonos adyacentes";
//            break;
//
//        case "Dados":
//            instrucciones = "Tira los dados para comenzar tu turno\n" +
//                    "(Opcional: Juega carta Caballero antes de tirar)";
//            break;
//
//        case "Comercio":
//            instrucciones = "• Intercambia recursos con otros jugadores\n" +
//                    "• Comercia con la banca (4:1 general o 2:1/3:1 si tienes puerto)";
//            break;
//
//        case "Construccion":
//            instrucciones = "• Construye caminos (1 madera + 1 arcilla)\n" +
//                    "• Construye poblados (1 madera + 1 arcilla + 1 oveja + 1 trigo)\n" +
//                    "• Mejora a ciudad (3 mineral + 2 trigo)\n" +
//                    "• Compra carta de desarrollo (1 oveja + 1 trigo + 1 mineral)";
//            break;
//
//        case "JugarCartas":
//            instrucciones = "Juega cartas de desarrollo si tienes\n" +
//                    "(No puedes jugar cartas compradas este turno)";
//            break;
//    }
//
//    if (txtInstrucciones != null) {
//        txtInstrucciones.setText(instrucciones);
//    }
//}
//public class VistaTablero {
//    // Labels informativos
//    private Label lblJugadorActual;
//    private Label lblFaseActual;
//    private Label lblInfo;
//    private TextArea txtInstrucciones;
//
//    // Botones de acción
//    private Button btnTirarDados;
//    private Button btnComerciar;
//    private Button btnConstruir;
//    private Button btnJugarCarta;
//    private Button btnComprarCarta;
//    private Button btnTerminarFase;
//    private Button btnSiguienteTurno;
//    private Button btnColocarPobladoInicial;
//
//    // Panel de información
//    private VBox panelInfo;
//
//    // Constructor
//    public VistaTablero(Tablero tablero, Stage stage, List<Jugador> jugadores,
//                        Jugador jugadorActual, ControladorGeneral controlador) {
//        // ... código existente ...
//
//        inicializarElementosUI();
//        configurarEventos(controlador);
//    }
//
//    private void inicializarElementosUI() {
//        // Crear todos los elementos
//        lblFaseActual = new Label("Fase: Inicial");
//        lblFaseActual.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
//
//        lblInfo = new Label();
//        lblInfo.setStyle("-fx-font-size: 14px;");
//
//        txtInstrucciones = new TextArea();
//        txtInstrucciones.setEditable(false);
//        txtInstrucciones.setPrefHeight(100);
//
//        panelInfo = new VBox(10, lblFaseActual, lblInfo, txtInstrucciones);
//        panelInfo.setPadding(new Insets(10));
//        panelInfo.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-radius: 5;");
//
//        // ... crear botones ...
//    }
//
//    private void configurarEventos(ControladorGeneral controlador) {
//        btnTerminarFase.setOnAction(e -> {
//            controlador.siguienteFase();
//        });
//
//        // ... otros eventos ...
//    }
//
//    public void actualizarInfoTurno(String nombreFase) {
//        lblFaseActual.setText(nombreFase);
//        actualizarBotonesSegunFase(nombreFase);
//        actualizarPanelInfo(nombreFase);
//        mostrarInstruccionesFase(nombreFase);
//    }
//
//    // ... resto de métodos ...
//}
