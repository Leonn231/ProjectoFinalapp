package view;

import controller.GestorCitas;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Estudiante;

public class RegistroEstudianteView {
    private GestorCitas gestorCitas = new GestorCitas();

    public void mostrar() {
        Stage stage = new Stage();
        stage.setTitle("Registro de Estudiantes");

        // Crear los elementos del formulario
        Label lblNombre = new Label("Nombre:");
        TextField txtNombre = new TextField();
        Label lblCodigo = new Label("Código de Estudiante:");
        TextField txtCodigo = new TextField();
        Label lblCorreo = new Label("Correo:");
        TextField txtCorreo = new TextField();
        Label lblTelefono = new Label("Teléfono:");
        TextField txtTelefono = new TextField();
        Button btnGuardar = new Button("Guardar");

        // Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(lblNombre, 0, 0);
        grid.add(txtNombre, 1, 0);
        grid.add(lblCodigo, 0, 1);
        grid.add(txtCodigo, 1, 1);
        grid.add(lblCorreo, 0, 2);
        grid.add(txtCorreo, 1, 2);
        grid.add(lblTelefono, 0, 3);
        grid.add(txtTelefono, 1, 3);
        grid.add(btnGuardar, 1, 4);

        // Acciones del botón
        btnGuardar.setOnAction(event -> {
            Estudiante estudiante = new Estudiante();
            estudiante.setNombre(txtNombre.getText());
            estudiante.setCodigoEstudiante(txtCodigo.getText());
            estudiante.setCorreo(txtCorreo.getText());
            estudiante.setTelefono(txtTelefono.getText());
            gestorCitas.registrarEstudiante(estudiante);

            System.out.println("Estudiante guardado exitosamente");
            stage.close();
        });

        // Mostrar la escena
        Scene scene = new Scene(grid, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}


