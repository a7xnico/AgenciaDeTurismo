package vista.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

import Agencia.Modelo.Servicios.Hotel;

public class VentanaHotelesController {

    @FXML
    private TableView<Hotel> tablaHoteles;

    @FXML
    private TableColumn<Hotel, String> colNombre;

    @FXML
    private TableColumn<Hotel, String> colUbicacion;

    @FXML
    private TableColumn<Hotel, Integer> colEstrellas;

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colUbicacion.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colEstrellas.setCellValueFactory(new PropertyValueFactory<>("estrellas"));

        ObservableList<Hotel> hoteles = FXCollections.observableArrayList(
                new Hotel("Hotel del Mar", "Mar del Plata", 4, 12000.0, 15),
                new Hotel("Cielo Azul", "Buenos Aires", 5, 25000.0, 10),
                new Hotel("El Sol", "Córdoba", 3, 8000.0, 20)
        );

        tablaHoteles.setItems(hoteles);
    }

    public void volverVentanaPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/fxml/ventana_principal.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Agencia de Turismo");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}