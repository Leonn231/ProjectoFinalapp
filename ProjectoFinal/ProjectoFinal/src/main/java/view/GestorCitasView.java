//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package view;

import controller.GestorCitas;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cita;

public class GestorCitasView {
    private final GestorCitas gestorCitas = new GestorCitas();

    public GestorCitasView() {
    }

    public void mostrar() {
        Stage stage = new Stage();
        Label lblTitulo = new Label("Gestión de Citas");
        TableView<Cita> tablaCitas = new TableView();
        TableColumn<Cita, Long> colId = new TableColumn("ID");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<Cita, String> colEstudiante = new TableColumn("Estudiante");
        colEstudiante.setCellValueFactory((c) -> ((Cita)c.getValue()).getEstudiante() != null ? new SimpleStringProperty(((Cita)c.getValue()).getEstudiante().getNombre()) : new SimpleStringProperty(""));
        TableColumn<Cita, String> colPsicologo = new TableColumn("Psicólogo");
        colPsicologo.setCellValueFactory((c) -> ((Cita)c.getValue()).getPsicologo() != null ? new SimpleStringProperty(((Cita)c.getValue()).getPsicologo().getNombre()) : new SimpleStringProperty(""));
        TableColumn<Cita, String> colFechaHora = new TableColumn("Fecha y Hora");
        colFechaHora.setCellValueFactory((c) -> new SimpleStringProperty(((Cita)c.getValue()).getFechaHora().toString()));
        tablaCitas.getColumns().addAll(new TableColumn[]{colId, colEstudiante, colPsicologo, colFechaHora});
        this.cargarTablaCitas(tablaCitas);
        Button btnRegistrarCita = new Button("Registrar Nueva Cita");
        Button btnModificarCita = new Button("Modificar Cita");
        Button btnCancelarCita = new Button("Cancelar Cita");
        btnRegistrarCita.setOnAction((e) -> (new RegistroCitaView()).mostrar());
        btnModificarCita.setOnAction((e) -> {
            Cita citaSeleccionada = (Cita)tablaCitas.getSelectionModel().getSelectedItem();
            if (citaSeleccionada != null) {
                TextInputDialog dialog = new TextInputDialog(citaSeleccionada.getFechaHora().toString());
                dialog.setTitle("Modificar Cita");
                dialog.setHeaderText("Modificar Fecha y Hora");
                dialog.setContentText("Ingrese la nueva fecha y hora (YYYY-MM-DDTHH:MM):");
                dialog.showAndWait().ifPresent((nuevaFechaHora) -> {
                    try {
                        this.gestorCitas.modificarCita(citaSeleccionada.getId(), nuevaFechaHora);
                        this.cargarTablaCitas(tablaCitas);
                        this.mostrarMensaje("Cita modificada correctamente.");
                    } catch (IllegalArgumentException ex) {
                        this.mostrarMensaje(ex.getMessage());
                    } catch (Exception ex) {
                        this.mostrarMensaje("Error al modificar la cita: " + ex.getMessage());
                    }

                });
            } else {
                this.mostrarMensaje("Por favor, seleccione una cita.");
            }

        });
        btnCancelarCita.setOnAction((e) -> {
            Cita citaSeleccionada = (Cita)tablaCitas.getSelectionModel().getSelectedItem();
            if (citaSeleccionada != null) {
                this.gestorCitas.cancelarCita(citaSeleccionada.getId());
                this.cargarTablaCitas(tablaCitas);
                this.mostrarMensaje("Cita cancelada correctamente.");
            } else {
                this.mostrarMensaje("Por favor, seleccione una cita.");
            }

        });
        VBox layout = new VBox((double)10.0F, new Node[]{lblTitulo, tablaCitas, btnRegistrarCita, btnModificarCita, btnCancelarCita});
        Scene scene = new Scene(layout, (double)800.0F, (double)600.0F);
        stage.setScene(scene);
        stage.setTitle("Gestión de Citas");
        stage.show();
    }

    private void cargarTablaCitas(TableView<Cita> tabla) {
        List<Cita> citas = this.gestorCitas.obtenerCitas();
        tabla.setItems(FXCollections.observableArrayList(citas));
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.show();
    }
}


