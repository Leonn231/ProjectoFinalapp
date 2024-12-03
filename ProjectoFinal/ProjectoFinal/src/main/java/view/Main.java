package view;

import controller.GestorCitas;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;  // Usar HBox en lugar de VBox
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private boolean isAuthenticated = false; // Bandera para comprobar si el usuario está autenticado
    GestorCitas gestorCitas = new GestorCitas();

    @Override
    public void start(Stage primaryStage) {
        gestorCitas.inicializarDatos();

        // Mostrar la pantalla de inicio de sesión
        LoginView loginView = new LoginView();
        loginView.mostrar(primaryStage, () -> cargarVistaPrincipal(primaryStage)); // Cargar la vista principal después del login
    }

    private void cargarVistaPrincipal(Stage primaryStage) {
        // Crear botones para acceder a las diferentes vistas
        Button btnGestionCitas = new Button("Gestionar Citas");
        Button btnRegistroEstudiantes = new Button("Registrar Estudiantes");
        Button btnDisponibilidadPsicologos = new Button("Ver Disponibilidad de Psicólogos");

        // Estilo de los botones (según el estilo solicitado)
        String buttonStyle = "-fx-background-color: transparent; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 20; " +  // Tamaño de fuente aumentado
                "-fx-font-weight: bold; " +
                "-fx-padding: 15; " +    // Aumentar el padding para hacer los botones más grandes
                "-fx-border-color: white; " +
                "-fx-border-width: 2; " +  // Borde más grueso
                "-fx-border-radius: 5;";

        btnGestionCitas.setStyle(buttonStyle);
        btnRegistroEstudiantes.setStyle(buttonStyle);
        btnDisponibilidadPsicologos.setStyle(buttonStyle);

        // Acciones de los botones
        btnGestionCitas.setOnAction(e -> new GestorCitasView().mostrar());
        btnRegistroEstudiantes.setOnAction(e -> new RegistroEstudianteView().mostrar());
        btnDisponibilidadPsicologos.setOnAction(e -> new DisponibilidadView().mostrar());

        // Layout principal con los botones, ahora usando HBox para alinearlos horizontalmente
        HBox layout = new HBox(70, btnGestionCitas, btnDisponibilidadPsicologos, btnRegistroEstudiantes); // Aumento el espacio entre los botones a 70

        // Centrar los botones
        layout.setStyle("-fx-alignment: center;");

        // Crear StackPane para el fondo con la imagen
        StackPane root = new StackPane();
        root.setStyle("-fx-background-image: url('https://old.cue.edu.co/upload/gallery/201711031008587.jpg'); " +
                "-fx-background-size: cover; -fx-background-position: center;");

        // Filtro oscuro translúcido (capa de superposición)
        Pane overlay = new Pane();
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

        // Añadir el filtro y los botones al StackPane
        root.getChildren().addAll(overlay, layout);

        // Configurar la escena y la ventana principal
        Scene scene = new Scene(root, 800, 600);  // Tamaño más grande
        primaryStage.setTitle("Sistema de Gestión de Citas - Clínica Psicológica");
        primaryStage.setScene(scene);

        // Asegurarse de que la ventana tenga un tamaño adecuado
        primaryStage.setWidth(800);  // Establecer el ancho
        primaryStage.setHeight(600); // Establecer el alto
        primaryStage.setResizable(true);  // Permitir que la ventana sea redimensionable

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}









