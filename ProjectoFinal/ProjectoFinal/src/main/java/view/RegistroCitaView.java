package view;

import controller.GestorCitas;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cita;
import model.Estudiante;
import model.Psicologo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class RegistroCitaView {
    private final GestorCitas gestorCitas = new GestorCitas();

    public RegistroCitaView() {

    }

    public void mostrar() {
        Stage stage = new Stage();

        Label lblTitulo = new Label("Registrar Nueva Cita");
        ComboBox<Estudiante> cbEstudiantes = new ComboBox<>();
        ComboBox<Psicologo> cbPsicologos = new ComboBox<>();
        TextField txtFechaHora = new TextField();
        txtFechaHora.setPromptText("Ingrese fecha y hora (YYYY-MM-DDTHH:MM)");

        Button btnRegistrar = new Button("Registrar Cita");

        // Cargar estudiantes y psicólogos en los ComboBox
        cargarEstudiantes(cbEstudiantes);
        cargarPsicologos(cbPsicologos);

        // Acción al registrar una cita
        btnRegistrar.setOnAction(e -> {
            Estudiante estudianteSeleccionado = cbEstudiantes.getValue();
            Psicologo psicologoSeleccionado = cbPsicologos.getValue();
            String fechaHoraTexto = txtFechaHora.getText();

            if (estudianteSeleccionado == null || psicologoSeleccionado == null || fechaHoraTexto.isEmpty()) {
                mostrarMensaje("Por favor, complete todos los campos.");
                return;
            }

            try {
                LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraTexto);
                Cita nuevaCita = new Cita();
                nuevaCita.setEstudiante(estudianteSeleccionado);
                nuevaCita.setPsicologo(psicologoSeleccionado);
                nuevaCita.setFechaHora(fechaHora);

                gestorCitas.registrarCita(nuevaCita);
                mostrarMensaje("Cita registrada exitosamente.");
                stage.close();
            } catch (IllegalArgumentException ex) {
                mostrarMensaje(ex.getMessage());
            } catch (DateTimeParseException ex) {
                mostrarMensaje("El formato de fecha y hora es incorrecto. Use el formato: YYYY-MM-DDTHH:MM");
            }
        });


        // Layout
        VBox layout = new VBox(10, lblTitulo, cbEstudiantes, cbPsicologos, txtFechaHora, btnRegistrar);
        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Registrar Nueva Cita");
        stage.show();
    }

    private void cargarEstudiantes(ComboBox<Estudiante> cbEstudiantes) {
        List<Estudiante> estudiantes = gestorCitas.obtenerEstudiantes(); // Método a implementar si no existe
        cbEstudiantes.setItems(FXCollections.observableArrayList(estudiantes));
    }

    private void cargarPsicologos(ComboBox<Psicologo> cbPsicologos) {
        List<Psicologo> psicologos = gestorCitas.obtenerPsicologos();
        cbPsicologos.setItems(FXCollections.observableArrayList(psicologos));
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.show();
    }
}

