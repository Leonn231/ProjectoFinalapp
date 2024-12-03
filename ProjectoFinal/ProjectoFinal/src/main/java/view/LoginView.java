package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Usuario;
import repository.UsuarioRepository;

public class LoginView {
    private final UsuarioRepository usuarioRepo = new UsuarioRepository();

    public void mostrar(Stage primaryStage, Runnable onLoginSuccess) {
        // Layout principal
        VBox layout = new VBox(20); // Espaciado entre elementos
        layout.setAlignment(Pos.CENTER); // Centrar todos los elementos
        layout.setStyle("-fx-background-color: transparent; -fx-padding: 20;");

        // Texto de bienvenida
        Label lblBienvenida = new Label("Bienvenido al Centro de Citas de Psicología\nUniversidad Alexander von Humboldt");
        lblBienvenida.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Más grande y grueso
        lblBienvenida.setTextFill(Color.WHITE);
        lblBienvenida.setAlignment(Pos.CENTER); // Centrar texto dentro del Label
        lblBienvenida.setStyle("-fx-text-alignment: center;"); // Centrar texto multilinea

        // Título
        Label lblTitulo = new Label("Iniciar Sesión");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 24)); // Más grande y grueso
        lblTitulo.setTextFill(Color.WHITE);
        lblTitulo.setAlignment(Pos.CENTER); // Centrar texto dentro del Label
        lblTitulo.setStyle("-fx-text-alignment: center;");

        // Campos de texto
        TextField txtUsername = new TextField();
        txtUsername.setPromptText("Usuario");
        txtUsername.setMaxWidth(250); // Tamaño más corto
        txtUsername.setStyle("-fx-padding: 10; -fx-font-size: 16; -fx-border-color: white; -fx-border-width: 1; -fx-border-radius: 5; " +
                "-fx-background-color: rgba(26,26,26,0.5); -fx-text-fill: white;");

        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Contraseña");
        txtPassword.setMaxWidth(250); // Tamaño más corto
        txtPassword.setStyle("-fx-padding: 10; -fx-font-size: 16; -fx-border-color: white; -fx-border-width: 1; -fx-border-radius: 5; " +
                "-fx-background-color: rgba(26,26,26,0.5); -fx-text-fill: white;");

        // Botones
        Button btnLogin = new Button("Iniciar Sesión");
        btnLogin.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 16; -fx-font-weight: bold; " +
                "-fx-padding: 10; -fx-border-color: white; -fx-border-width: 1; -fx-border-radius: 5;");

        Button btnRegister = new Button("Registrarse");
        btnRegister.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 16; -fx-font-weight: bold; " +
                "-fx-padding: 10; -fx-border-color: white; -fx-border-width: 1; -fx-border-radius: 5;");

        // Acciones de los botones
        btnLogin.setOnAction(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            Usuario usuario = usuarioRepo.obtenerPorUsername(username);

            if (usuario != null && usuario.getPassword().equals(password)) {
                onLoginSuccess.run(); // Ejecutar callback para cargar la vista principal
            } else {
                mostrarMensaje("Usuario o contraseña incorrectos.");
            }
        });

        btnRegister.setOnAction(e -> new RegistroUsuarioView().mostrar());

        // Contenedor de botones
        HBox botonesLayout = new HBox(10, btnLogin, btnRegister);
        botonesLayout.setAlignment(Pos.CENTER);

        // Agregar componentes al layout principal
        layout.getChildren().addAll(lblBienvenida, lblTitulo, txtUsername, txtPassword, botonesLayout);

        // Fondo con imagen
        StackPane root = new StackPane();
        root.setStyle("-fx-background-image: url('https://old.cue.edu.co/upload/gallery/201711031008587.jpg'); " +
                "-fx-background-size: cover; -fx-background-position: center;");

        // Crear un AnchorPane para contener la imagen
        Image logoImage = new Image("https://unihumboldt.edu.co/hs-fs/hubfs/humboldt-theme/logo-cue-ccaq.webp?width=304&height=44&name=logo-cue-ccaq.webp");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setPreserveRatio(true);  // Mantener proporción de la imagen
        logoImageView.setFitHeight(44);  // Establecer la altura de la imagen
        logoImageView.setFitWidth(304);  // Establecer el ancho de la imagen

        // Colocar la imagen en la esquina superior izquierda
        AnchorPane.setTopAnchor(logoImageView, 10.0);  // Separación de la parte superior
        AnchorPane.setLeftAnchor(logoImageView, 10.0);  // Separación del lado izquierdo

        // Crear un AnchorPane para contener la imagen
        AnchorPane logoPane = new AnchorPane();
        logoPane.getChildren().add(logoImageView);

        // Agregar el AnchorPane (con la imagen) al root
        root.getChildren().add(logoPane);

        // Filtro oscuro translúcido (debe ir debajo de los controles interactivos)
        Pane overlay = new Pane();
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

        // Colocar el overlay debajo de todos los demás elementos en el StackPane
        root.getChildren().add(overlay);

        // Agregar layout principal y filtro oscuro al root
        root.getChildren().addAll(layout);

        // Crear la escena y establecerla en el Stage
        Scene scene = new Scene(root, 800, 600);  // Tamaño de la ventana
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inicio de Sesión");
        primaryStage.show();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.show();
    }
}









