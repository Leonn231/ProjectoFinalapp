package view;

import controller.GestorCitas;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Psicologo;

import java.util.List;

public class DisponibilidadView {
    private final GestorCitas gestorCitas = new GestorCitas();

    public void mostrar() {
        Stage stage = new Stage();

        // Título
        Label lblTitulo = new Label("Disponibilidad de Psicólogos");
        lblTitulo.setFont(Font.font(24));
        lblTitulo.setTextFill(Color.WHITE);

        // Crear tabla
        TableView<Psicologo> tablaPsicologos = new TableView<>();

        // Configurar columnas
        TableColumn<Psicologo, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Psicologo, String> colEspecialidad = new TableColumn<>("Especialidad");
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));

        TableColumn<Psicologo, String> colHorariosDisponibles = new TableColumn<>("Horarios Disponibles");
        colHorariosDisponibles.setCellValueFactory(new PropertyValueFactory<>("horariosDisponibles"));

        // Agregar columnas a la tabla
        tablaPsicologos.getColumns().addAll(colNombre, colEspecialidad, colHorariosDisponibles);

        // Cargar los datos
        cargarTablaDisponibilidad(tablaPsicologos);

        // Estilo de transparencia para la tabla
        tablaPsicologos.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-border-color: transparent;");
        tablaPsicologos.setRowFactory(tv -> {
            TableRow<Psicologo> row = new TableRow<>();
            row.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);");
            return row;
        });

        // Configurar layout
        VBox layout = new VBox(10, lblTitulo, tablaPsicologos);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        // Fondo con imagen
        Image backgroundImage = new Image("https://old.cue.edu.co/upload/gallery/201711031008587.jpg");
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true
                )
        );
        layout.setBackground(new Background(bgImage));

        // Filtro oscuro translúcido
        StackPane root = new StackPane();
        Pane overlay = new Pane();
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        root.getChildren().addAll(overlay, layout);

        // Crear escena
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Disponibilidad de Psicólogos");
        stage.show();
    }

    private void cargarTablaDisponibilidad(TableView<Psicologo> tabla) {
        // Obtener datos desde el controlador
        List<Psicologo> psicologos = gestorCitas.obtenerPsicologos();

        // Asignar datos al TableView
        tabla.setItems(FXCollections.observableArrayList(psicologos));
    }
}





