package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Usuario;
import repository.UsuarioRepository;

public class RegistroUsuarioView {
    private final UsuarioRepository usuarioRepo = new UsuarioRepository();

    public void mostrar() {
        Stage stage = new Stage();

        // Título
        Label lblTitulo = new Label("Registrar Usuario");
        lblTitulo.setFont(Font.font(24));
        lblTitulo.setTextFill(Color.WHITE);

        // Campos de texto
        TextField txtUsername = new TextField();
        txtUsername.setPromptText("Usuario");
        txtUsername.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.1); " +
                        "-fx-text-fill: white; " +
                        "-fx-prompt-text-fill: gray; " +
                        "-fx-border-color: white; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-padding: 5px;"
        );

        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Contraseña");
        txtPassword.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.1); " +
                        "-fx-text-fill: white; " +
                        "-fx-prompt-text-fill: gray; " +
                        "-fx-border-color: white; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-padding: 5px;"
        );

        // Botón de registro
        Button btnRegistrar = new Button("Registrar");
        btnRegistrar.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-border-color: white; " +
                        "-fx-border-width: 1px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-padding: 10px;"
        );

        // Acción del botón
        btnRegistrar.setOnAction(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();

            if (username.isEmpty() || password.isEmpty()) {
                mostrarMensaje("Por favor, complete todos los campos.");
                return;
            }

            if (usuarioRepo.obtenerPorUsername(username) != null) {
                mostrarMensaje("El usuario ya existe.");
                return;
            }

            Usuario usuario = new Usuario(username, password);
            usuarioRepo.guardar(usuario);
            mostrarMensaje("Usuario registrado exitosamente.");
            stage.close();
        });

        // Layout principal
        VBox layout = new VBox(10, lblTitulo, txtUsername, txtPassword, btnRegistrar);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: black;"); // Fondo negro

        // Crear escena
        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Registro de Usuario");
        stage.show();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.show();
    }
}


